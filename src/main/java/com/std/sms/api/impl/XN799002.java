package com.std.sms.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.sms.ao.ISmsCaptchaAO;
import com.std.sms.api.AProcessor;
import com.std.sms.common.JsonUtil;
import com.std.sms.dto.req.XN799002Req;
import com.std.sms.dto.res.XN799002Res;
import com.std.sms.exception.BizException;
import com.std.sms.exception.ParaException;
import com.std.sms.spring.SpringContextHolder;
import com.std.sms.util.PhoneUtil;

/**
 * 验证短信验证码
 * @author: myb858 
 * @since: 2016年5月25日 下午12:33:30 
 * @history:
 */
public class XN799002 extends AProcessor {
    private ISmsCaptchaAO smsCaptchaAO = SpringContextHolder
        .getBean(ISmsCaptchaAO.class);

    private XN799002Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        smsCaptchaAO.doCheckCaptcha(req.getMobile(), req.getCaptcha(),
            req.getBizType());
        return new XN799002Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN799002Req.class);
        if (!PhoneUtil.isMobile(req.getMobile())) {
            throw new ParaException("xn799002", "手机号非法");
        }
        if (StringUtils.isBlank(req.getCaptcha())) {
            throw new ParaException("xn799002", "短信验证码不能为空");
        }
        if (StringUtils.isBlank(req.getBizType())) {
            throw new ParaException("xn799002", "业务类型不能为空");
        }

    }

}
