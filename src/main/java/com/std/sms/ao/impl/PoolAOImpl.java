package com.std.sms.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.sms.ao.ICompanyAO;
import com.std.sms.ao.IPoolAO;
import com.std.sms.ao.ISOutAO;
import com.std.sms.bo.IPoolBO;
import com.std.sms.common.DateUtil;
import com.std.sms.core.OrderNoGenerater;
import com.std.sms.domain.Company;
import com.std.sms.domain.Pool;
import com.std.sms.sent.Senter;

@Service
public class PoolAOImpl implements IPoolAO {

    @Autowired
    private IPoolBO poolBO;

    @Autowired
    private ISOutAO sOutAO;

    @Autowired
    Senter senter;

    @Autowired
    ICompanyAO companyAO;

    @Override
    public void doSaveSOutToPool(String channel, String mobile, String content,
            String sendDatetime) {
        Pool data = new Pool();
        String[] str = channel.split("-");
        data.setCompanyCode(str[0]);
        data.setCode(OrderNoGenerater.generateM("PO"));
        data.setMobile(mobile);
        data.setContent(content);
        data.setChannel(channel);
        Date toSendDatetime = DateUtil.strToDate(sendDatetime,
            DateUtil.DATA_TIME_PATTERN_2);
        data.setToSendDatetime(toSendDatetime);
        poolBO.savePool(data);
    }

    @Override
    public void doRemoveSOutFromPool(String code) {
        poolBO.removePool(code);
    }

    @Override
    public List<Pool> queryPoolList(String code) {
        Pool data = new Pool();
        data.setCode(code);
        return poolBO.queryPoolList(data);
    }

    @Override
    public void doSendFromPool() {
        List<Pool> list = queryPoolList("");
        if (list != null) {
            for (Pool p : list) {
                String today = DateUtil.getToday(DateUtil.DATA_TIME_PATTERN_2);
                Date now = DateUtil.strToDate(today,
                    DateUtil.DATA_TIME_PATTERN_2);
                Date toSendDatetime = p.getToSendDatetime();
                if (toSendDatetime != null) {
                    if (now.after(toSendDatetime) || now.equals(toSendDatetime)) {
                        String[] str = p.getChannel().split("-");
                        String prefixContent = changeContent(str[0],
                            p.getContent());
                        senter.send(str[0], p.getChannel(), p.getMobile(),
                            prefixContent);
                        sOutAO.doSaveSOut(p.getChannel(), p.getMobile(),
                            prefixContent);
                        doRemoveSOutFromPool(p.getCode());
                    }
                }
            }
        }
    }

    public String changeContent(String companyCode, String content) {
        Company data = companyAO.doGetCompany(companyCode);
        String result = "【" + data.getPrefix() + "】" + content;
        return result;
    }

}
