package com.std.sms.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.sms.bo.IDayReportBO;
import com.std.sms.bo.base.PaginableBOImpl;
import com.std.sms.dao.IDayReportDAO;
import com.std.sms.domain.DayReport;

@Component
public class DayReportBOImpl extends PaginableBOImpl<DayReport> implements
        IDayReportBO {

    @Autowired
    private IDayReportDAO dayReportDAO;

    @Override
    public int saveDayReport(DayReport data) {
        int count = 0;
        if (data != null) {
            count = dayReportDAO.insert(data);
        }
        return count;
    }

    @Override
    public DayReport getDayReport(DayReport data) {
        return dayReportDAO.select(data);
    }

    @Override
    public List<DayReport> queryDayReportList(DayReport data) {
        return dayReportDAO.selectList(data);
    }

}
