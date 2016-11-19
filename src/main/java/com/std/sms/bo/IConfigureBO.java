package com.std.sms.bo;

import com.std.sms.bo.base.IPaginableBO;
import com.std.sms.domain.Configure;

public interface IConfigureBO extends IPaginableBO<Configure> {
    /** 
     * 查询单条公司配置信息
     * @param data
     * @return 
     * @create: 2016年7月8日 上午10:49:51 zuixian
     * @history: 
     */
    public Configure queryConfigure(Configure data);
}
