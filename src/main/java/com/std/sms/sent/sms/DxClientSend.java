package com.std.sms.sent.sms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.std.sms.exception.BizException;
import com.std.sms.sent.csmd.CsmdWebServiceClient;
import com.std.sms.sent.hhxx.SmsClientSend;

/**
 * @author: xieyj 
 * @since: 2016年11月27日 下午2:41:08 
 * @history:
 */
@Component
public class DxClientSend {
    protected static final Logger logger = LoggerFactory
        .getLogger(DxClientSend.class);

    public static boolean sendByHHXX(String userid, String account,
            String password, String mobile, String content) throws BizException {
        boolean result = false;
        if (StringUtils.isBlank(userid) || StringUtils.isBlank(account)
                || StringUtils.isBlank(password)) {
            logger.error("短信发送失败，userid或account或password未定义");
            return result;
        }
        try {
            String url = "http://118.145.18.144:5888/sms.aspx";
            String res = SmsClientSend.sendSms(url, userid, account, password,
                mobile, content);
            // 发送短信，如果是以负号开头就是发送失败。
            if (!res.contains("ok")) {
                logger.error("xn709901", "短信发送失败，错误代码：" + res);
            } else {
                result = true;
            }
        } catch (Exception e) {
            logger.error("xn709901", "汇禾信息发送短信未知错误", e);
        }
        return result;
    }

    public static boolean sendByCSMD(String sn, String password, String mobile,
            String content) throws BizException {
        boolean result = false;
        if (StringUtils.isBlank(sn) || StringUtils.isBlank(password)) {
            logger.error("短信发送失败，sn或password未定义");
            return result;
        }
        try {
            content = URLEncoder.encode(content, "utf-8");
            CsmdWebServiceClient client = new CsmdWebServiceClient(sn, password);
            String result_mt = client.mdSmsSend_u(mobile, content, "", "", "");
            // 发送短信，如果是以负号开头就是发送失败。
            if (result_mt.startsWith("-") || result_mt.equals("")) {
                logger.error("短信发送失败，错误代码：" + result_mt);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("创世漫道发送短信未知错误", e);
        }
        return result;
    }

    public static void main(String[] args) {
        // new Senter().send("XN1001", "CSMD",
        // "【雄牛科技】尊敬的用户,您的验证码是678987 ,请妥善保留",
        // "15088750712");
    }
}
