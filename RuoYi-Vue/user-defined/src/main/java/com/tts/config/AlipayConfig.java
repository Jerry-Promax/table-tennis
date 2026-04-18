package com.tts.config;

import lombok.Data;
import org.springframework.stereotype.Component;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import java.util.Map;

/**
 * 支付宝配置类
 */
@Component
@Data
public class AlipayConfig {
    /** 应用ID */
    private String appId = "9021000160685333";
    /** 应用私钥 */
    private String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCbgGX3a0bE6WopeIXT+e9BtrFkOe7pCDleIgFwxwsIX5qGgvA6kgLM4d9z93x/eGhss88eNSKJ+ULpd/O9afmai57Xa18HXoS4/8R4B2//IoNGCDjMN7/kAxoMIpEvYXtB9dWXhrsw5ViPEtv/pKBfBr6c5fkheMYh94FUlN/6Omse0Q4jh+0dP6qfC9b9VvRzIciDBugkvrRA9dtJDMOGUAcpy7ThPjqp1LxzK5kN0daRKdLN9K7uDmG1b3CMFH/by2ng0B2EE9UiBBhEfzpfZLjjOI51TrkWAKiDO2ehPDG4RfaHYmfmbduzvzkMYT4I+IaS7n7LculZg2mmrYZDAgMBAAECggEALGHEOI7MvwzjuPxmLe8q43WSQcnrL2WiggRwptCYjIZE41lh2UWtOO7KyR180iD6pPPZDIUb24diGqKa1Zf2nq504oUJtV+7v9V4LM3qoEYoc4eLNnY+9YQRHh+LkptfA593m+zfZoA++27ljZxeDWEubDeTGIORAUbUCeEH4DHzcwuBJd+Cy79twrg1a5/4LFMxNUlKJ9dmKlbWbCFBJDO7qKaebyJcWGH9abE3tuWGJCILzuSHC9u5c8IexTvEsYvl3ac83ujsOHaWBzofsq7Ms1mNwVwHcxm7iIsSLi2XkbayR2SzmOm7QhZVOw2hQgrg/qfdizuFSA1B/uRQyQKBgQDiC6uVnoUG1OKB2rBvjtK9QVJK8tM/B4HaSPH4j3pY67DkdAeiuCXy2UJPs18f0CQIsJZb2LbZutopxPE/rquui+lKQqVAxyoMuda4flDlh0zITG3TeURiD2l99UGztcCFm78EwYvd6HOpbZzbqtFYGMZh11ey4WSrdDqPqyUoHQKBgQCwG5rlQ8mkHsE9RFLAoHc3bFdfjn92WbIWUJQSpAHG87vrZh9JxVbOXfkWb1C7NO4g9QHGrkmsFNnmL22HDjv/EwbE+vxV9FTVmFrxP0Kyjlh3Azzbp5X/EBmSuuKatN1aoifFVSHzM/p+wTu8DossFZytG7WBbsiH8ZhCEFvZ3wKBgQChQA/74KQm1nBqj7WzLyBYT6PDwWs+mD1imQy78qYljqSWo8raoiC4F5H9RwjBqfo6lLX9+R+fPHLqvwoWEqV4E2itR9OY3Tq670skOz+8LQNyE0t5QxWsGjB34SAocaxP42Wln+gFug/gOkheT4GdJbdHDpYKDk2BHZ205TLRZQKBgBGxNEaEIOXUESXOj2G6vT9cKwr2jeed6z5Cqh2VTHhoVCC6CbqnkKj61+NhG4MzNhkphBwptk0Lazis2xwOWgiuKKfxbxhTi5JJ1BkYeeLmWgzazmpi07bkLy2GiO1x7isEz2oLu3Vra2cAkre53lF3el4JwppWPt8pblp0ukWRAoGAEaZl5N7Zyu4LdhhJ3S//edGniwReg4dFhIe8KEjAZeEonq7g/nmm5pzCxqLzwyR/P8KHY+1RMPUIhqZUDyiyXdENojtmi7m+6zCC4hLOK1Ej2VrYEiiem3EAbzG7ZacySuCZL3Fmjee5nu6++/MSPgSCckI+BRazGGy+jof9CtQ=";
    /** 支付宝公钥 */
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmUChRR1iXIglFytgpf0Oa+HomxTpun4HyEx9F8cHmf17mp3ihhcssJoCjkh61zBV8SPK7WBs8wAgGPUC/wNAOi24fWPDznEncGaNsvnqMl3uTNLtAhUZLJe82Ng6BcRIBygeziThejb4ilxqBCheONCY80CBjNJvnWn7qHSKEM9xB7PsExdCxnFDblE5rJcnXZwNtZqKb6DHMjxeppU1K2vcTxlCAgGXI96Xyv4IWBb+i4BYhydsABwbHq2Pdb9XEFQDKwN/AroDT8NrVmUyB0hW4Cuv/gH3CfoG6bd7qYT0IJvfhNExFQcJZpwGkOE99z4PjmjvvxrFa7j6UnfTHwIDAQAB";
    /** 支付宝网关 */
    private String gatewayHost = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    /** 签名类型 */
    private String signType = "RSA2";
    /** 支付宝同步通知路径 */
    private String returnUrl = "http://localhost:8080/payment/alipay/return";
    /** 异步通知地址 */
    private String notifyUrl = "http://s78873bb.natappfree.cc/payment/alipay/notify";
    
    private String charset = "UTF-8";
    private String format = "json";
    
    private AlipayClient alipayClient;

    public String sentRequestToAlipay(String outTradeNo, String totalAmount, String subject) throws AlipayApiException {
        alipayClient = new DefaultAlipayClient(gatewayHost, appId, privateKey, format, charset, publicKey, signType);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);
        String body = "";
        request.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = alipayClient.pageExecute(request).getBody();
        System.out.println("返回的结果是：" + result);
        return result;
    }

    /**
     * 验证签名
     * @param params 支付宝回调参数
     * @return 验签结果
     * @throws AlipayApiException
     */
    public boolean verifySignature(Map<String, String> params) throws AlipayApiException {
        return AlipaySignature.rsaCheckV1(params, publicKey, charset, signType);
    }
}
