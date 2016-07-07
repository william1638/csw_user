package com.std.sms.ao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.sms.ao.ISmsCaptchaAO;
import com.std.sms.bo.ISmsCaptchaBO;
import com.std.sms.bo.base.Paginable;
import com.std.sms.common.DateUtil;
import com.std.sms.domain.SmsCaptcha;
import com.std.sms.enums.ESmsStatus;

@Service
public class SmsCaptchaAOImpl implements ISmsCaptchaAO {

    @Autowired
    ISmsCaptchaBO smsCaptchaBO;

    @Override
    public Paginable<SmsCaptcha> querySmsCaptchaPage(int start, int limit,
            SmsCaptcha condition) {
        return smsCaptchaBO.getPaginable(start, limit, condition);
    }

    @Override
    public void doCheckCaptcha(String mobile, String captcha, String bizType) {
        smsCaptchaBO.checkSmsCaptcha(mobile, captcha, bizType);
    }

    @Override
    public Long doSaveSmsCaptcha(String mobile, String captcha, String bizType,
            boolean flag) {
        // 存db
        SmsCaptcha data1 = new SmsCaptcha();
        data1.setMobile(mobile);
        data1.setBizType(bizType);
        data1.setSmsCaptcha(captcha);
        if (flag) {
            data1.setStatus(ESmsStatus.SENT_YES.getCode());
        } else {
            data1.setStatus(ESmsStatus.SENT_NO.getCode());
        }
        Date now = new Date();
        data1.setCreateDatetime(now);
        data1.setSentDatetime(now);
        data1.setInvalidDatetime(DateUtil.getRelativeDate(now, 600));// 60秒后失效
        smsCaptchaBO.saveSentSmsCaptcha(data1);
        return data1.getId();
    }

}
