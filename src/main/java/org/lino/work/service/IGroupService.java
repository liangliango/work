package org.lino.work.service;

import org.lino.work.base.bean.Employee;
import org.lino.work.base.bean.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGroupService {
    Page<Group> findAllGroupByPage(Pageable pageable);

    boolean deleteGroupById(int id);

    List<Group> findAllGroup();
}
