package com.std.sms.bo;

import java.util.List;

import com.std.sms.bo.base.IPaginableBO;
import com.std.sms.domain.Receiver;

public interface IReceiverBO extends IPaginableBO<Receiver> {

    public String saveReceiver(Receiver data);

    public int removeReceiver(String mobile, String systemCode);

    public int refreshReceiver(Receiver data);

    public List<Receiver> queryReceiverList(Receiver condition);

    public Receiver getReceiver(String mobile, String systemCode);
}
