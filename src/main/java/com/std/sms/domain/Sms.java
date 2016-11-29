package com.std.sms.domain;

import java.util.Date;
import java.util.Map;

import com.std.sms.dao.base.ABaseDO;

/**
* 消息
* @author: xieyj 
* @since: 2016年11月20日 16:03:07
* @history:
*/
public class Sms extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 序号
    private Long id;

    // from系统编号
    private String fromSystemCode;

    // from系统名称
    private String fromSystemName;

    // 渠道大类(0 全渠道 1短信 2 APP 3 微信 4 公告)
    private String channelType;

    // 渠道小类(11 创世漫道 12 汇禾 21 极光推送 31 微信 41 公告)
    private String pushType;

    // to系统编号
    private String toSystemCode;

    // to手机号
    private String toMobile;

    // 消息类型（1 即时发 2定时发）
    private String smsType;

    // 消息标题
    private String smsTitle;

    // 消息内容
    private String smsContent;

    // 状态（0 未发送，1 已发送）
    private String status;

    // 生成时间
    private Date createDatetime;

    // 拟发送时间
    private Date topushDatetime;

    // 发送时间
    private Date pushedDatetime;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ****************db properties ***********************

    // 消息内容
    private Map<String, String> wxSmsContent;

    public Map<String, String> getWxSmsContent() {
        return wxSmsContent;
    }

    public void setWxSmsContent(Map<String, String> wxSmsContent) {
        this.wxSmsContent = wxSmsContent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Long getId() {
        return id;
    }

    public void setFromSystemCode(String fromSystemCode) {
        this.fromSystemCode = fromSystemCode;
    }

    public String getFromSystemCode() {
        return fromSystemCode;
    }

    public void setFromSystemName(String fromSystemName) {
        this.fromSystemName = fromSystemName;
    }

    public String getFromSystemName() {
        return fromSystemName;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public String getPushType() {
        return pushType;
    }

    public void setToSystemCode(String toSystemCode) {
        this.toSystemCode = toSystemCode;
    }

    public String getToSystemCode() {
        return toSystemCode;
    }

    public void setToMobile(String toMobile) {
        this.toMobile = toMobile;
    }

    public String getToMobile() {
        return toMobile;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsTitle(String smsTitle) {
        this.smsTitle = smsTitle;
    }

    public String getSmsTitle() {
        return smsTitle;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getTopushDatetime() {
        return topushDatetime;
    }

    public void setTopushDatetime(Date topushDatetime) {
        this.topushDatetime = topushDatetime;
    }

    public Date getPushedDatetime() {
        return pushedDatetime;
    }

    public void setPushedDatetime(Date pushedDatetime) {
        this.pushedDatetime = pushedDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }
}
