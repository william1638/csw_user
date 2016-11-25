package com.std.sms.ao;

import java.util.List;

import com.std.sms.bo.base.Paginable;
import com.std.sms.domain.Receiver;

public interface IReceiverAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 同步接收者
     * @create: 2016年11月20日 下午4:21:27 xieyj
     * @history:
     */
    public void synchReceivers();

    /**
     * 导入接收者
     * @param dataList 
     * @create: 2016年11月20日 下午4:22:21 xieyj
     * @history:
     */
    public void importReceivers(List<Receiver> dataList);

    public void addReceiver(Receiver data);

    public void dropReceiver(String mobile, String systemCode);

    public void editReceiver(Receiver data);

    public Paginable<Receiver> queryReceiverPage(int start, int limit,
            Receiver condition);

    public List<Receiver> queryReceiverList(Receiver condition);

    public Receiver getReceiver(String mobile, String systemCode);

}
