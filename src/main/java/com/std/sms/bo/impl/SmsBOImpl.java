package com.std.sms.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.sms.bo.ISmsBO;
import com.std.sms.bo.base.PaginableBOImpl;
import com.std.sms.dao.ISmsDAO;
import com.std.sms.domain.Sms;
import com.std.sms.enums.ESmsType;
import com.std.sms.exception.BizException;

@Component
public class SmsBOImpl extends PaginableBOImpl<Sms> implements ISmsBO {

    @Autowired
    private ISmsDAO smsDAO;

    @Override
    public boolean isSmsExist(Long id) {
        Sms condition = new Sms();
        condition.setId(id);
        if (smsDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveSms(Sms data) {
        String code = null;
        if (data != null) {
            data.setCreateDatetime(new Date());
            data.setTopushDatetime(new Date());
            if (ESmsType.NOW_SEND.getCode().equals(data.getSmsType())) {
                data.setPushedDatetime(new Date());
            }
            smsDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeSms(Long id) {
        int count = 0;
        if (id != null) {
            Sms data = new Sms();
            data.setId(id);
            count = smsDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshSms(Sms data) {
        int count = 0;
        if (null != data.getId()) {
            count = smsDAO.updateStatus(data);
        }
        return count;
    }

    @Override
    public List<Sms> querySmsList(Sms condition) {
        return smsDAO.selectList(condition);
    }

    @Override
    public Sms getSms(Long id) {
        Sms data = null;
        if (null != id) {
            Sms condition = new Sms();
            condition.setId(id);
            data = smsDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "消息序号不存在");
            }
        }
        return data;
    }
}
