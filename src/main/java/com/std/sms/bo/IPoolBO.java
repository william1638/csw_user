package com.std.sms.bo;

import java.util.List;

import com.std.sms.bo.base.IPaginableBO;
import com.std.sms.domain.Pool;

public interface IPoolBO extends IPaginableBO<Pool> {

    /** 
     * @param data
     * @return 
     * @create: 2016年7月7日 下午7:54:03 zuixian
     * @history: 
     */
    public String savePool(String channel, String mobile, String content,
            String sendDatetime);

    /** 
     * @param data
     * @return 
     * @create: 2016年7月7日 下午7:56:23 zuixian
     * @history: 
     */
    public int removePool(String code);

    /** 
     * @param data
     * @return 
     * @create: 2016年7月7日 下午7:54:18 zuixian
     * @history: 
     */
    public List<Pool> queryPoolList(Pool data);
}
