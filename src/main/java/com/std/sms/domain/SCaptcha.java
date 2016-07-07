package com.std.sms.domain;

import com.std.sms.dao.base.ABaseDO;

/** 
 * @author: zuixian 
 * @since: 2016年7月6日 下午8:23:54 
 * @history:
 */
public class SCaptcha extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -5517106014111527180L;

    // 编号
    private String code;

    // 公司编号
    private String companyCode;

    // 手机号码
    private String mobile;

    // 验证码
    private String captcha;

    // 状态
    private String status;

    // 发送时间
    private String sendTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
