package com.std.sms.bo.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.sms.bo.ISCaptchaBO;
import com.std.sms.bo.base.PaginableBOImpl;
import com.std.sms.common.DateUtil;
import com.std.sms.core.OrderNoGenerater;
import com.std.sms.dao.ISCaptchaDAO;
import com.std.sms.domain.SCaptcha;
import com.std.sms.enums.ESmsStatus;

@Component
public class SCaptchaBOImpl extends PaginableBOImpl<SCaptcha> implements
        ISCaptchaBO {

    @Autowired
    private ISCaptchaDAO sCaptchaDAO;

    @Override
    public String savaSCaptcha(String channel, String mobile, String captcha) {
        SCaptcha data = new SCaptcha();
        data.setCode(OrderNoGenerater.generateM("SC"));
        data.setCaptcha(captcha);
        data.setStatus(ESmsStatus.SENT_YES.getCode());
        Date now = new Date();
        data.setSendDatetime(now);
        data.setInvalidDatetime(DateUtil.getRelativeDate(now, 600));// 60秒后失效
        sCaptchaDAO.insert(data);
        return data.getCode();
    }

    @Override
    public SCaptcha getSCaptcha(String code) {
        SCaptcha condition = new SCaptcha();
        condition.setCode(code);
        return sCaptchaDAO.select(condition);
    }

    @Override
    public void refreshSCaptchaInfo(SCaptcha data) {
        sCaptchaDAO.updateCheckInfo(data);
    }
}
