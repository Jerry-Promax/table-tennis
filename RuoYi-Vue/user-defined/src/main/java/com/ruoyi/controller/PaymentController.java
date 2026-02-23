package com.ruoyi.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.config.AlipayConfig;
import com.ruoyi.domain.entity.BookOrder;
import com.ruoyi.domain.vo.BookOrderVo;
import com.ruoyi.service.IBookOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支付控制器
 *
 */
@RestController
@RequestMapping("/payment")
public class PaymentController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private IBookOrderService bookOrderService;

    @Autowired
    private AlipayConfig alipayConfig;

    /**
     * 支付宝电脑网站支付
     */
    @GetMapping("/alipay/pay")
    public AjaxResult alipayPay(@RequestParam("orderNo") String orderNo) {
        try {
            // 1. 查询订单信息
            BookOrder query = new BookOrder();
            query.setOrderNo(orderNo);
            List<BookOrderVo> list = bookOrderService.selectBookOrderList(query);
            if (list.isEmpty()) {
                return error("订单不存在");
            }
            BookOrderVo order = list.get(0);

            // 2. 校验订单状态
            if (order.getPayStatus() != null && order.getPayStatus() == 1) {
                return error("订单已支付，请勿重复支付");
            }
            if (order.getPayAmount() == null || order.getPayAmount().compareTo(BigDecimal.ZERO) <= 0) {
                return error("订单金额异常");
            }

            // 3. 构建支付参数
            String subject = "球桌预订-" + order.getTableCode(); // 例如：球桌预订-1号桌
            String amount = order.getPayAmount().toString();

            // 4. 发起支付
            String form = alipayConfig.sentRequestToAlipay(orderNo, amount, subject);
            return success(form); // 前端拿到form后document.write(form)即可跳转
        } catch (Exception e) {
            return error("发起支付失败：" + e.getMessage());
        }
    }
    
    /**
     * 支付宝异步回调
     */
    @PostMapping("/alipay/notify")
    public String alipayNotify(HttpServletRequest request) {
        log.info("收到支付宝异步通知");
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        try {
            // 验证签名
            boolean signVerified = alipayConfig.verifySignature(params);
            if (signVerified) {
                log.info("支付宝验签通过");
                // 商户订单号
                String outTradeNo = request.getParameter("out_trade_no");
                // 交易状态
                String tradeStatus = request.getParameter("trade_status");
                // 付款金额
                String totalAmount = request.getParameter("total_amount");

                if ("TRADE_SUCCESS".equals(tradeStatus) || "TRADE_FINISHED".equals(tradeStatus)) {
                    // 更新订单状态
                    bookOrderService.paySuccess(outTradeNo, new BigDecimal(totalAmount), new Date());
                    log.info("订单支付成功，订单号：{}", outTradeNo);
                }
                return "success";
            } else {
                log.error("支付宝验签失败");
                return "fail";
            }
        } catch (AlipayApiException e) {
            log.error("支付宝回调异常", e);
            return "fail";
        } catch (Exception e) {
            log.error("系统处理回调异常", e);
            return "fail";
        }
    }

    /**
     * 支付宝同步回调
     */
    @GetMapping("/alipay/return")
    public void alipayReturn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("收到支付宝同步回调");
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        try {
            boolean signVerified = alipayConfig.verifySignature(params);
            if (signVerified) {
                log.info("支付宝同步验签通过");
                // 跳转到前端支付成功页面，这里假设前端部署在 localhost:80，路由为 /system/bookOrder
                response.sendRedirect("http://localhost:80/client/profile?tab=myBooking");
            } else {
                log.error("支付宝同步验签失败");
                response.sendRedirect("http://localhost/500");
            }
        } catch (Exception e) {
            log.error("支付宝同步回调异常", e);
            response.sendRedirect("http://localhost/500");
        }
    }
}
