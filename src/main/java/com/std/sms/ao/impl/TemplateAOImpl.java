package com.std.sms.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.sms.ao.ITemplateAO;
import com.std.sms.bo.ISystemChannelBO;
import com.std.sms.common.PropertiesUtil;
import com.std.sms.domain.SystemChannel;
import com.std.sms.enums.EChannelType;
import com.std.sms.enums.EPushType;
import com.std.sms.sent.wechat.Template;
import com.std.sms.sent.wechat.WeChatClientSend;

@Service
public class TemplateAOImpl implements ITemplateAO {
    @Autowired
    private ISystemChannelBO systemChannelBO;

    /** 
     * @see com.std.sms.ao.ITemplateAO#getTemplate()
     */
    @Override
    public Template getTemplate(String systemCode) {
        Template data = null;
        SystemChannel systemChannel = systemChannelBO
            .getSystemChannelByCondition(systemCode, EChannelType.WECHAT,
                EPushType.WEIXIN);
        List<Template> list = WeChatClientSend.getTemplateList(
            systemChannel.getPrivateKey1(), systemChannel.getPrivateKey2());
        for (Template template : list) {
            if (PropertiesUtil.Config.TEMPLATE_ID.equals(template
                .getTemplate_id())) {
                data = template;
                break;
            }
        }
        return data;
    }
}
