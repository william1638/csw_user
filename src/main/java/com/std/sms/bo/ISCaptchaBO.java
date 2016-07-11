package com.std.sms.bo;

import com.std.sms.bo.base.IPaginableBO;
import com.std.sms.domain.SCaptcha;

public interface ISCaptchaBO extends IPaginableBO<SCaptcha> {
    /** 
     * @param data 
     * @create: 2016年7月10日 下午2:11:25 zuixian
     * @history: 
     */
    public String savaSCaptcha(String channel, String mobile, String captcha);

    /** 
     * @param data 
     * @create: 2016年7月10日 下午2:11:48 zuixian
     * @history: 
     */
    public SCaptcha getSCaptcha(String code);

    /** 
     * @param code 
     * @create: 2016年7月10日 下午2:58:51 zuixian
     * @history: 
     */
    public void refreshSCaptchaInfo(SCaptcha data);
}
