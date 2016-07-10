package com.std.sms.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.sms.bo.ISCaptchaBO;
import com.std.sms.bo.base.PaginableBOImpl;
import com.std.sms.dao.ISCaptchaDAO;
import com.std.sms.domain.SCaptcha;

@Component
public class SCaptchaBOImpl extends PaginableBOImpl<SCaptcha> implements
        ISCaptchaBO {

    @Autowired
    private ISCaptchaDAO sCaptchaDAO;

    @Override
    public int savaSCaptcha(SCaptcha data) {
        int count = 0;
        if (data != null) {
            count = sCaptchaDAO.insert(data);
        }
        return count;
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
