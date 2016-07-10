package com.std.sms.ao;

public interface ISCaptchaAO {
    /** 
     * @param channel
     * @param mobile
     * @return 
     * @create: 2016年7月10日 下午2:17:47 zuixian
     * @history: 
     */
    public String doSend(String channel, String mobile);

    /** 
     * @param captcha
     * @param mobile
     * @return 
     * @create: 2016年7月10日 下午2:18:24 zuixian
     * @history: 
     */
    public boolean doCheck(String captcha, String mobile);
}
