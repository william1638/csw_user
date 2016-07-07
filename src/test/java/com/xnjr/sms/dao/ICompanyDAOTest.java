package com.xnjr.sms.dao;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.sms.dao.ICompanyDAO;
import com.std.sms.domain.Company;

public class ICompanyDAOTest extends ADAOTest {

    @SpringBeanByType
    private ICompanyDAO companyDAO;

    @Test
    public void select() {
        Company condition = new Company();
        condition.setCode("XN1001");
        Company data = companyDAO.select(condition);
        logger.info("select : {}", data.getCode() + "\t" + data.getName()
                + "\t" + data.getPrefix());
    }
}
