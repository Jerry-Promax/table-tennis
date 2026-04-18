package com.tts.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tts.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 我的赛事
 * 作者：jerry
 * 日期：2026-02-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpVo {
    private Long signupId;
    private Long competitionId;
    private String competitionName;
    private Long userId;
    private String nickName;
    /** 报名开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signupStart;
    private Integer status;
}