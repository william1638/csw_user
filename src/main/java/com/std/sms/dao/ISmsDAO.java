package com.std.sms.dao;

import java.util.List;

import com.std.sms.dao.base.IBaseDAO;
import com.std.sms.domain.Sms;

public interface ISmsDAO extends IBaseDAO<Sms> {
    String NAMESPACE = ISmsDAO.class.getName().concat(".");

    public int update(Sms data);

    public int updateStatus(Sms data);
    
    public long selectUserTotalCount(Sms condition);
    
    public List<Sms> selectUserList(Sms condition, int start, int count) ;
    
    
}
