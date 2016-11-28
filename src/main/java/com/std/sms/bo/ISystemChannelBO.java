package com.std.sms.bo;

import java.util.List;

import com.std.sms.bo.base.IPaginableBO;
import com.std.sms.domain.SystemChannel;
import com.std.sms.enums.EChannelType;
import com.std.sms.enums.EPushType;

public interface ISystemChannelBO extends IPaginableBO<SystemChannel> {

    public boolean isSystemChannelExist(String systemCode);

    public String saveSystemChannel(SystemChannel data);

    public int removeSystemChannel(String systemCode);

    public int refreshSystemChannel(SystemChannel data);

    public List<SystemChannel> querySystemChannelList(SystemChannel condition);

    public SystemChannel getSystemChannel(String systemCode);

    public SystemChannel getSystemChannelByCondition(String systemCode,
            EChannelType channelType, EPushType pushType);
}
