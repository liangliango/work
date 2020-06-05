package org.lino.work.service;

import org.lino.work.base.bean.Page;
import org.lino.work.base.bean.PageWithGroup;

import java.util.List;

public interface IPageWithGroupService {
    List<PageWithGroup> findPageByGroupId(int groupId);

    String addPageWithGroup(int groupId, int[] arr);
}
