package com.std.sms.bo;

import com.std.sms.bo.base.IPaginableBO;
import com.std.sms.domain.SCaptcha;

public interface ISCaptchaBO extends IPaginableBO<SCaptcha> {
    /** 
     * 保存验证码
     * @param data 
     * @create: 2016年7月10日 下午2:11:25 zuixian
     * @history: 
     */
    public String savaSCaptcha(String channel, String mobile, String captcha);

    /** 
     * 获取验证码
     * @param data 
     * @create: 2016年7月10日 下午2:11:48 zuixian
     * @history: 
     */
    public SCaptcha getSCaptcha(String code);

    /** 
     * 更新验证码信息
     * @param code 
     * @create: 2016年7月10日 下午2:58:51 zuixian
     * @history: 
     */
    public void refreshSCaptchaInfo(SCaptcha data);
}
