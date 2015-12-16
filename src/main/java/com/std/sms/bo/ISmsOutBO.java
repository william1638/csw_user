package com.std.sms.bo;

import java.util.List;

import com.std.sms.bo.base.IPaginableBO;
import com.std.sms.domain.SmsOut;

/**
 * 
 * @author: myb858 
 * @since: 2015年8月20日 下午2:33:00 
 * @history:
 */
public interface ISmsOutBO extends IPaginableBO<SmsOut> {
    /**
     * 
     * @param data
     * @return 
     * @create: 2015年8月20日 下午2:33:10 myb858
     * @history:
     */
    public int saveSmsOut(SmsOut data);

    /**
     * 
     * @param id
     * @return 
     * @create: 2015年8月20日 下午2:33:14 myb858
     * @history:
     */
    public int removeSmsOut(Long id);

    /**
     * 
     * @param id
     * @return 
     * @create: 2015年8月20日 下午2:33:41 myb858
     * @history:
     */
    public SmsOut getSmsOut(Long id);

    /**
     * 
     * @param data
     * @return 
     * @create: 2015年8月20日 下午2:33:47 myb858
     * @history:
     */
    public List<SmsOut> querySmsOutList(SmsOut data);
}
