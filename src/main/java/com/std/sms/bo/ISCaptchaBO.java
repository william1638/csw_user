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
    public String savaSCaptcha(String channel, String mobile, String captcha,
            String bizType);

    /** 
     * 获取验证码
     * @param data 
     * @create: 2016年7月10日 下午2:11:48 zuixian
     * @history: 
     */
    public SCaptcha getSCaptcha(String code);

    /** 
     * 通过手机号与公司编号获取验证码
     * @param code
     * @return 
     * @create: 2016年10月17日 下午4:00:59 zuixian
     * @history: 
     */
    public SCaptcha getSCaptchaByCM(String companyCode, String mobile,
            String bizType);

    /** 
     * 更新验证码信息
     * @param code 
     * @create: 2016年7月10日 下午2:58:51 zuixian
     * @history: 
     */
    public void refreshSCaptchaInfo(SCaptcha data);
}
