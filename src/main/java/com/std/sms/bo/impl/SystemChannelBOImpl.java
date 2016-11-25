package com.std.sms.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.sms.bo.ISystemChannelBO;
import com.std.sms.bo.base.PaginableBOImpl;
import com.std.sms.core.OrderNoGenerater;
import com.std.sms.dao.ISystemChannelDAO;
import com.std.sms.domain.SystemChannel;
import com.std.sms.enums.EGeneratePrefix;
import com.std.sms.exception.BizException;

@Component
public class SystemChannelBOImpl extends PaginableBOImpl<SystemChannel>
        implements ISystemChannelBO {

    @Autowired
    private ISystemChannelDAO systemChannelDAO;

    @Override
    public boolean isSystemChannelExist(String systemCode) {
        SystemChannel condition = new SystemChannel();
        condition.setSystemCode(systemCode);
        if (systemChannelDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveSystemChannel(SystemChannel data) {
        String systemCode = null;
        if (data != null) {
            systemCode = OrderNoGenerater
                .generateM(EGeneratePrefix.SYSTEMCHANNEL.getCode());
            data.setSystemCode(systemCode);
            systemChannelDAO.insert(data);
        }
        return systemCode;
    }

    @Override
    public int removeSystemChannel(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            SystemChannel data = new SystemChannel();
            data.setSystemCode(code);
            count = systemChannelDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshSystemChannel(SystemChannel data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getSystemCode())) {
            count = systemChannelDAO.update(data);
        }
        return count;
    }

    @Override
    public List<SystemChannel> querySystemChannelList(SystemChannel condition) {
        return systemChannelDAO.selectList(condition);
    }

    @Override
    public SystemChannel getSystemChannel(String systemCode) {
        SystemChannel data = null;
        if (StringUtils.isNotBlank(systemCode)) {
            SystemChannel condition = new SystemChannel();
            condition.setSystemCode(systemCode);
            data = systemChannelDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "系统渠道不存在");
            }
        }
        return data;
    }
}
