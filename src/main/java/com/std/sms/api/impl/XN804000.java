package com.std.sms.api.impl;

import com.std.sms.ao.ISystemChannelAO;
import com.std.sms.api.AProcessor;
import com.std.sms.common.JsonUtil;
import com.std.sms.core.StringValidater;
import com.std.sms.domain.SystemChannel;
import com.std.sms.dto.req.XN804000Req;
import com.std.sms.dto.res.PKCodeRes;
import com.std.sms.exception.BizException;
import com.std.sms.exception.ParaException;
import com.std.sms.spring.SpringContextHolder;

/**
 * 新增系统渠道
 * @author: xieyj 
 * @since: 2016年11月26日 上午11:08:13 
 * @history:
 */
public class XN804000 extends AProcessor {
    private ISystemChannelAO systemChannelAO = SpringContextHolder
        .getBean(ISystemChannelAO.class);

    private XN804000Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SystemChannel data = new SystemChannel();
        data.setSystemName(req.getSystemName());
        data.setChannelType(req.getChannelType());
        data.setPushType(req.getPushType());
        data.setStatus(req.getStatus());
        data.setPushSystem(req.getPushSystem());
        data.setPrivateKey1(req.getPrivateKey1());
        data.setPrivateKey2(req.getPrivateKey2());
        data.setPrivateKey3(req.getPrivateKey3());
        data.setRemark(req.getRemark());
        return new PKCodeRes(systemChannelAO.addSystemChannel(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN804000Req.class);
        StringValidater.validateBlank(req.getSystemName(),
            req.getChannelType(), req.getPushType(), req.getStatus());
    }
}
