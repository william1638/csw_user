package com.std.sms.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.sms.bo.ISOutBO;
import com.std.sms.bo.base.PaginableBOImpl;
import com.std.sms.dao.ISOutDAO;
import com.std.sms.domain.SOut;

@Component
public class SOutBOImpl extends PaginableBOImpl<SOut> implements ISOutBO {

    @Autowired
    private ISOutDAO sOutDAO;

    @Override
    public int saveSOut(SOut data) {
        int count = 0;
        if (data != null) {
            count = sOutDAO.insert(data);
        }
        return count;
    }

    @Override
    public List<SOut> querySOutList(SOut data) {
        return sOutDAO.selectList(data);
    }

}
