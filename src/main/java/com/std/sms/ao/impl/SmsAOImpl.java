package com.std.sms.ao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.sms.ao.ISmsAO;
import com.std.sms.bo.ISmsOutBO;
import com.std.sms.domain.SmsOut;
import com.std.sms.enums.ESmsStatus;
import com.std.sms.sent.Senter;

@Service
public class SmsAOImpl implements ISmsAO {
    @Autowired
    ISmsOutBO smsOutBO;

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
