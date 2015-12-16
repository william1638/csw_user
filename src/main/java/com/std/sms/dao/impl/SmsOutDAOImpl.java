package com.std.sms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.sms.dao.ISmsOutDAO;
import com.std.sms.dao.base.support.AMybatisTemplate;
import com.std.sms.domain.SmsOut;

@Repository("smsOutDAOImpl")
public class SmsOutDAOImpl extends AMybatisTemplate implements ISmsOutDAO {

    @Override
    public int insert(SmsOut data) {
        return super.insert(NAMESPACE.concat("insert_smsOut"), data);
    }

    @Override
    public int delete(SmsOut data) {
        return 0;
    }

    @Override
    public SmsOut select(SmsOut condition) {
        return (SmsOut) super.select(NAMESPACE.concat("select_smsOut"),
            condition, SmsOut.class);
    }

    @Override
    public long selectTotalCount(SmsOut condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_smsOut_count"),
            condition);
    }

    @Override
    public List<SmsOut> selectList(SmsOut condition) {
        return super.selectList(NAMESPACE.concat("select_smsOut"), condition,
            SmsOut.class);
    }

    @Override
    public List<SmsOut> selectList(SmsOut condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_smsOut"), start,
            count, condition, SmsOut.class);
    }

}
