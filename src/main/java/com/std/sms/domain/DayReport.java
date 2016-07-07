package com.std.sms.domain;

import com.std.sms.dao.base.ABaseDO;

/** 
 * @author: zuixian 
 * @since: 2016年7月6日 下午8:30:33 
 * @history:
 */
public class DayReport extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 612663667784971001L;

    // 编号
    private String code;

    // 公司编号
    private String companyCode;

    // 短信服务商
    private String facilitator;

    // 发送成功的条数
    private String success;

    // 发送失败的条数
    private String failure;

    // 日期
    private String date;

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

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFacilitator() {
        return facilitator;
    }

    public void setFacilitator(String facilitator) {
        this.facilitator = facilitator;
    }
}
