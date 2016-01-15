package com.std.sms.sent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.std.sms.enums.EServerType;
import com.std.sms.exception.BizException;
import com.std.sms.sent.hhxx.SmsClientSend;

/**
 * 发短信 返回信息详见文档。
 * 
 * @author Administrator
 * 
 */
@Component
public class Senter {

    private static Properties props = new Properties();
    static {
        try {
            props.load(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("config.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String content, String mobileNumber) throws BizException {
        String senterType = props.getProperty("senterType");
        if (EServerType.CSMD.getCode().equalsIgnoreCase(senterType)) {
            sendByCSMD(content, mobileNumber);
        } else if (EServerType.HHXX.getCode().equalsIgnoreCase(senterType)) {
            sendByHHXX(content, mobileNumber);
        } else {
            throw new BizException("xn709901", "短信配置信息，senterType未定义");
        }

    }

    private void sendByHHXX(String content, String mobileNumber)
            throws BizException {
        String userid = props.getProperty("hhxx_userid");
        String account = props.getProperty("hhxx_account");
        String password = props.getProperty("hhxx_password");
        if (userid == null || account == null || password == null) {
            throw new BizException("xn709901",
                "短信发送失败，userid或account或password未定义");
        }
        try {
            String url = "http://118.145.18.144:5888/sms.aspx";
            String res = SmsClientSend.sendSms(url, userid, account, password,
                mobileNumber, content);
            // 发送短信，如果是以负号开头就是发送失败。
            if (!res.contains("ok")) {
                throw new BizException("xn709901", "短信发送失败，错误代码：" + res);
            }
        } catch (Exception e) {
            throw new BizException("xn709901", "汇禾信息发送短信未知错误");
        }
    }

    private void sendByCSMD(String content, String mobileNumber)
            throws BizException {
        String sn = props.getProperty("csmd_sn");
        String pwd = props.getProperty("csmd_password");
        if (sn == null || pwd == null) {
            throw new BizException("xn709901", "短信发送失败，sn或password未定义");
        }
        try {
            content = URLEncoder.encode(content, "utf-8");
            CsmdWebServiceClient client = new CsmdWebServiceClient(sn, pwd);
            String result_mt = client.mdSmsSend_u(mobileNumber, content, "",
                "", "");
            // 发送短信，如果是以负号开头就是发送失败。
            if (result_mt.startsWith("-") || result_mt.equals("")) {
                throw new BizException("xn709901", "短信发送失败，错误代码：" + result_mt);
            }
        } catch (UnsupportedEncodingException e) {
            throw new BizException("xn709901", "创世漫道发送短信未知错误");
        }
    }

    // public static void main(String[] args) {
    // new Senter().send("【个金所】尊敬的用户,您的验证码是678987 ,请妥善保留", "13958092437");
    // }
}
