package com.std.sms.sent.wechat;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.std.sms.common.JsonUtil;
import com.std.sms.util.HttpsUtil;

/**
 * @author: xieyj 
 * @since: 2016年11月27日 下午2:41:08 
 * @history:
 */
@Component
public class WeChatClientSend {
    protected static final Logger logger = LoggerFactory
        .getLogger(WeChatClientSend.class);

    public static boolean sendWeChatSingle(String systemCode,
            String privateKey1, String privateKey2, String openId,
            String mobile, String content) {
        boolean result = false;
        String accessToken = getAccessToken(privateKey1, privateKey2);
        System.out.println("accessToken:" + accessToken);
        // 发送对接
        WxTemplate wxTemplate = JsonUtil.json2Bean(content, WxTemplate.class);
        wxTemplate.setTouser(openId);
        String postUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
                + accessToken;
        String response = null;
        try {
            response = new String(HttpsUtil.post(postUrl,
                JsonUtil.Object2Json(wxTemplate), "UTF-8"));
            WeChatSendResult weChatSendResult = JsonUtil.json2Bean(response,
                WeChatSendResult.class);
            if ("ok".equals(weChatSendResult.getErrmsg())) {
                result = true;
                logger.info("errcode:" + weChatSendResult.getErrcode()
                        + ";errmsg:" + weChatSendResult.getErrmsg());
            } else {
                logger.error("errcode:" + weChatSendResult.getErrcode()
                        + ";errmsg:" + weChatSendResult.getErrmsg());
            }
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    // 获取access_tokenId
    private static String getAccessToken(String privateKey1, String privateKey2) {
        String accessToken = null;
        String postUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + privateKey1 + "&secret=" + privateKey2;
        String response = null;
        try {
            response = new String(HttpsUtil.post(postUrl, "", "UTF-8"));
            TokenResponse tokenResponse = JsonUtil.json2Bean(response,
                TokenResponse.class);
            accessToken = tokenResponse.getAccess_token();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return accessToken;
    }

    public static void main(String[] args) {
        // new Senter().send("XN1001", "CSMD",
        // "【雄牛科技】尊敬的用户,您的验证码是678987 ,请妥善保留",
        // "15088750712");
    }
}
