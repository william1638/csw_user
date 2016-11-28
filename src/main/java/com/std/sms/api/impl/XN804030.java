/**
 * @Title XN804030.java 
 * @Package com.std.sms.api.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年11月21日 下午5:42:18 
 * @version V1.0   
 */
package com.std.sms.api.impl;

import com.std.sms.ao.ISmsAO;
import com.std.sms.api.AProcessor;
import com.std.sms.common.JsonUtil;
import com.std.sms.core.StringValidater;
import com.std.sms.domain.Sms;
import com.std.sms.dto.req.XN804030Req;
import com.std.sms.dto.res.BooleanRes;
import com.std.sms.enums.EChannelType;
import com.std.sms.enums.EPushType;
import com.std.sms.exception.BizException;
import com.std.sms.exception.ParaException;
import com.std.sms.spring.SpringContextHolder;

/** 
 * 发送消息
 * @author: xieyj 
 * @since: 2016年11月21日 下午5:42:18 
 * @history:
 */
public class XN804030 extends AProcessor {
    private ISmsAO smsAO = SpringContextHolder.getBean(ISmsAO.class);

    private XN804030Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Sms data = new Sms();
        data.setFromSystemCode(req.getFromSystemCode());
        data.setChannelType(req.getChannelType());
        data.setPushType(req.getPushType());
        data.setToSystemCode(req.getToSystemCode());
        data.setToMobile(req.getToMobile());
        data.setSmsType(req.getSmsType());
        data.setSmsTitle(req.getSmsTitle());
        data.setSmsContent(req.getSmsContent());
        if (EChannelType.WECHAT.getCode().equals(req.getChannelType())
                && EPushType.WEIXIN.getCode().equals(req.getPushType())) {
            data.setSmsContent(JsonUtil.Object2Json(req.getWxContent()));
        } else {
            data.setSmsContent(req.getSmsContent());
        }
        data.setTopushDatetime(req.getTopushDatetime());
        data.setRemark(req.getRemark());
        smsAO.toSendSms(data);
        return new BooleanRes(true);
        // req.setSmsContent("{\"template_id\":\"E1KoO96UdD5-xAuUDhEIktkQBDarcsRJxhljsDEOk3M\",\"url\":\"http://www.longyan.cn\",\"topcolor\":\"#FF0000\",\"data\":{\"first\":{\"value\":\"尊敬的用户：您好，感谢您使用龙岩公共服务网进行全流程网上办事，您的办事进度如下\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"法律援助案件审批\",\"color\":\"#173177\"},\"keyword2\":{\"value\":\"市法律援助中心\",\"color\":\"#173177\"},\"keyword3\":{\"value\":\"正在办理\",\"color\":\"#173177\"},\"keyword4\":{\"value\":\"陈娉娉\",\"color\":\"#173177\"},\"keyword5\":{\"value\":\"2016年4月15日\",\"color\":\"#173177\"},\"remark\":{\"value\":\"更多详细内容，请登录www.longyan.cn查看\",\"color\":\"#173177\"}}}");
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN804030Req.class);
        StringValidater.validateBlank(req.getFromSystemCode(),
            req.getChannelType(), req.getPushType(), req.getToSystemCode(),
            req.getSmsType());
        if (EChannelType.WECHAT.getCode().equals(req.getChannelType())
                && EPushType.WEIXIN.getCode().equals(req.getPushType())) {
            if (null == req.getWxContent()) {
                throw new BizException("xn702000", "微信内容不能为空");
            }
        } else {
            StringValidater.validateBlank(req.getSmsContent());
        }
    }
}
