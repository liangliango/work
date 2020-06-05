package org.lino.work.service.impl;

import org.lino.work.base.bean.Page;
import org.lino.work.iobus.dao.IPageDao;
import org.lino.work.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IPageService")
public class IPageServiceImpl implements IPageService {
    @Autowired
    IPageDao pageDao;
    @Override
    public List<Page> findAllPage() {
        return pageDao.findAll();
    }
}
