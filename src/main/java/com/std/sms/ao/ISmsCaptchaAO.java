package com.std.sms.ao;

import com.std.sms.bo.base.Paginable;
import com.std.sms.domain.SmsCaptcha;

public interface ISmsCaptchaAO {

    String DEFAULT_ORDER_COLUMN = "id";

    public Paginable<SmsCaptcha> querySmsCaptchaPage(int start, int limit,
            SmsCaptcha condition);

    public void doCheckCaptcha(String mobile, String captcha, String bizType);

}
