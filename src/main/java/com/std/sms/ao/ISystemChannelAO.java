package com.std.sms.ao;

import java.util.List;

import com.std.sms.bo.base.Paginable;
import com.std.sms.domain.SystemChannel;

public interface ISystemChannelAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addSystemChannel(SystemChannel data);

    public int dropSystemChannel(String systemCode);

    public int editSystemChannel(SystemChannel data);

    public Paginable<SystemChannel> querySystemChannelPage(int start,
            int limit, SystemChannel condition);

    public List<SystemChannel> querySystemChannelList(SystemChannel condition);

    public SystemChannel getSystemChannel(String systemCode);
}
