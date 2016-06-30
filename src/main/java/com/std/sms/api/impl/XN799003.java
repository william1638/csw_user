package com.std.sms.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.sms.ao.ISmsAO;
import com.std.sms.ao.ISmsCaptchaAO;
import com.std.sms.api.AProcessor;
import com.std.sms.common.JsonUtil;
import com.std.sms.common.PropertiesUtil;
import com.std.sms.dto.req.XN799003Req;
import com.std.sms.dto.res.XN799003Res;
import com.std.sms.exception.BizException;
import com.std.sms.exception.ParaException;
import com.std.sms.spring.SpringContextHolder;
import com.std.sms.util.PhoneUtil;
import com.std.sms.util.RandomUtil;

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
        String captcha = RandomUtil.generate4();
        String mobile = req.getMobile();
        String smsContent = addContent(mobile, captcha);
        boolean flag = smsAO.doSend(mobile, smsContent);
        Long id = smsCaptchaAO.doSaveSmsCaptcha(req.getMobile(), captcha,
            req.getBizType(), flag);
        return new XN799003Res(id);
    }

    private String addContent(String mobile, String captcha) {
        return "尊敬的" + PhoneUtil.hideMobile(mobile) + "用户, 您的验证码为" + captcha
                + "，请妥善保管此验证码，切勿泄露给他人。" + PropertiesUtil.Config.PRE_SUF;
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
