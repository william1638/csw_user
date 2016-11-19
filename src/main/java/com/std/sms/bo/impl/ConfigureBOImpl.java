package com.std.sms.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.sms.bo.IConfigureBO;
import com.std.sms.bo.base.PaginableBOImpl;
import com.std.sms.dao.IConfigureDAO;
import com.std.sms.domain.Configure;

@Component
public class ConfigureBOImpl extends PaginableBOImpl<Configure> implements
        IConfigureBO {

    @Autowired
    private IConfigureDAO configureDAO;

    @Override
    public Configure queryConfigure(Configure data) {
        return configureDAO.select(data);
    }
}
