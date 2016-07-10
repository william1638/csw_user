package com.std.sms.bo;

import java.util.List;

import com.std.sms.bo.base.IPaginableBO;
import com.std.sms.domain.DayReport;

public interface IDayReportBO extends IPaginableBO<DayReport> {
    /** 
     * @param data 
     * @create: 2016年7月8日 下午5:45:26 zuixian
     * @history: 
     */
    public int saveDayReport(DayReport data);

    /** 
     * @param data 
     * @create: 2016年7月8日 下午5:46:32 zuixian
     * @history: 
     */
    public DayReport getDayReport(DayReport data);

    /** 
     * @param data
     * @return 
     * @create: 2016年7月8日 下午5:47:47 zuixian
     * @history: 
     */
    public List<DayReport> queryDayReportList(DayReport data);
}
