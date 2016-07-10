package com.std.sms.dto.req;

public class XN799006Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 3520400355754417697L;

    private String companyCode;

    private String channel;

    private String reportDateStart;

    private String reportDateEnd;

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

    public String getReportDateStart() {
        return reportDateStart;
    }

    public void setReportDateStart(String reportDateStart) {
        this.reportDateStart = reportDateStart;
    }

    public String getReportDateEnd() {
        return reportDateEnd;
    }

    public void setReportDateEnd(String reportDateEnd) {
        this.reportDateEnd = reportDateEnd;
    }

}
