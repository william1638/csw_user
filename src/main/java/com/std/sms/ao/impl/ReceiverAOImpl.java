package com.std.sms.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.sms.ao.IReceiverAO;
import com.std.sms.bo.IReceiverBO;
import com.std.sms.bo.base.Paginable;
import com.std.sms.domain.Receiver;

@Service
public class ReceiverAOImpl implements IReceiverAO {

    @Autowired
    private IReceiverBO receiverBO;

    /** 
     * @see com.std.sms.ao.IReceiverAO#synchReceivers()
     */
    @Override
    public void synchReceivers() {
        // TODO Auto-generated method stub

    }

    /** 
     * @see com.std.sms.ao.IReceiverAO#importReceivers(java.util.List)
     */
    @Override
    public void importReceivers(List<Receiver> dataList) {
        for (Receiver receiver : dataList) {
            receiverBO.saveReceiver(receiver);
        }
    }

    @Override
    public void addReceiver(Receiver data) {
        receiverBO.saveReceiver(data);
    }

    @Override
    public void editReceiver(Receiver data) {
        receiverBO.getReceiver(data.getMobile(), data.getSystemCode());
        receiverBO.refreshReceiver(data);
    }

    @Override
    public void dropReceiver(String mobile, String systemCode) {
        receiverBO.getReceiver(mobile, systemCode);
        receiverBO.removeReceiver(mobile, systemCode);
    }

    @Override
    public Paginable<Receiver> queryReceiverPage(int start, int limit,
            Receiver condition) {
        return receiverBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Receiver> queryReceiverList(Receiver condition) {
        return receiverBO.queryReceiverList(condition);
    }

    @Override
    public Receiver getReceiver(String mobile, String systemCode) {
        return receiverBO.getReceiver(mobile, systemCode);
    }
}
