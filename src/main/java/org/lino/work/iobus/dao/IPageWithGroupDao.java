package org.lino.work.iobus.dao;

import org.jetbrains.annotations.Nullable;
import org.lino.work.base.bean.PageWithGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPageWithGroupDao extends JpaRepository<PageWithGroup, Integer> {

    public List<PageWithGroup> findByGroupId(int groupId);

    @Nullable
    public PageWithGroup findByPageIdAndGroupId(int pageId, int groupId);

    public void deleteByGroupId(int groupId);

}
