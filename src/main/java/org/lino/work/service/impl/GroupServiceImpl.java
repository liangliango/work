package org.lino.work.service.impl;

import cn.hutool.log.Log;
import org.lino.work.base.bean.PageWithGroup;
import org.lino.work.base.bean.Group;
import org.lino.work.iobus.dao.*;
import org.lino.work.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;

public class GroupServiceImpl implements IGroupService {

    @Autowired
    private Log log;

    @Autowired
    private IGroupDao groupDao;

    @Autowired
    private IEmployeeDao employeeDao;

    @Autowired
    private IPageDao pageDao;

    @Autowired
    private IPageWithGroupDao pageWithGroupDao;

    @Autowired
    private IUserWithGroupDao userWithGroupDao;

    @Override
    public boolean save(Group group) {

        try {
            groupDao.save(group);
            return true;
        } catch (Exception e) {
            log.info("用户组表关联失败！");
            return false;
        }
    }

    @Override
    public boolean delete(int id) {

        try{
            Group group = findById(id);
            if (group != null){
                groupDao.delete(group);
                employeeDao.updateDepartment("临时组", group.getGroupName());
                return true;
            }
            return false;
        }catch(Exception e){
            log.info("用户组表删除 | 职工部门更新 失败！");
            return false;
        }
    }

    @Override
    public Page<Group> selectAllGroup(Pageable pageable) {
        return groupDao.findAll(pageable);
    }

    @Override
    public Group findById(int id) {
        return groupDao.findById(id);
    }

    @Override
    public boolean update(int id, String description) {
        try{
            Group group = findById(id);
            group.setDescription(description);
            groupDao.save(group);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    public List<org.lino.work.base.bean.Page> findAllPage() {
        return pageDao.findAll();
    }

    @Override
    public List<PageWithGroup> findAllPageWithGroups(int groupId) {
        return pageWithGroupDao.findByGroupId(groupId);
    }

    @Override
    public boolean addPageGro(int groupId, int[] pageId) {
        log.info(groupId+"");
        log.info(pageId+"");
        List<Integer> list = new LinkedList<>();
        for (int i : pageId) {
            list.add(i);
        }

        pageWithGroupDao.deleteByGroupId(groupId);

        for (int i = 0; i < pageId.length; i++) {
            if (pageWithGroupDao.findByPageIdAndGroupId(list.get(i), groupId) == null){
                PageWithGroup pageWithGroup = new PageWithGroup();
                pageWithGroup.setPageId(list.get(i));
                pageWithGroup.setGroupId(groupId);
                pageWithGroupDao.save(pageWithGroup);
            }
        }
//        for (int i = 0; i < 11; i++) {
//            if (pageWithGroupDao.findByPageIdAndGroupId(i + 1, groupId) == null) {
//                System.out.println(i);
//                if (list.contains(i + 1)) {
//                    PageWithGroup pageWithGroup = new PageWithGroup();
//                    pageWithGroup.setPageId(i + 1);
//                    pageWithGroup.setGroupId(groupId);
//                    pageWithGroupDao.save(pageWithGroup);
//                }
//            }
//        }
        return true;
    }

    @Override
    public List<PageWithGroup> findByLoginId(String loginId) {
        int groupId = userWithGroupDao.findByUserId(loginId).getGroupId();
        return pageWithGroupDao.findByGroupId(groupId);
    }
}
