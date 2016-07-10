package com.std.sms.ao;

import java.util.List;

import com.std.sms.bo.base.Paginable;
import com.std.sms.domain.DayReport;

public interface IDayReportAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /** 
     *  
     * @create: 2016年7月10日 上午9:58:24 zuixian
     * @history: 
     */
    public void doSaveDayReport();

    /** 
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2016年7月10日 上午11:06:35 zuixian
     * @history: 
     */
    public Paginable<DayReport> queryDayReportPage(int start, int limit,
            DayReport condition);

    /** 
     * @param condition
     * @return 
     * @create: 2016年7月10日 上午11:06:38 zuixian
     * @history: 
     */
    public List<DayReport> queryDayReportList(DayReport condition);
}
