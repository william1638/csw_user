package com.std.sms.ao;

public interface ISOutAO {

    /** 
     * @param mobile
     * @param content
     * @param channel
     * @return 
     * @create: 2016年7月7日 下午5:44:57 zuixian
     * @history: 
     */
    public boolean doSend(String mobile, String content, String channel);

    /** 
     * @param moible
     * @param content
     * @param flag
     * @return 
     * @create: 2016年7月7日 下午5:45:34 zuixian
     * @history: 
     */
    public Long doSaveSOut(String mobile, String content, String channel,
            String sendDatetime, boolean flag);
}
