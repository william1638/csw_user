package com.std.sms.bo;

import com.std.sms.sent.wechat.Template;

public interface ITemplateBO {

    public Template getTemplate(String systemCode);
}
