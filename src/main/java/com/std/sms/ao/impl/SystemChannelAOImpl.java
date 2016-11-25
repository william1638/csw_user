package com.std.sms.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.sms.ao.ISystemChannelAO;
import com.std.sms.bo.ISystemChannelBO;
import com.std.sms.bo.base.Paginable;
import com.std.sms.domain.SystemChannel;
import com.std.sms.exception.BizException;

@Service
public class SystemChannelAOImpl implements ISystemChannelAO {

    @Autowired
    private ISystemChannelBO systemChannelBO;

    @Override
    public String addSystemChannel(SystemChannel data) {
        return systemChannelBO.saveSystemChannel(data);
    }

    @Override
    public int editSystemChannel(SystemChannel data) {
        if (!systemChannelBO.isSystemChannelExist(data.getSystemCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return systemChannelBO.refreshSystemChannel(data);
    }

    @Override
    public int dropSystemChannel(String systemCode) {
        if (!systemChannelBO.isSystemChannelExist(systemCode)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return systemChannelBO.removeSystemChannel(systemCode);
    }

    @Override
    public Paginable<SystemChannel> querySystemChannelPage(int start,
            int limit, SystemChannel condition) {
        return systemChannelBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SystemChannel> querySystemChannelList(SystemChannel condition) {
        return systemChannelBO.querySystemChannelList(condition);
    }

    @Override
    public SystemChannel getSystemChannel(String systemCode) {
        return systemChannelBO.getSystemChannel(systemCode);
    }
}
