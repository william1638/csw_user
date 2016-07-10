package com.xnjr.sms.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.sms.bo.IDayReportBO;
import com.std.sms.common.DateUtil;
import com.std.sms.core.OrderNoGenerater;
import com.std.sms.dao.ICompanyDAO;
import com.std.sms.dao.ISOutDAO;
import com.std.sms.domain.Company;
import com.std.sms.domain.DayReport;
import com.std.sms.domain.SOut;

public class ICompanyDAOTest extends ADAOTest {

    @SpringBeanByType
    private ICompanyDAO companyDAO;

    @Autowired
    private ISOutDAO sOutDAO;

    @Autowired
    private IDayReportBO dayReportBO;

    @Test
    public void select() {
        Company condition = new Company();
        condition.setCode("XN1001");
        Company data = companyDAO.select(condition);
        logger.info("select : {}", data.getCode() + "\t" + data.getName()
                + "\t" + data.getPrefix());
    }

    @Test
    public void sfsdf() {
        System.out.println("asdasdas");
        SOut sData = new SOut();
        DayReport dData = new DayReport();
        Set<String> channels = new HashSet<String>();
        Set<String> companys = new HashSet<String>();
        Date start = DateUtil.getTodayStart();
        Date end = DateUtil.getTodayEnd();
        sData.setSendDatetimeStart(start);
        sData.setSendDatetimeEnd(end);
        List<SOut> list = sOutDAO.selectList(sData);
        for (SOut s : list) {
            if (!channels.contains(s.getChannel()))
                channels.add(s.getChannel());
            if (!companys.contains(s.getCompanyCode()))
                companys.add(s.getCompanyCode());
            System.out.println(s.toString());
        }
        int Scount = 0;
        int Fcount = 0;
        for (String company : companys) {
            System.out.println(company);
            for (String channel : channels) {
                System.out.println(channel);
                for (SOut s : list) {
                    if (s.getCompanyCode().equals(company)
                            && s.getChannel().split("-")[1].equals(channel
                                .split("-")[1])) {
                        if (s.getErrorCode().equals("1")) {
                            Scount++;
                            System.out.println(Scount);
                        } else {
                            Fcount++;
                            System.out.println(Fcount);
                        }
                    }
                }
                if (Scount != 0 || Fcount != 0) {
                    dData.setCode(OrderNoGenerater.generateM("DR"));
                    dData.setCompanyCode(company);
                    String[] str = channel.split("-");
                    dData.setChannel(str[1]);
                    dData.setSucTimes(String.valueOf(Scount));
                    dData.setFailTimes(String.valueOf(Fcount));
                    String reportDate = DateUtil
                        .getToday(DateUtil.DATA_TIME_PATTERN_1);
                    dData.setReportDate(DateUtil.strToDate(reportDate,
                        DateUtil.DATA_TIME_PATTERN_1));
                    dayReportBO.saveDayReport(dData);
                    Scount = 0;
                    Fcount = 0;
                }
            }
        }
    }
}
