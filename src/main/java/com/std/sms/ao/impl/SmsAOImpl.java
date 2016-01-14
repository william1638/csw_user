package com.std.sms.ao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.sms.ao.ISmsAO;
import com.std.sms.bo.ISmsCaptchaBO;
import com.std.sms.bo.ISmsOutBO;
import com.std.sms.domain.SmsCaptcha;
import com.std.sms.domain.SmsOut;
import com.std.sms.enums.ESmsBizType;
import com.std.sms.enums.ESmsStatus;
import com.std.sms.sent.Senter;
import com.std.sms.util.DateUtil;

@Service
public class SmsAOImpl implements ISmsAO {
    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    ISmsCaptchaBO smsCaptchaBO;

    @Autowired
    Senter senter;

    @Override
    @Transactional
    public Long doSaveSmsOut(String moible, String content, String bizType,
            String remark, boolean flag) {
        SmsOut data = new SmsOut();
        data.setMobile(moible);
        data.setContent(changeContent(content));
        data.setBizType(bizType);
        data.setRemark(remark);
        Date now = new Date();
        data.setCreateDatetime(now);
        if (flag) {
            data.setStatus(ESmsStatus.SENT_YES.getCode());
            data.setSentDatetime(now);
        } else {
            data.setStatus(ESmsStatus.SENT_NO.getCode());
            data.setSentDatetime(null);
        }
        smsOutBO.saveSmsOut(data);

        if (ESmsBizType.YZM.getCode().equalsIgnoreCase(bizType)) {
            // 存db
            SmsCaptcha data1 = new SmsCaptcha();
            data1.setMobile(moible);
            data1.setBizType(bizType);
            data1.setSmsCaptcha(content);
            if (flag) {
                data1.setStatus(ESmsStatus.SENT_YES.getCode());
            } else {
                data1.setStatus(ESmsStatus.SENT_NO.getCode());
            }

            data1.setCreateDatetime(now);
            data1.setSentDatetime(now);
            data1.setInvalidDatetime(DateUtil.getRelativeDate(now, 600));// 60秒后失效
            smsCaptchaBO.saveSentSmsCaptcha(data1);
        }
        return data.getId();
    }

    @Override
    public boolean doSend(String mobile, String content) {
        senter.send(changeContent(content), mobile);
        return true;
    }

    private String changeContent(String content) {
        return content + "【个金所】";
    }

}
