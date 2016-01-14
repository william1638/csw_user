package com.std.sms.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.sms.ao.ISmsAO;
import com.std.sms.ao.ISmsCaptchaAO;
import com.std.sms.api.AProcessor;
import com.std.sms.common.JsonUtil;
import com.std.sms.dto.req.XN799003Req;
import com.std.sms.dto.res.XN799003Res;
import com.std.sms.enums.ESmsBizType;
import com.std.sms.exception.BizException;
import com.std.sms.exception.ParaException;
import com.std.sms.spring.SpringContextHolder;
import com.std.sms.util.PhoneUtil;

/**
 * 发送验证码
 * @author: myb858 
 * @since: 2016年1月14日 下午4:07:08 
 * @history:
 */
public class XN799003 extends AProcessor {
    private ISmsAO smsAO = SpringContextHolder.getBean(ISmsAO.class);

    private ISmsCaptchaAO smsCaptchaAO = SpringContextHolder
        .getBean(ISmsCaptchaAO.class);

    private XN799003Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String captcha = "1234";
        boolean flag = smsAO.doSend(req.getMobile(), captcha,
            ESmsBizType.YZM.getCode());
        Long id = smsCaptchaAO.doSaveSmsCaptcha(req.getMobile(), captcha,
            req.getBizType(), flag);
        return new XN799003Res(id);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN799003Req.class);
        if (!PhoneUtil.isMobile(req.getMobile())) {
            throw new ParaException("xn799003", "手机号非法");
        }
        if (StringUtils.isBlank(req.getBizType())) {
            throw new ParaException("xn799003", "业务类型不能为空");
        }

    }

}
