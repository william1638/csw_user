package com.std.sms.api.impl;

import com.std.sms.ao.ISmsAO;
import com.std.sms.api.AProcessor;
import com.std.sms.common.JsonUtil;
import com.std.sms.core.StringValidater;
import com.std.sms.domain.Sms;
import com.std.sms.dto.req.XN804050Req;
import com.std.sms.dto.res.BooleanRes;
import com.std.sms.enums.EChannelType;
import com.std.sms.enums.EPushType;
import com.std.sms.enums.ESmsType;
import com.std.sms.exception.BizException;
import com.std.sms.exception.ParaException;
import com.std.sms.spring.SpringContextHolder;

/**
 * 微信单发
 * @author: xieyj 
 * @since: 2016年11月28日 下午4:16:38 
 * @history:
 */
public class XN804050 extends AProcessor {
    private ISmsAO smsAO = SpringContextHolder.getBean(ISmsAO.class);

    private XN804050Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Sms data = new Sms();
        data.setFromSystemCode(req.getFromSystemCode());
        data.setChannelType(EChannelType.WECHAT.getCode());
        data.setPushType(EPushType.WEIXIN.getCode());
        data.setToSystemCode(req.getToSystemCode());
        data.setToMobile(req.getToMobile());
        data.setSmsType(ESmsType.NOW_SEND.getCode());
        // 办件状态
        data.setStatus(req.getStatus());
        smsAO.toSendWxSms(data);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN804050Req.class);
        StringValidater.validateBlank(req.getFromSystemCode(),
            req.getToSystemCode(), req.getStatus());
    }
}
