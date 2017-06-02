package com.std.sms.enums;

import java.util.HashMap;
import java.util.Map;

public enum EOpenType {
    // 数据字典类型
    CONTENT("0", "内容"), URL("1", "帖子跳转");

    public static Map<String, EOpenType> getOpenTypeMap() {
        Map<String, EOpenType> map = new HashMap<String, EOpenType>();
        for (EOpenType dictType : EOpenType.values()) {
            map.put(dictType.getCode(), dictType);
        }
        return map;
    }

    EOpenType(String code, String value) {
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
