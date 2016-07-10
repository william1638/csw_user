package com.std.sms.ao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.sms.ao.ICompanyAO;
import com.std.sms.ao.ISOutAO;
import com.std.sms.bo.ISOutBO;
import com.std.sms.bo.base.Paginable;
import com.std.sms.core.OrderNoGenerater;
import com.std.sms.domain.Company;
import com.std.sms.domain.SOut;
import com.std.sms.enums.ESmsStatus;
import com.std.sms.sent.Senter;

@Service
public class SOutAOImpl implements ISOutAO {

    @Autowired
    ICompanyAO companyAO;

    @Autowired
    Senter senter;

    @Autowired
    ISOutBO sOutBO;

    @Override
    public boolean doSend(String channel, String mobile, String content) {
        String[] str = channel.split("-");
        if (str[2].equalsIgnoreCase("K") || str[2].equalsIgnoreCase("M")) {
            String prefixContent = changeContent(str[0], content);
            senter.send(str[0], channel, mobile, prefixContent);
            return true;
        }
        return false;
    }

    @Override
    public String doSaveSOut(String channel, String mobile, String content) {
        SOut data = new SOut();
        String[] str = channel.split("-");
        data.setCode(OrderNoGenerater.generateM("SO"));
        data.setCompanyCode(str[0]);
        data.setMobile(mobile);
        String prefixContent = changeContent(str[0], content);
        data.setContent(prefixContent);
        data.setChannel(channel);
        Date now = new Date();
        data.setSendDatetime(now);
        data.setErrorCode(ESmsStatus.SENT_YES.getCode());
        data.setErrorInfo("成功发送");
        sOutBO.saveSOut(data);
        return data.getCode();
    }

    public String changeContent(String companyCode, String content) {
        Company data = companyAO.doGetCompany(companyCode);
        String result = content + "【" + data.getPrefix() + "】";
        return result;
    }

    @Override
    public Paginable<SOut> querySOutPage(int start, int limit, SOut condition) {
        return sOutBO.getPaginable(start, limit, condition);
    }
}
