/**
 * @Title PhoneUtils.java 
 * @Package com.hsnet.pz.utils 
 * @Description 
 * @author miyb  
 * @date 2014-8-19 下午3:39:39 
 * @version V1.0   
 */
package com.std.sms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/** 
 * @author: miyb 
 * @since: 2014-8-19 下午3:39:39 
 * @history:
 */
public class PhoneUtil {
    /** 
     * 手机号验证 
     * @param  mobile 
     * @return 验证通过返回true 
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
        Pattern p = Pattern.compile("^1[34578]\\d{9}$"); // 验证手机号
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

}
