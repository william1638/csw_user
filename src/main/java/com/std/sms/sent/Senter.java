package com.std.sms.sent;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.sms.ao.IConfigureAO;
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

    @Autowired
    private IConfigureAO configureAO;

    // private IConfigureAO configureAO = SpringContextHolder
    // .getBean(IConfigureAO.class);

    // private static Properties props = new Properties();
    // static {
    // try {
    // props.load(Thread.currentThread().getContextClassLoader()
    // .getResourceAsStream("config.properties"));
    // } catch (FileNotFoundException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    public void send(String companyCode, String channel, String mobileNumber,
            String content) throws BizException {
        String[] str = channel.split("-");
        if (str[1].equalsIgnoreCase("CSMD")) {
            sendByCSMD(companyCode, channel, content, mobileNumber);
        } else if (str[1].equalsIgnoreCase("HHXX")) {
            sendByHHXX(companyCode, channel, content, mobileNumber);
        } else {
            throw new BizException("xn709901", "短信配置信息，channel未定义");
        }
        // String senterType = props.getProperty("senterType");
        // if (EServerType.CSMD.getCode().equalsIgnoreCase(senterType)) {
        // sendByCSMD(content, mobileNumber);
        // } else if (EServerType.HHXX.getCode().equalsIgnoreCase(senterType)) {
        // sendByHHXX(content, mobileNumber);
        // } else {
        // throw new BizException("xn709901", "短信配置信息，senterType未定义");
        // }
    }

    private void sendByHHXX(String companyCode, String channel, String content,
            String mobileNumber) throws BizException {
        // String userid = props.getProperty("hhxx_userid");
        // String account = props.getProperty("hhxx_account");
        // String password = props.getProperty("hhxx_password");
        String userid = null;
        String account = null;
        String password = null;
        String[] str = channel.split("-");
        if (str[2].equalsIgnoreCase("K")) {
            userid = configureAO.doGetConfigure(companyCode, str[1],
                "hhxx_userid_1").getValue();
            account = configureAO.doGetConfigure(companyCode, str[1],
                "hhxx_account_1").getValue();
            password = configureAO.doGetConfigure(companyCode, str[1],
                "hhxx_password_1").getValue();
        } else {
            userid = configureAO.doGetConfigure(companyCode, str[1],
                "hhxx_userid_2").getValue();
            account = configureAO.doGetConfigure(companyCode, str[1],
                "hhxx_account_2").getValue();
            password = configureAO.doGetConfigure(companyCode, str[1],
                "hhxx_password_2").getValue();
        }
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

    private void sendByCSMD(String companyCode, String channel, String content,
            String mobileNumber) throws BizException {
        // String sn = props.getProperty("csmd_sn");
        // String pwd = props.getProperty("csmd_password");
        String[] str = channel.split("-");
        String sn = configureAO.doGetConfigure(companyCode, str[1], "csmd_sn")
            .getValue();
        String pwd = configureAO.doGetConfigure(companyCode, str[1],
            "csmd_password").getValue();
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

    public static void main(String[] args) {
        // new Senter().send("XN1001", "CSMD",
        // "【雄牛科技】尊敬的用户,您的验证码是678987 ,请妥善保留",
        // "15088750712");
    }
}
