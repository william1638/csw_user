package com.std.sms.sent.jiguang;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.std.sms.common.PropertiesUtil;
import com.std.sms.enums.EOpenType;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JPushClientSend {
    protected static final Logger LOG = LoggerFactory
        .getLogger(JPushClient.class);

    public static boolean toSendPush(String appKey, String masterSecret,
            String noticeInfo,String title,String openType,String postCode,String postTitle) {
        return toSendPush(appKey, masterSecret, null,null, noticeInfo, title,openType,postCode,postTitle);
    }

    public static boolean toSendPush(String appKey, String masterSecret,
            String alias,String tag, String noticeInfo,String title,String openType,String postCode,String postTitle) {
        boolean res = false;
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null,
            clientConfig);
        // 生产环境apnsProduction传入true，研发传false
        Boolean apnsProduction = false;
        if ("1".equals(PropertiesUtil.Config.APNS_PRODUCTION)) {
            apnsProduction = true;
        }
        PushPayload payload = null;
        // 全渠道发或者单发
        if (StringUtils.isNotBlank(alias)) {
        	
        	if(StringUtils.isBlank(openType)||EOpenType.CONTENT.getCode().equals(openType)){     		
        		payload = buildPushObject_android_and_ios_alert(title,noticeInfo,apnsProduction,EOpenType.CONTENT.getCode(),alias);
        	}else if(EOpenType.URL.getCode().equals(openType)){
        		//把帖子标题作为 通知栏显示文本
        		payload = buildPushObject_android_and_ios_alert_post(title,noticeInfo,apnsProduction,openType,postCode,postTitle,alias);
        	}
               
        }else if(StringUtils.isNotBlank(tag)){
        	if(StringUtils.isBlank(openType)||EOpenType.CONTENT.getCode().equals(openType)){     		
        		payload = buildPushObject_android_and_ios_tag_alert(title,noticeInfo,
                        apnsProduction,EOpenType.CONTENT.getCode(),tag);
        	}else if(EOpenType.URL.getCode().equals(openType)){
        		payload = buildPushObject_android_and_ios_tag_alert_post(title,noticeInfo,
                        apnsProduction,openType,postCode,postTitle,tag);
        	}
        	
        }else {
        	if(StringUtils.isBlank(openType)||EOpenType.CONTENT.getCode().equals(openType)){     		
        		 payload = buildPushObject_android_and_ios_alert(noticeInfo,
        	                apnsProduction,EOpenType.CONTENT.getCode());
        	}else if(EOpenType.URL.getCode().equals(openType)){
        		payload = buildPushObject_android_and_ios_alert_post(title,noticeInfo,
    	                apnsProduction,openType,postCode,postTitle);
        	}
            
        }
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("responseCode:" + result.getResponseCode());
            LOG.info("Got result - " + result);
            if (200 == result.getResponseCode()) {
                res = true;
            }
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error(
                "Error response from JPush server. Should review and fix it. ",
                e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        } catch (Exception e) {
            LOG.info("Error Message: " + e.getMessage());
        }
        return res;
    }

    
    public static PushPayload buildPushObject_all_all_alert(String alert) {
        return PushPayload.alertAll(alert);
    }

    public static PushPayload buildPushObject_android_and_ios_alert(
            String ALERT, boolean APNS_PRODUCTION,String openType) {
        return PushPayload
            .newBuilder()
            .setPlatform(Platform.all())
            .setAudience(Audience.all())
            .setNotification(
                Notification
                    .newBuilder()
                    .addPlatformNotification(
                        AndroidNotification.newBuilder().setAlert(ALERT).addExtra("openType",openType)
                            .build())
                    .addPlatformNotification(
                        IosNotification.newBuilder().setAlert(ALERT).addExtra("openType",openType)
                            .setBadge(5).setSound("happy").build()).build())
            .setOptions(
                Options.newBuilder().setApnsProduction(APNS_PRODUCTION).build())
            .build();
    }
    
    public static PushPayload buildPushObject_android_and_ios_alert_post(
            String title,String ALERT, boolean APNS_PRODUCTION,String openType,String postCode,String postTitle) {
        return PushPayload
            .newBuilder()
            .setPlatform(Platform.all())
            .setAudience(Audience.all())
            .setNotification(
                Notification
                    .newBuilder()
                    .addPlatformNotification(
                        AndroidNotification.newBuilder().setAlert(postTitle).setTitle(title).addExtra("openType", openType).addExtra("code", postCode)
                            .build())
                    .addPlatformNotification(
                        IosNotification.newBuilder().setAlert(postTitle).addExtra("openType", openType).addExtra("code", postCode)
                            .setBadge(5).setSound("happy").build()).build())
            .setOptions(
                Options.newBuilder().setApnsProduction(APNS_PRODUCTION).build())
            .build();
    }


    public static PushPayload buildPushObject_android_and_ios_alert(String title,
            String ALERT, boolean APNS_PRODUCTION,String openType,String... alias1) {
        return PushPayload
            .newBuilder()
            .setPlatform(Platform.all())
            .setAudience(
                Audience.newBuilder()
                    .addAudienceTarget(AudienceTarget.alias(alias1)).build())
            .setNotification(
                Notification
                    .newBuilder()
                    .addPlatformNotification(
                        AndroidNotification.newBuilder().setAlert(ALERT).setTitle(title).addExtra("openType",openType)
                            .build())
                    .addPlatformNotification(
                        IosNotification.newBuilder().setAlert(ALERT).addExtra("openType",openType)
                            .setBadge(5).setSound("happy").build()).build())
            .setOptions(
                Options.newBuilder().setApnsProduction(APNS_PRODUCTION).build())
            .build();
    }
    
    public static PushPayload buildPushObject_android_and_ios_alert_post(String title,
            String ALERT, boolean APNS_PRODUCTION,String openType,String postCode,String postTitle,String... alias1) {
        return PushPayload
            .newBuilder()
            .setPlatform(Platform.all())
            .setAudience(
                Audience.newBuilder()
                    .addAudienceTarget(AudienceTarget.alias(alias1)).build())
            .setNotification(
                Notification
                    .newBuilder()
                    .addPlatformNotification(
                        AndroidNotification.newBuilder().setAlert(postTitle).setTitle(title).addExtra("openType",openType).addExtra("code", postCode)
                            .build())
                    .addPlatformNotification(
                        IosNotification.newBuilder().setAlert(postTitle).addExtra("openType",openType).addExtra("code", postCode)
                            .setBadge(5).setSound("happy").build()).build())
            .setOptions(
                Options.newBuilder().setApnsProduction(APNS_PRODUCTION).build())
            .build();
    }
    
    
    //tag推送
    public static PushPayload buildPushObject_android_and_ios_tag_alert(
    		String title,String ALERT, boolean APNS_PRODUCTION,String openType, String... tag1) {
    	return PushPayload
                .newBuilder()
                .setPlatform(Platform.all())
                .setAudience(
                    Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag(tag1)).build())
                .setNotification(
                    Notification
                        .newBuilder()
                        .addPlatformNotification(
                            AndroidNotification.newBuilder().setAlert(ALERT).setTitle(title).addExtra("openType",openType)
                                .build())
                        .addPlatformNotification(
                            IosNotification.newBuilder().setAlert(ALERT).addExtra("openType",openType)
                                .setBadge(5).setSound("happy").build()).build())
                .setOptions(
                        Options.newBuilder().setApnsProduction(APNS_PRODUCTION).build())
                    .build();
    }
    
    //tag推送
    public static PushPayload buildPushObject_android_and_ios_tag_alert_post(
    		String title,String ALERT, boolean APNS_PRODUCTION,String openType,String postCode,String postTitle, String... tag1) {
    	return PushPayload
                .newBuilder()
                .setPlatform(Platform.all())
                .setAudience(
                    Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag(tag1)).build())
                .setNotification(
                    Notification
                        .newBuilder()
                        .addPlatformNotification(
                            AndroidNotification.newBuilder().setAlert(postTitle).setTitle(title).addExtra("openType",openType).addExtra("code", postCode)
                                .build())
                        .addPlatformNotification(
                            IosNotification.newBuilder().setAlert(postTitle).addExtra("openType",openType).addExtra("code", postCode)
                                .setBadge(5).setSound("happy").build()).build())
                .setOptions(
                        Options.newBuilder().setApnsProduction(APNS_PRODUCTION).build())
                    .build();
    }
    
    
    
    
    
    
    

    
}
