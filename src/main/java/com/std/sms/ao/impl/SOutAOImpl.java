package com.std.sms.ao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.std.sms.ao.ISOutAO;
import com.std.sms.bo.ISOutBO;
import com.std.sms.core.OrderNoGenerater;
import com.std.sms.domain.SOut;
import com.std.sms.sent.Senter;

public class SOutAOImpl implements ISOutAO {

    @Autowired
    Senter senter;

    @Autowired
    ISOutBO sOutBO;

    @Override
    public boolean doSend(String mobile, String content, String channel) {
        String[] str = channel.split("-");
        if (str[2].equalsIgnoreCase("Y")) {
            senter.send(mobile, content, str[0], str[1]);
            return true;
        }
        return false;
    }

    @Override
    public Long doSaveSOut(String mobile, String content, String channel,
            String sendDatetime, boolean flag) {
        SOut data = new SOut();
        data.setCode(OrderNoGenerater.generateM("SO"));
        data.setMobile(mobile);
        data.setContent(content);
        data.setChannel(channel);
        Date now = new Date();
        if (flag) {
            data.setSendDatetime(now);
            sOutBO.saveSOut(data);
        } else {

        }
        return null;
    }

}
