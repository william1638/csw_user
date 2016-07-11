package com.std.sms.ao;

import com.std.sms.bo.base.Paginable;
import com.std.sms.domain.SOut;

public interface ISOutAO {

    String DEFAULT_ORDER_COLUMN = "code";

    /** 
     * @param mobile
     * @param content
     * @param channel
     * @return 
     * @create: 2016年7月7日 下午5:44:57 zuixian
     * @history: 
     */
    public String doSend(String channel, String mobile, String content,
            String sendDatetime);

    /** 
     * @param moible
     * @param content
     * @param flag
     * @return 
     * @create: 2016年7月7日 下午5:45:34 zuixian
     * @history: 
     */
    public String doSaveSOut(String channel, String mobile, String content);

    /** 
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2016年7月10日 上午11:07:04 zuixian
     * @history: 
     */
    public Paginable<SOut> querySOutPage(int start, int limit, SOut condition);
}
