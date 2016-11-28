package com.std.sms.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.sms.ao.ISmsAO;
import com.std.sms.bo.IReceiverBO;
import com.std.sms.bo.ISmsBO;
import com.std.sms.bo.ISystemChannelBO;
import com.std.sms.bo.base.Paginable;
import com.std.sms.domain.Receiver;
import com.std.sms.domain.Sms;
import com.std.sms.domain.SystemChannel;
import com.std.sms.enums.EBoolean;
import com.std.sms.enums.EChannelType;
import com.std.sms.enums.EPushType;
import com.std.sms.enums.ESmsStatus;
import com.std.sms.enums.ESmsType;
import com.std.sms.sent.jiguang.JPushClientSend;
import com.std.sms.sent.sms.DxClientSend;
import com.std.sms.sent.wechat.WeChatClientSend;

@Service
public class SmsAOImpl implements ISmsAO {

    @Autowired
    private ISmsBO smsBO;

    @Autowired
    private ISystemChannelBO systemChannelBO;

    @Autowired
    private IReceiverBO receiverBO;

    @Override
    public void toSendSms(Sms data) {
        String systemCode = data.getToSystemCode();
        String channelType = data.getChannelType();
        String content = data.getSmsContent();
        String mobile = data.getToMobile();
        if (EBoolean.NO.getCode().equals(channelType)) {
            // 全渠道
            // 站内信
            // 极光
            // 微信
            // 短信
        } else {
            String status = ESmsStatus.TOSEND.getCode();
            if (ESmsType.NOW_SEND.getCode().equals(data.getSmsType())) {
                boolean result = true;
                // 创世漫道
                if (EPushType.CSMD.getCode().equals(data.getPushType())) {
                    this.sendCsmd(systemCode, mobile, content);
                    // 汇禾信息
                } else if (EPushType.HHXX.getCode().equals(data.getPushType())) {
                    this.sendHhxx(systemCode, mobile, content);
                    // 极光推送
                } else if (EPushType.JIGUANG.getCode().equals(
                    data.getPushType())) {
                    this.sendJPush(systemCode, mobile, content);
                } else if (EPushType.WEIXIN.getCode()
                    .equals(data.getPushType())) {
                    this.sendWeChat(systemCode, mobile, content);
                } else if (EPushType.NOTICE.getCode()
                    .equals(data.getPushType())) {
                    // 将数据插入阅读表
                }
                if (result) {
                    status = ESmsStatus.SENT_YES.getCode();
                } else {
                    status = ESmsStatus.SENT_NO.getCode();
                }
                data.setStatus(status);
            }
            smsBO.saveSms(data);
        }
    }

    @Override
    public void reSendSms(Sms data) {
        smsBO.refreshSms(data);
    }

    @Override
    public Paginable<Sms> querySmsPage(int start, int limit, Sms condition) {
        return smsBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Sms> querySmsList(Sms condition) {
        return smsBO.querySmsList(condition);
    }

    @Override
    public Sms getSms(Long id) {
        return smsBO.getSms(id);
    }

    /** 
     * @see com.std.sms.ao.ISmsAO#copySms(java.lang.Long)
     */
    @Override
    public void copySms(Long id) {
        Sms sms = smsBO.getSms(id);
        smsBO.saveSms(sms);
    }

    /** 
     * @see com.std.sms.ao.ISmsAO#doSmsDaily()
     */
    @Override
    public void doSmsDaily() {
    }

    private void sendCsmd(String systemCode, String mobile, String content) {
        SystemChannel smsSc = systemChannelBO.getSystemChannelByCondition(
            systemCode, EChannelType.SMS, EPushType.CSMD);
        content = "【" + smsSc.getRemark() + "】" + content;
        if (StringUtils.isNotBlank(mobile)) {
            // 单发
            receiverBO.getReceiver(mobile, systemCode);
            DxClientSend.sendByCSMD(smsSc.getPrivateKey1(),
                smsSc.getPrivateKey2(), mobile, content);
        } else {
            // 群发
            Receiver condition = new Receiver();
            condition.setSystemCode(systemCode);
            List<Receiver> receiverList = receiverBO
                .queryReceiverList(condition);
            if (CollectionUtils.isNotEmpty(receiverList)) {
                for (Receiver receiver : receiverList) {
                    DxClientSend.sendByCSMD(smsSc.getPushSystem(),
                        smsSc.getPrivateKey1(), receiver.getMobile(), content);
                }
            }
        }
    }

    private void sendHhxx(String systemCode, String mobile, String content) {
        SystemChannel smsSc = systemChannelBO.getSystemChannelByCondition(
            systemCode, EChannelType.SMS, EPushType.HHXX);
        content = "【" + smsSc.getRemark() + "】" + content;
        if (StringUtils.isNotBlank(mobile)) {
            // 单发
            DxClientSend
                .sendByHHXX(smsSc.getPrivateKey1(), smsSc.getPrivateKey2(),
                    smsSc.getPrivateKey3(), mobile, content);
        } else {
            // 群发
            Receiver condition = new Receiver();
            condition.setSystemCode(systemCode);
            List<Receiver> receiverList = receiverBO
                .queryReceiverList(condition);
            if (CollectionUtils.isNotEmpty(receiverList)) {
                for (Receiver receiver : receiverList) {
                    DxClientSend.sendByHHXX(smsSc.getPushSystem(),
                        smsSc.getPrivateKey1(), smsSc.getPrivateKey2(),
                        receiver.getMobile(), content);
                }
            }
        }
    }

    private void sendJPush(String systemCode, String mobile, String content) {
        SystemChannel jgSc = systemChannelBO.getSystemChannelByCondition(
            systemCode, EChannelType.APP, EPushType.JIGUANG);
        if (StringUtils.isNotBlank(mobile)) {
            Receiver receiver = receiverBO.getReceiver(mobile, systemCode);
            JPushClientSend.toSendPush(jgSc.getPushSystem(),
                jgSc.getPrivateKey1(), receiver.getJpushId(), content);
        } else {
            JPushClientSend.toSendPush(jgSc.getPushSystem(),
                jgSc.getPrivateKey1(), content);
        }
    }

    private void sendWeChat(String systemCode, String mobile, String content) {
        SystemChannel weChatSystemChannel = systemChannelBO
            .getSystemChannelByCondition(systemCode, EChannelType.WECHAT,
                EPushType.WEIXIN);
        Receiver receiver = receiverBO.getReceiver(mobile, systemCode);
        WeChatClientSend.sendWeChatSingle(systemCode,
            weChatSystemChannel.getPrivateKey1(),
            weChatSystemChannel.getPrivateKey2(), receiver.getWechatId(),
            mobile, content);
    }
}
