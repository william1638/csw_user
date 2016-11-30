package com.std.sms.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.sms.bo.ITemplateBO;
import com.std.sms.common.PropertiesUtil;
import com.std.sms.dao.ISystemChannelDAO;
import com.std.sms.domain.SystemChannel;
import com.std.sms.enums.EChannelType;
import com.std.sms.enums.EPushType;
import com.std.sms.sent.wechat.Template;
import com.std.sms.sent.wechat.WeChatClientSend;

@Component
public class TemplateBOImpl implements ITemplateBO {

    @Autowired
    private ISystemChannelDAO systemChannelDAO;

    /** 
     * @see com.std.sms.bo.ITemplateBO#getTemplate(java.lang.String)
     */
    @Override
    public Template getTemplate(String systemCode) {
        SystemChannel condition = new SystemChannel();
        condition.setSystemCode(systemCode);
        condition.setChannelType(EChannelType.WECHAT.getCode());
        condition.setPushType(EPushType.WEIXIN.getCode());
        SystemChannel systemChannel = systemChannelDAO.select(condition);
        List<Template> list = WeChatClientSend.getTemplateList(
            systemChannel.getPrivateKey1(), systemChannel.getPrivateKey2());
        Template data = null;
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
