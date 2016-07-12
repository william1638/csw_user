package com.std.sms.api.impl;

import org.apache.commons.lang.StringUtils;

import com.std.sms.ao.IDayReportAO;
import com.std.sms.api.AProcessor;
import com.std.sms.common.DateUtil;
import com.std.sms.common.JsonUtil;
import com.std.sms.core.StringValidater;
import com.std.sms.domain.DayReport;
import com.std.sms.dto.req.XN799006Req;
import com.std.sms.exception.BizException;
import com.std.sms.exception.ParaException;
import com.std.sms.spring.SpringContextHolder;
import com.std.sms.util.DateTimeUtil;

public class XN799006 extends AProcessor {

    private IDayReportAO dayReportAO = SpringContextHolder
        .getBean(IDayReportAO.class);

    private XN799006Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        DayReport condition = new DayReport();
        condition.setCompanyCode(req.getCompanyCode());
        condition.setChannel(req.getChannel());
        condition.setReportDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setReportDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IDayReportAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return dayReportAO.queryDayReportPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN799006Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater.validateNumber(req.getDateStart(), req.getDateEnd());
        if (req.getStart().length() > 16 || req.getLimit().length() > 16) {
            throw new ParaException("xn799001", "输入数据格式错误");
        }
        if (!DateTimeUtil.isDate(req.getDateStart())) {
            throw new ParaException("xn799001", "开始时间格式错误");
        }
        if (!DateTimeUtil.isDate(req.getDateEnd())) {
            throw new ParaException("xn799001", "结束时间格式错误");
        }
    }

}
