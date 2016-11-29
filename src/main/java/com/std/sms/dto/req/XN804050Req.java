package com.std.sms.dto.req;

public class XN804050Req {
    // 真实姓名(必填)
    private String realName;

    // 审批部门(必填)
    private String department;

    // 办理事项(必填)
    private String content;

    // 联系电话(必填)
    private String telephone;

    // 备注(选填)
    private String remark;

    // 用户编号(必填)
    private String userId;

    // 系统编号(必填)
    private String systemCode;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
}
