package com.std.sms.ao;

import com.std.sms.domain.Company;

public interface ICompanyAO {
    /** 
     * @param code
     * @return 
     * @create: 2016年7月8日 上午10:44:13 zuixian
     * @history: 
     */
    public Company doGetCompany(String code);
}
