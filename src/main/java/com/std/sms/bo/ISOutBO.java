package com.std.sms.bo;

import java.util.List;

import com.std.sms.bo.base.IPaginableBO;
import com.std.sms.domain.SOut;

public interface ISOutBO extends IPaginableBO<SOut> {

    /** 
     * @param data
     * @return 
     * @create: 2016年7月7日 下午5:36:39 zuixian
     * @history: 
     */
    public String saveSOut(String channel, String mobile, String content);

    /** 
     * @param data
     * @return 
     * @create: 2016年7月7日 下午5:37:39 zuixian
     * @history: 
     */
    public List<SOut> querySOutList(SOut data);
}
