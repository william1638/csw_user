package com.std.sms.bo.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.sms.bo.IDayReportBO;
import com.std.sms.bo.base.Paginable;
import com.std.sms.bo.base.PaginableBOImpl;
import com.std.sms.domain.DayReport;

@Component
public class DayReportBOImpl extends PaginableBOImpl<DayReport> implements
        IDayReportBO {

    @Override
    public long getTotalCount(DayReport condition) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Paginable<DayReport> getPaginable(int start, DayReport condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Paginable<DayReport> getPaginable(int start, int pageSize,
            DayReport condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void saveDayReport(DayReport data) {
        // TODO Auto-generated method stub

    }

    @Override
    public DayReport getDayReport(DayReport data) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DayReport> queryDayReportList(DayReport data) {
        // TODO Auto-generated method stub
        return null;
    }

}
