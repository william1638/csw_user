package com.std.sms.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.sms.bo.ISOutBO;
import com.std.sms.bo.base.Paginable;
import com.std.sms.bo.base.PaginableBOImpl;
import com.std.sms.dao.ISOutDAO;
import com.std.sms.domain.SOut;

@Component
public class SOutBOImpl extends PaginableBOImpl<SOut> implements ISOutBO {

    @Autowired
    private ISOutDAO sOutDAO;

    @Override
    public long getTotalCount(SOut condition) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Paginable<SOut> getPaginable(int start, SOut condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Paginable<SOut> getPaginable(int start, int pageSize, SOut condition) {
        // TODO Auto-generated method stub
        return null;
    }

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
