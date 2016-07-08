package com.std.sms.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.sms.bo.IPoolBO;
import com.std.sms.bo.base.PaginableBOImpl;
import com.std.sms.dao.IPoolDAO;
import com.std.sms.domain.Pool;

@Component
public class PoolBOImpl extends PaginableBOImpl<Pool> implements IPoolBO {

    @Autowired
    private IPoolDAO poolDAO;

    @Override
    public int savePool(Pool data) {
        int count = 0;
        if (data != null) {
            count = poolDAO.insert(data);
        }
        return count;
    }

    @Override
    public int removePool(String code) {
        int count = 0;
        if (code != null && code != "") {
            Pool data = new Pool();
            data.setCode(code);
            count = poolDAO.delete(data);
        }
        return count;
    }

    @Override
    public List<Pool> queryPoolList(Pool data) {
        return poolDAO.selectList(data);
    }

}
