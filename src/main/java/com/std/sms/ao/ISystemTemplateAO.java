package com.std.sms.ao;

import java.util.List;

import com.std.sms.bo.base.Paginable;
import com.std.sms.domain.SystemTemplate;

public interface ISystemTemplateAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    public String addSystemTemplate(SystemTemplate data);

    public int dropSystemTemplate(Long id);

    public int editSystemTemplate(SystemTemplate data);

    public Paginable<SystemTemplate> querySystemTemplatePage(int start,
            int limit, SystemTemplate condition);

    public List<SystemTemplate> querySystemTemplateList(SystemTemplate condition);

    public SystemTemplate getSystemTemplate(Long id);

}
