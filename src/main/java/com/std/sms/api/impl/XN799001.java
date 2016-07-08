package com.std.sms.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.sms.ao.IPoolAO;
import com.std.sms.ao.ISOutAO;
import com.std.sms.api.AProcessor;
import com.std.sms.common.JsonUtil;
import com.std.sms.dto.req.XN799001Req;
import com.std.sms.dto.res.XN799001Res;
import com.std.sms.exception.BizException;
import com.std.sms.exception.ParaException;
import com.std.sms.spring.SpringContextHolder;
import com.std.sms.util.PhoneUtil;

/**
 * 发送下行短信
 * @author: myb858 
 * @since: 2015年8月20日 上午10:29:57 
 * @history:
 */
public class XN799001 extends AProcessor {
    private IPoolAO poolAO = SpringContextHolder.getBean(IPoolAO.class);

    private ISOutAO sOutAO = SpringContextHolder.getBean(ISOutAO.class);

    private XN799001Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        // boolean flag = smsAO.doSend(req.getMobile(), req.getContent());
        // Long id = smsAO.doSaveSmsOut(req.getMobile(), req.getContent(),
        // req.getBizType(), req.getRemark(), flag);
        boolean flag = sOutAO.doSend(req.getChannel(), req.getMobile(),
            req.getContent());
        String code = null;
        if (flag) {
            code = sOutAO.doSaveSOut(req.getChannel(), req.getMobile(),
                req.getContent());
        } else {
            poolAO.doSaveSOutToPool(req.getChannel(), req.getMobile(),
                req.getContent(), req.getSendDatetime());
            code = "待发送";
        }
        return new XN799001Res(code);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN799001Req.class);
        if (!PhoneUtil.isMobile(req.getMobile())) {
            throw new ParaException("xn799001", "手机号非法");
        }
        if (StringUtils.isBlank(req.getContent())) {
            throw new ParaException("xn799001", "短信内容不能为空");
        }
        if (StringUtils.isBlank(req.getChannel())) {
            throw new ParaException("xn799001", "通道不能为空");
        }
    }
}
