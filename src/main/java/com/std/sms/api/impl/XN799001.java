package com.std.sms.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.sms.ao.ISmsAO;
import com.std.sms.api.AProcessor;
import com.std.sms.common.JsonUtil;
import com.std.sms.dto.req.XN799001Req;
import com.std.sms.dto.res.XN799001Res;
import com.std.sms.exception.BizException;
import com.std.sms.exception.ParaException;
import com.std.sms.spring.SpringContextHolder;
import com.std.sms.util.PhoneUtil;

/**
 * 发送短信
 * @author: myb858 
 * @since: 2015年8月20日 上午10:29:57 
 * @history:
 */
public class XN799001 extends AProcessor {
    private ISmsAO smsAO = SpringContextHolder.getBean(ISmsAO.class);

    private XN799001Req xn799001Req = null;

    @Override
    public Object doBusiness() throws BizException {
        boolean flag = smsAO.doSend(xn799001Req.getMobile(),
            xn799001Req.getContent());
        Long id = smsAO.doSaveSmsOut(xn799001Req.getMobile(),
            xn799001Req.getContent(), xn799001Req.getBizType(),
            xn799001Req.getRemark(), flag);
        return new XN799001Res(id);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn799001Req = JsonUtil.json2Bean(inputparams, XN799001Req.class);
        if (!PhoneUtil.isMobile(xn799001Req.getMobile())) {
            throw new ParaException("xn799001", "手机号非法");
        }
        if (StringUtils.isBlank(xn799001Req.getContent())) {
            throw new ParaException("xn799001", "短信内容不能为空");
        }
    }
}
