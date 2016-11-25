package com.std.sms.ao;

import java.util.List;

import com.std.sms.bo.base.Paginable;
import com.std.sms.domain.Sms;

public interface ISmsAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 立即发,type 传 1;定时发，type传2
     * @param data
     * @return 
     * @create: 2016年11月20日 下午4:45:14 xieyj
     * @history:
     */
    public void toSendSms(Sms data);

    /**
     * 失败重发
     * @param data
     * @return 
     * @create: 2016年11月20日 下午4:44:07 xieyj
     * @history:
     */
    public void reSendSms(Sms data);

    /**
     * 成功再发
     * @param id
     * @return 
     * @create: 2016年11月20日 下午4:43:24 xieyj
     * @history:
     */
    public void copySms(Long id);

    /**
     * 定时器触发发送短信
     * @create: 2016年11月20日 下午4:46:53 xieyj
     * @history:
     */
    public void doSmsDaily();

    public Paginable<Sms> querySmsPage(int start, int limit, Sms condition);

    public List<Sms> querySmsList(Sms condition);

    public Sms getSms(Long id);
}
