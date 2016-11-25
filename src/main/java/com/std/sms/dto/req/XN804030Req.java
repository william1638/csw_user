package com.std.sms.dto.req;

import java.util.Date;

public class XN804030Req {
    // from系统编号(必填)
    private String fromSystemCode;

    // 渠道大类(必填)
    private String channelType;

    // 渠道小类(必填)
    private String pushType;

    // to系统编号(选填)
    private String toSystemCode;

    // to手机号(选填)
    private String toMobile;

    // 消息类型(必填)（1 即时发 2定时发）
    private String smsType;

    // 消息标题(选填)
    private String smsTitle;

    // 消息内容(必填)
    private String smsContent;

    // 拟发送时间(选填)
    private Date topushDatetime;

    // 备注(选填)
    private String remark;

    public String getFromSystemCode() {
        return fromSystemCode;
    }

    public void setFromSystemCode(String fromSystemCode) {
        this.fromSystemCode = fromSystemCode;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
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

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getSmsTitle() {
        return smsTitle;
    }

    public void setSmsTitle(String smsTitle) {
        this.smsTitle = smsTitle;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public Date getTopushDatetime() {
        return topushDatetime;
    }

    public void setTopushDatetime(Date topushDatetime) {
        this.topushDatetime = topushDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
