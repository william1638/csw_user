package com.std.sms.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.sms.ao.IConfigureAO;
import com.std.sms.bo.IConfigureBO;
import com.std.sms.domain.Configure;

@Service
public class ConfigureAOImpl implements IConfigureAO {

    @Autowired
    private IConfigureBO configureBO;

    @Override
    public Configure doGetConfigure(String companyCode, String channel,
            String key) {
        Configure data = new Configure();
        data.setCompanyCode(companyCode);
        data.setChannel(channel);
        data.setKey(key);
        return configureBO.queryConfigure(data);
    }
}
