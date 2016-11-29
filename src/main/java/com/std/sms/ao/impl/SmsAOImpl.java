package com.std.sms.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.sms.ao.ISmsAO;
import com.std.sms.bo.IReceiverBO;
import com.std.sms.bo.ISmsBO;
import com.std.sms.bo.ISystemChannelBO;
import com.std.sms.bo.base.Paginable;
import com.std.sms.common.JsonUtil;
import com.std.sms.common.PropertiesUtil;
import com.std.sms.domain.Receiver;
import com.std.sms.domain.Sms;
import com.std.sms.domain.SystemChannel;
import com.std.sms.enums.EBoolean;
import com.std.sms.enums.EChannelType;
import com.std.sms.enums.EPushType;
import com.std.sms.enums.ESmsStatus;
import com.std.sms.enums.ESmsType;
import com.std.sms.exception.BizException;
import com.std.sms.sent.jiguang.JPushClientSend;
import com.std.sms.sent.sms.DxClientSend;
import com.std.sms.sent.wechat.WeChatClientSend;
import com.std.sms.sent.wechat.WxTemplate;

@Service
public class SmsAOImpl implements ISmsAO {

    @Autowired
    private ISmsBO smsBO;

    @Autowired
    private ISystemChannelBO systemChannelBO;

    @Autowired
    private IReceiverBO receiverBO;

    @Override
    public void toSendDxSms(Sms data) {
        String mobile = data.getToMobile();
        String systemCode = data.getToSystemCode();
        String content = data.getSmsContent();
        String status = ESmsStatus.TOSEND.getCode();
        if (ESmsType.NOW_SEND.getCode().equals(data.getSmsType())) {
            if (StringUtils.isNotBlank(mobile)) {
                boolean result = sendSms(systemCode, mobile, content,
                    data.getPushType());
                if (result) {
                    status = ESmsStatus.SENT_YES.getCode();
                } else {
                    status = ESmsStatus.SENT_NO.getCode();
                }
                data.setStatus(status);
                smsBO.saveSms(data);
            } else {
                Receiver condition = new Receiver();
                condition.setSystemCode(systemCode);
                List<Receiver> receiverList = receiverBO
                    .queryReceiverList(condition);
                if (CollectionUtils.isNotEmpty(receiverList)) {
                    for (Receiver receiver : receiverList) {
                        boolean result = sendSms(systemCode,
                            receiver.getMobile(), content, data.getPushType());
                        if (result) {
                            status = ESmsStatus.SENT_YES.getCode();
                        } else {
                            status = ESmsStatus.SENT_NO.getCode();
                        }
                        data.setStatus(status);
                        smsBO.saveSms(data);
                    }
                }
            }
        }
    }

    private boolean sendSms(String systemCode, String mobile, String content,
            String pushType) {
        boolean result = true;
        SystemChannel smsSc = systemChannelBO.getSystemChannelByCondition(
            systemCode, EChannelType.SMS, EPushType.CSMD);
        content = "【" + smsSc.getRemark() + "】" + content;
        if (EPushType.CSMD.getCode().equals(pushType)) {
            result = DxClientSend.sendByCSMD(smsSc.getPrivateKey1(),
                smsSc.getPrivateKey2(), mobile, content);
        } else if (EPushType.HHXX.getCode().equals(pushType)) {
            result = DxClientSend
                .sendByHHXX(smsSc.getPrivateKey1(), smsSc.getPrivateKey2(),
                    smsSc.getPrivateKey3(), mobile, content);
        }
        return result;
    }

    @Override
    public void toSendJgSms(Sms data) {
        boolean result = true;
        String mobile = data.getToMobile();
        String systemCode = data.getToSystemCode();
        String content = data.getSmsContent();
        String status = ESmsStatus.TOSEND.getCode();
        if (ESmsType.NOW_SEND.getCode().equals(data.getSmsType())) {
            SystemChannel jgSc = systemChannelBO.getSystemChannelByCondition(
                systemCode, EChannelType.APP, EPushType.JIGUANG);
            if (StringUtils.isNotBlank(mobile)) {
                Receiver receiver = receiverBO.getReceiver(mobile, systemCode);
                if (StringUtils.isNotBlank(receiver.getJpushId())) {
                    result = JPushClientSend.toSendPush(jgSc.getPushSystem(),
                        jgSc.getPrivateKey1(), receiver.getJpushId(), content);
                }
            } else {
                result = JPushClientSend.toSendPush(jgSc.getPushSystem(),
                    jgSc.getPrivateKey1(), content);
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

    @Override
    public void toSendWxSms(Sms data) {
        String mobile = data.getToMobile();
        String systemCode = data.getToSystemCode();
        if (StringUtils.isNotBlank(mobile)) {
            this.sendWeChatSingle(data);
        } else {
            Receiver condition = new Receiver();
            condition.setSystemCode(systemCode);
            List<Receiver> receiverList = receiverBO
                .queryReceiverList(condition);
            if (CollectionUtils.isNotEmpty(receiverList)) {
                for (Receiver receiver : receiverList) {
                    data.setToMobile(receiver.getMobile());
                    this.sendWeChatSingle(data);
                }
            }
        }
    }

    @Transactional
    private void sendWeChatSingle(Sms data) {
        // 填充内容
        Receiver receiver = receiverBO.getReceiver(data.getToMobile(),
            data.getToSystemCode());
        String weChatId = receiver.getWechatId();
        if (StringUtils.isNotBlank(weChatId)) {
            WxTemplate content = new WxTemplate(
                PropertiesUtil.Config.TEMPLATE_ID, weChatId,
                PropertiesUtil.Config.URL, data.getWxSmsContent());
            SystemChannel weChatSystemChannel = systemChannelBO
                .getSystemChannelByCondition(data.getToSystemCode(),
                    EChannelType.WECHAT, EPushType.WEIXIN);
            WeChatClientSend.sendWeChatSingle(data.getToSystemCode(),
                weChatSystemChannel.getPrivateKey1(),
                weChatSystemChannel.getPrivateKey2(),
                JsonUtil.Object2Json(content));
            smsBO.saveSms(data);
        }
    }

    @Override
    public void addNoticeSms(Sms data) {
        smsBO.saveSms(data);
    }

    @Override
    public void editNoticeSms(Sms data) {
        Sms sms = smsBO.getSms(data.getId());
        if (ESmsStatus.SENT_YES.getCode().equals(sms.getStatus())) {
            throw new BizException("xn702002", "公告已发布，无法修改");
        }
        smsBO.refreshSms(data);
    }

    /** 
     * @see com.std.sms.ao.ISmsAO#toSendNoticeSms(java.lang.Long, java.lang.String)
     */
    @Override
    public void toSendNoticeSms(Long id, String updater) {
        Sms sms = smsBO.getSms(id);
        if (ESmsStatus.SENT_NO.getCode().equals(sms.getStatus())) {
            throw new BizException("xn702002", "公告已下撤，无法发布");
        }
        String status = ESmsStatus.SENT_YES.getCode();
        if (ESmsStatus.SENT_YES.getCode().equals(sms.getStatus())) {
            status = ESmsStatus.SENT_NO.getCode();
        }
        smsBO.refreshSmsStatus(id, status, updater);
    }

    @Override
    public void toSendSms(Sms data) {
        String channelType = data.getChannelType();
        String pushType = data.getPushType();
        if (EBoolean.NO.getCode().equals(channelType)) {
            this.toSendDxSms(data);
            this.toSendJgSms(data);
            this.toSendWxSms(data);
        } else {
            if (ESmsType.NOW_SEND.getCode().equals(data.getSmsType())) {
                if (EPushType.CSMD.getCode().equals(pushType)
                        || EPushType.CSMD.getCode().equals(pushType)) {
                    this.toSendDxSms(data);
                } else if (EPushType.JIGUANG.getCode().equals(pushType)) {
                    this.toSendJgSms(data);
                } else if (EPushType.WEIXIN.getCode().equals(pushType)) {
                    this.toSendWxSms(data);
                }
            }
        }
    }

    @Override
    public void reSendSms(Sms data) {
    }

    @Override
    public void copySms(Long id) {
        Sms sms = smsBO.getSms(id);
        smsBO.saveSms(sms);
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
     * @see com.std.sms.ao.ISmsAO#doSmsDaily()
     */
    @Override
    public void doSmsDaily() {
    }
}
