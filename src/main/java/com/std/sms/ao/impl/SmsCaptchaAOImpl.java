package com.std.sms.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.sms.ao.ISmsCaptchaAO;
import com.std.sms.bo.ISmsCaptchaBO;
import com.std.sms.bo.base.Paginable;
import com.std.sms.domain.SmsCaptcha;

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

}
