package com.std.sms.ao;

import java.util.Date;
import java.util.List;

import com.std.sms.domain.Pool;

public interface IPoolAO {

    /** 
     * @param mobile
     * @param content
     * @param channel
     * @param sendDatetime
     * @return 
     * @create: 2016年7月7日 下午8:07:38 zuixian
     * @history: 
     */
    public void doSaveSOutToPool(String mobile, String content, String channel,
            Date sendDatetime);

    /** 
     * @param code 
     * @create: 2016年7月7日 下午8:07:41 zuixian
     * @history: 
     */
    public void doRemoveSOutFromPool(String code);

    /** 
     * @param code 
     * @create: 2016年7月7日 下午8:37:45 zuixian
     * @history: 
     */
    public List<Pool> queryPoolList(String code);
}
