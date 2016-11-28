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
    public void addSystemChannel(SystemChannel data) {
        systemChannelBO.saveSystemChannel(data);
    }

    @Override
    public void editSystemChannel(SystemChannel data) {
        if (!systemChannelBO.isSystemChannelExist(data.getId())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        systemChannelBO.refreshSystemChannel(data);
    }

    @Override
    public void dropSystemChannel(Long id) {
        if (!systemChannelBO.isSystemChannelExist(id)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        systemChannelBO.removeSystemChannel(id);
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
    public SystemChannel getSystemChannel(Long id) {
        return systemChannelBO.getSystemChannel(id);
    }
}
