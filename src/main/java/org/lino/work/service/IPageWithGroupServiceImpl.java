package org.lino.work.service;

import org.lino.work.base.bean.Page;
import org.lino.work.base.bean.PageWithGroup;
import org.lino.work.iobus.dao.IPageWithGroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IPageWithGroupServiceImpl implements IPageWithGroupService {
    @Autowired
    IPageWithGroupDao pageWithGroupDao;
    @Override
    public List<PageWithGroup> findPageByGroupId(int groupId) {
        return pageWithGroupDao.findByGroupId(groupId);
    }

    @Transactional
    @Override
    public String addPageWithGroup(int groupId, int[] arr) {
        try {
            for (int i:arr
                 ) {
                    PageWithGroup pageWithGroup = new PageWithGroup();
                    pageWithGroup.setGroupId(groupId);
                    pageWithGroup.setPageId(i);
                    pageWithGroupDao.save(pageWithGroup);
            }
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
