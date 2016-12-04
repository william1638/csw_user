package com.std.sms.domain;

import com.std.sms.dao.base.ABaseDO;

/**
* 系统消息模板
* @author: xieyj 
* @since: 2016年12月04日 09:41:48
* @history:
*/
public class SystemTemplate extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 序号
    private Long id;

    // 系统编号
    private String systemCode;

    // 模板编号
    private String templateId;

    // URL
    private String url;

    // 字体样式1
    private String color1;

    // 字体样式2
    private String color2;

    // 内容样式
    private String content;

    // 备注
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor2() {
        return color2;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }
}
