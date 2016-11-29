package com.std.sms.dto.req;

public class XN804050Req {
    // from系统编号(必填)
    private String fromSystemCode;

    // to系统编号(必填)
    private String toSystemCode;

    // to手机号(必填)
    private String toMobile;

    // 办件状态(必填)（0 待处理 1 正在处理 2 已完成）
    private String status;

    public String getFromSystemCode() {
        return fromSystemCode;
    }

    public void setFromSystemCode(String fromSystemCode) {
        this.fromSystemCode = fromSystemCode;
    }

    public String getToSystemCode() {
        return toSystemCode;
    }

    public void setToSystemCode(String toSystemCode) {
        this.toSystemCode = toSystemCode;
    }

    public String getToMobile() {
        return toMobile;
    }

    public void setToMobile(String toMobile) {
        this.toMobile = toMobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
