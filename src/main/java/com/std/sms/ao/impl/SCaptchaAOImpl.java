package com.std.sms.ao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.sms.ao.ICompanyAO;
import com.std.sms.ao.ISCaptchaAO;
import com.std.sms.bo.ISCaptchaBO;
import com.std.sms.common.DateUtil;
import com.std.sms.core.OrderNoGenerater;
import com.std.sms.domain.Company;
import com.std.sms.domain.SCaptcha;
import com.std.sms.sent.Senter;
import com.std.sms.util.PhoneUtil;
import com.std.sms.util.RandomUtil;

@Service
public class SCaptchaAOImpl implements ISCaptchaAO {

    @Autowired
    ICompanyAO companyAO;

    @Autowired
    Senter senter;

    @Autowired
    ISCaptchaBO sCaptchaBO;

    @Override
    public String doSend(String channel, String mobile) {
        String[] str = channel.split("-");
        String captcha = RandomUtil.generate4();
        String captchaContent = addContent(mobile, captcha);
        String content = changeContent(str[0], captchaContent);
        senter.send(str[0], channel, mobile, content);
        SCaptcha data = new SCaptcha();
        data.setCode(OrderNoGenerater.generateM("SC"));
        data.setCaptcha(captcha);
        data.setStatus("1");
        Date now = new Date();
        data.setSendDatetime(now);
        data.setInvalidDatetime(DateUtil.getRelativeDate(now, 600));// 60秒后失效
        sCaptchaBO.savaSCaptcha(data);
        return data.getCode();
    }

    @Override
    public boolean doCheck(String code, String captcha) {
        SCaptcha data = sCaptchaBO.getSCaptcha(code);
        Date invalidDatetime = data.getInvalidDatetime();
        Date now = new Date();
        if (data.getCaptcha().equals(captcha) && invalidDatetime.after(now)) {
            data.setStatus("2");
            data.setCheckDatetime(now);
            sCaptchaBO.refreshSCaptchaInfo(data);
            return true;
        } else {
            return false;
        }
    }

    public String changeContent(String companyCode, String content) {
        Company data = companyAO.doGetCompany(companyCode);
        String result = "【" + data.getPrefix() + "】" + content;
        return result;
    }

    private String addContent(String mobile, String captcha) {
        return "尊敬的" + PhoneUtil.hideMobile(mobile) + "用户, 您的验证码为" + captcha
                + "，请妥善保管此验证码，切勿泄露给他人。";
    }

}
