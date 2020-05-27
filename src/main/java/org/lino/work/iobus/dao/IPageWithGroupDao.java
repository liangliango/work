package org.lino.work.iobus.dao;

import org.lino.work.base.bean.PageWithGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPageWithGroupDao extends JpaRepository<PageWithGroup, Long> {

    public List<PageWithGroup> findByGroupId(int groupId);

    public PageWithGroup findByPageIdAndGroupId(int pageId, int groupId);

    public void deleteByGroupId(int groupId);

}
