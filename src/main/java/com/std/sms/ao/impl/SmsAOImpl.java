package com.std.sms.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.sms.ao.ISmsAO;
import com.std.sms.bo.ISmsBO;
import com.std.sms.bo.ISystemChannelBO;
import com.std.sms.bo.base.Paginable;
import com.std.sms.domain.Sms;
import com.std.sms.domain.SystemChannel;
import com.std.sms.enums.EPushType;
import com.std.sms.enums.ESmsStatus;
import com.std.sms.enums.ESmsType;
import com.std.sms.exception.BizException;
import com.std.sms.sent.jiguang.JPushClientSend;

@Service
public class SmsAOImpl implements ISmsAO {

    @Autowired
    private ISmsBO smsBO;

    @Autowired
    private ISystemChannelBO systemChannelBO;

    @Override
    public void toSendSms(Sms data) {
        SystemChannel systemChannel = null;
        SystemChannel scCondition = new SystemChannel();
        scCondition.setSystemCode(data.getFromSystemCode());
        scCondition.setChannelType(data.getChannelType());
        scCondition.setPushType(data.getPushType());
        List<SystemChannel> list = systemChannelBO
            .querySystemChannelList(scCondition);
        if (CollectionUtils.isNotEmpty(list)) {
            systemChannel = list.get(0);
        } else {
            throw new BizException("xn000000", "指定系统渠道不存在");
        }
        String status = ESmsStatus.TOSEND.getCode();
        if (ESmsType.NOW_SEND.getCode().equals(data.getSmsType())) {
            boolean result = false;
            // 极光发送
            if (EPushType.JIGUANG.getCode().equals(data.getPushType())) {
                result = JPushClientSend.toSendPush(
                    systemChannel.getPushSystem(),
                    systemChannel.getPrivateKey1(), data.getSmsContent());
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
        // TODO Auto-generated method stub

    }
}
