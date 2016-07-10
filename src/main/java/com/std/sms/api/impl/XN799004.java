package com.std.sms.api.impl;

import org.apache.commons.lang.StringUtils;

import com.std.sms.ao.ISOutAO;
import com.std.sms.api.AProcessor;
import com.std.sms.common.DateUtil;
import com.std.sms.common.JsonUtil;
import com.std.sms.core.StringValidater;
import com.std.sms.domain.SOut;
import com.std.sms.dto.req.XN799004Req;
import com.std.sms.exception.BizException;
import com.std.sms.exception.ParaException;
import com.std.sms.spring.SpringContextHolder;

public class XN799004 extends AProcessor {

    private ISOutAO sOutAO = SpringContextHolder.getBean(ISOutAO.class);

    private XN799004Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SOut condition = new SOut();
        condition.setCode(req.getCode());
        condition.setCompanyCode(req.getCompanyCode());
        condition.setChannel(req.getChannel());
        condition.setMobile(req.getMobile());
        condition.setSendDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setSendDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISOutAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return sOutAO.querySOutPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN799004Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }
}
