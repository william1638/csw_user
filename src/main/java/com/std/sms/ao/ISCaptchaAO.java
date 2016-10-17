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
     * @param code
     * @param captcha
     * @return 验证码正确返回true
     * @create: 2016年7月10日 下午2:18:24 zuixian
     * @history: 
     */
    public boolean doCheck(String code, String captcha);

    /** 
     * 通过公司编号与手机号来检查验证码是否正确
     * @param companyCode
     * @param mobile
     * @param captcha
     * @return 
     * @create: 2016年10月17日 下午4:08:15 zuixian
     * @history: 
     */
    public boolean doCheckByCM(String companyCode, String mobile, String captcha);
}
