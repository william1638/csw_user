package com.std.sms.sent.wechat;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.std.sms.bo.ISystemChannelBO;
import com.std.sms.common.JsonUtil;
import com.std.sms.domain.SystemChannel;
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

    @Autowired
    private ISystemChannelBO systemChannelBO;

    public boolean sendWeChatSingle(SystemChannel systemChannel, String content) {
        boolean result = false;
        // Long id = systemChannel.getId();
        String privateKey1 = systemChannel.getPrivateKey1();
        String privateKey2 = systemChannel.getPrivateKey2();
        String accessToken = getAccessToken(privateKey1, privateKey2);
        // 请求链接
        String prefixPostUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
        // 发送内容
        WxTemplate wxTemplate = JsonUtil.json2Bean(content, WxTemplate.class);
        // // token获取，并组装
        // if (StringUtils.isBlank(accessToken)) {
        // accessToken = getAccessToken(privateKey1, privateKey2);
        // // systemChannelBO.refreshSystemChannel(id, accessToken);
        // }
        String postUrl = prefixPostUrl + accessToken;
        WeChatSendResult weChatSendResult = sendWxContent(postUrl, wxTemplate);
        if ("0".equals(weChatSendResult.getErrcode())) {
            result = true;
        }
        // else if (EWxErrorCode.EXPIRE.getCode().equals(
        // weChatSendResult.getErrcode())) {
        // // 请求超时，重新获取，保存到数据库中
        // accessToken = getAccessToken(privateKey1, privateKey2);
        // // systemChannelBO.refreshSystemChannel(id, accessToken);
        // postUrl = prefixPostUrl + accessToken;
        // WeChatSendResult weChatResult = sendWxContent(postUrl, wxTemplate);
        // if ("ok".equals(weChatResult.getErrmsg())) {
        // result = true;
        // }
        // }
        return result;
    }

    // 获取模板
    public static List<Template> getTemplateList(String privateKey1,
            String privateKey2) {
        List<Template> result = null;
        String accessToken = getAccessToken(privateKey1, privateKey2);
        // 发送对接
        String postUrl = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token="
                + accessToken;
        String response = null;
        try {
            response = new String(HttpsUtil.post(postUrl, "", "UTF-8"));
            Gson gson = new Gson();
            if (StringUtils.isNotBlank(response)) {
                // 去除外面大类{}
                response = response.substring(response.indexOf("["),
                    response.lastIndexOf("}"));
            }
            result = gson.fromJson(response, new TypeToken<List<Template>>() {
            }.getType());
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

    public WeChatSendResult sendWxContent(String postUrl, WxTemplate wxTemplate) {
        WeChatSendResult weChatSendResult = null;
        // 发送微信请求
        String response = null;
        try {
            response = new String(HttpsUtil.post(postUrl,
                JsonUtil.Object2Json(wxTemplate), "UTF-8"));
            weChatSendResult = JsonUtil.json2Bean(response,
                WeChatSendResult.class);
            if ("ok".equals(weChatSendResult.getErrmsg())) {
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
        return weChatSendResult;
    }

    // 获取access_tokenId
    public static String getAccessToken(String privateKey1, String privateKey2) {
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
        String accessToken = getAccessToken("wxef7fda595f81f6d6",
            "057815f636178d3a81c3b065f395a209");
        String postUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="
                + accessToken;
        String result;
        try {
            result = new String(HttpsUtil.post(postUrl, "", "UTF-8"));
            System.out.println("result:" + result);
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
    }
}
