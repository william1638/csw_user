package com.xnjr.sms.dao;

import java.util.Date;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.sms.dao.ISmsOutDAO;
import com.std.sms.domain.SmsOut;
import com.std.sms.enums.ESmsStatus;

public class ISmsOutDAOTest extends ADAOTest {
    @SpringBeanByType
    private ISmsOutDAO smsOutDAO;

    @Test
    public void insert() {
        SmsOut data = new SmsOut();
        data.setMobile("13958092437");
        data.setContent("短信测试内容");
        data.setBizType("1");
        data.setRemark("单元测试");
        data.setStatus(ESmsStatus.TOSEND.getCode());
        data.setCreateDatetime(new Date());
        int lineNum = smsOutDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void insert1() {
        SmsOut data = new SmsOut();
        data.setMobile("13958092437");
        data.setContent("短信测试内容");
        data.setBizType("1");
        data.setRemark("单元测试");
        data.setStatus(ESmsStatus.TOSEND.getCode());
        data.setCreateDatetime(new Date());
        int lineNum = smsOutDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }
}
