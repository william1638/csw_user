package com.std.sms.ao;

public interface ISmsAO {
    /**
     * 发送短信
     * @param mobile
     * @param content
     * @param bizType
     * @return 
     * @create: 2016年1月14日 下午2:52:08 myb858
     * @history:
     */
    public boolean doSend(String mobile, String content, String bizType);

    /**
     * 发送短信日志
     * @param moible
     * @param content
     * @param bizType
     * @param remark
     * @param flag
     * @return 
     * @create: 2015年12月6日 下午6:49:54 myb858
     * @history:
     */
    public Long doSaveSmsOut(String moible, String content, String bizType,
            String remark, boolean flag);

}
