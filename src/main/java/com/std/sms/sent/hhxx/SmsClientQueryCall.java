package com.std.sms.sent.hhxx;

import java.net.URLEncoder;

import com.std.sms.common.SmsClientAccessTool;

/**
 * <p>
 * <date>2012-03-01</date><br/>
 * <span>软维提供的JAVA接口信息（短信，彩信）调用API</span><br/>
 * <span>----------查询上行信息/用户回复------------</span>
 * </p>
 * @author: myb858 
 * @since: 2015年11月5日 下午4:28:54 
 * @history:
 */
public class SmsClientQueryCall {

    /**
     * 第五章 上行接口
     * 其一：发送方式，默认为POST<br/>
     * 其二：发送内容编码方式，默认为UTF-8
     * @param url：必填--发送连接地址URL--比如>http://118.145.30.35/callApi.aspx
     * @param userid：必填--用户ID，为数字
     * @param account：必填--用户帐号
     * @param password：必填--用户密码
     * @return 返回状态报告
     */
    public static String queryCall(String url, String userid,
            String account, String password) {

        try {
            StringBuffer sendParam = new StringBuffer();
            sendParam.append("action=query");
            sendParam.append("&userid=").append(userid);
            sendParam.append("&account=").append(
                URLEncoder.encode(account, "UTF-8"));
            sendParam.append("&password=").append(
                URLEncoder.encode(password, "UTF-8"));

            return SmsClientAccessTool.getInstance().doAccessHTTPPost(url,
                sendParam.toString(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "未发送，异常-->" + e.getMessage();
        }
    }
}
