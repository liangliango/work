package org.lino.work.service.impl;

import org.lino.work.base.bean.Group;
import org.lino.work.iobus.dao.IGroupDao;
import org.lino.work.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("IGroupService")
public class IGroupServiceImpl implements IGroupService {
    @Autowired
    IGroupDao groupDao;
    @Override
    public Page<Group> findAllGroupByPage(Pageable pageable) {
        return groupDao.findAll(pageable);
    }

    @Transactional
    @Override
    public boolean deleteGroupById(int id) {
        try {
            groupDao.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Group> findAllGroup() {
        return groupDao.findAll();
    }
}
