package com.std.sms.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.sms.ao.ISmsAO;
import com.std.sms.api.AProcessor;
import com.std.sms.common.DateUtil;
import com.std.sms.common.JsonUtil;
import com.std.sms.core.StringValidater;
import com.std.sms.domain.Sms;
import com.std.sms.dto.req.XN804034Req;
import com.std.sms.dto.res.BooleanRes;
import com.std.sms.enums.EChannelType;
import com.std.sms.enums.EPushType;
import com.std.sms.enums.ESmsType;
import com.std.sms.exception.BizException;
import com.std.sms.exception.ParaException;
import com.std.sms.spring.SpringContextHolder;

/** 
 * 公告新增
 * @author: xieyj 
 * @since: 2016年11月21日 下午5:42:18 
 * @history:
 */
public class XN804034 extends AProcessor {

    private ISmsAO smsAO = SpringContextHolder.getBean(ISmsAO.class);

    private XN804034Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Sms data = new Sms();
        data.setFromSystemCode(req.getFromSystemCode());
        data.setChannelType(EChannelType.NOTICE.getCode());
        data.setPushType(EPushType.NOTICE.getCode());
        data.setToSystemCode(req.getToSystemCode());
        data.setToKind(req.getToKind());
        data.setSmsType(req.getSmsType());
        data.setSmsTitle(req.getSmsTitle());
        data.setSmsContent(req.getSmsContent());
        data.setToCompanyCode(req.getCompanyCode());
        data.setToUserId(req.getUserId());
        data.setOpenType(req.getOpenType());
        data.setJpushId(req.getUserId());
        data.setTopushDatetime(DateUtil.getFrontDate(req.getTopushDatetime(),
            false));
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        smsAO.addNoticeSms(data);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN804034Req.class);
        StringValidater.validateBlank(req.getFromSystemCode(),
            req.getToSystemCode(), req.getSmsTitle(), req.getSmsContent(),
            req.getUpdater());
        if (ESmsType.WAIT_SEND.getCode().equals(req.getSmsType())
                && StringUtils.isBlank(req.getTopushDatetime())) {
            throw new BizException("xn702000", "拟发送时间不能为空");
        }
    }
}
