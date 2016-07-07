package com.std.sms.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.std.sms.ao.IPoolAO;
import com.std.sms.bo.IPoolBO;
import com.std.sms.core.OrderNoGenerater;
import com.std.sms.domain.Pool;

public class PoolAOImpl implements IPoolAO {

    @Autowired
    private IPoolBO poolBO;

    @Override
    public void doSaveSOutToPool(String mobile, String content, String channel,
            Date sendDatetime) {
        Pool data = new Pool();
        String[] str = channel.split("-");
        data.setCompanyCode(str[0]);
        data.setCode(OrderNoGenerater.generateM("PO"));
        data.setMobile(mobile);
        data.setContent(content);
        data.setChannel(channel);
        data.setToSendDatetime(sendDatetime);
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

}
