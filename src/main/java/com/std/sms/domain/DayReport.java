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
    private String channel;

    // 发送成功的条数
    private String sucTimes;

    // 发送失败的条数
    private String failTimes;

    // 日期
    private String reportDate;

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSucTimes() {
        return sucTimes;
    }

    public void setSucTimes(String sucTimes) {
        this.sucTimes = sucTimes;
    }

    public String getFailTimes() {
        return failTimes;
    }

    public void setFailTimes(String failTimes) {
        this.failTimes = failTimes;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
}
