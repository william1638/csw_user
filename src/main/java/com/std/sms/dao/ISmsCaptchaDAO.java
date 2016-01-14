package com.std.sms.dao;

import com.std.sms.dao.base.IBaseDAO;
import com.std.sms.domain.SmsCaptcha;

/**
 * @author: xieyj 
 * @since: 2015-3-7 下午2:01:11 
 * @history:
 */
public interface ISmsCaptchaDAO extends IBaseDAO<SmsCaptcha> {
    String NAMESPACE = ISmsCaptchaDAO.class.getName().concat(".");

    public int updateCheckInfo(SmsCaptcha data);
}
