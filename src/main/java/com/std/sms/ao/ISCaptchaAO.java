package com.std.sms.ao;

public interface ISCaptchaAO {
    /** 
     * 执行发送验证码函数
     * @param channel
     * @param mobile
     * @return 
     * @create: 2016年7月10日 下午2:17:47 zuixian
     * @history: 
     */
    public String doSend(String channel, String mobile);

    /** 
     * 检查验证码是否正确
     * @param captcha
     * @param mobile
     * @return 验证码正确返回true
     * @create: 2016年7月10日 下午2:18:24 zuixian
     * @history: 
     */
    public boolean doCheck(String captcha, String mobile);
}
