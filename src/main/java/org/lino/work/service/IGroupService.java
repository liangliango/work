package org.lino.work.service;

import org.lino.work.base.bean.PageWithGroup;
import org.lino.work.base.bean.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGroupService {

    /**
     * 添加一个组
     * @param group
     * @return
     */
    public boolean save(Group group);

    /**
     * 删除一个组
     * @param id
     * @return
     */
    public boolean delete(int id);

    /**
     * 分页查询所有的组
     * @param pageable
     * @return
     */
    public Page<Group> selectAllGroup(Pageable pageable);

    /**
     * 通过id查询组
     * @param id
     * @return
     */
    public Group findById(int id);

    /**
     * 修改组描述
     * @param id
     * @param description
     * @return
     */
    public boolean update(int id, String description);

    /**
     * 查询所有的组
     * @return
     */
    public List<Group> findAll();

    /**
     * 查询页面
     * @return
     */
    public List<org.lino.work.base.bean.Page> findAllPage();

    /**
     * 通过组id查询改组有权限的页面
     * @param groupId
     * @return
     */
    public List<PageWithGroup> findAllPageWithGroups(int groupId);

    /**
     * 组和页面关联
     * @param groupId
     * @param pageId
     * @return
     */
    public boolean addPageGro(int groupId, int[] pageId);

    /**
     * 查询用户有权限的页面
     * @param loginId
     * @return
     */
    public List<PageWithGroup> findByLoginId(String loginId);

}
