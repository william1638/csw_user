package com.std.sms.ao;

import com.std.sms.domain.Configure;

public interface IConfigureAO {
    /** 
     * @param companyCode
     * @return 
     * @create: 2016年7月8日 上午10:53:28 zuixian
     * @history: 
     */
    public Configure doGetConfigure(String companyCode, String channel,
            String key);
}
