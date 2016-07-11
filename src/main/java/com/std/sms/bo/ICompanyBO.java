package com.std.sms.bo;

import com.std.sms.bo.base.IPaginableBO;
import com.std.sms.domain.Company;

public interface ICompanyBO extends IPaginableBO<Company> {

    /** 
     * @param data
     * @return 
     * @create: 2016年7月8日 上午9:56:42 zuixian
     * @history: 
     */
    public Company queryCompany(String code);
}
