package com.std.sms.enums;

public enum ESenterType {
    CSMD("0", "创世漫道短信服务商"), HHXX("1", "汇禾信息短信服务商");

    ESenterType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
