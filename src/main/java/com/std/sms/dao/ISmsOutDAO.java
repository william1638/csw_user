package com.std.sms.dao;

import com.std.sms.dao.base.IBaseDAO;
import com.std.sms.domain.SmsOut;

public interface ISmsOutDAO extends IBaseDAO<SmsOut> {
    String NAMESPACE = ISmsOutDAO.class.getName().concat(".");
}
