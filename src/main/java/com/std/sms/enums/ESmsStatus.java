/**
 * @Title Status.java 
 * @Package com.ibis.pz.enums 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 上午8:41:50 
 * @version V1.0   
 */
package com.std.sms.enums;

/** 
 * @author: miyb 
 * @since: 2015-3-7 上午8:41:50 
 * @history:
 */
public enum ESmsStatus {
    // 0 待发送 1 已发送
    TOSEND("0", "待发送"), SENT_YES("1", "发送成功"), SENT_NO("2", "发送失败");

    ESmsStatus(String code, String value) {
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
