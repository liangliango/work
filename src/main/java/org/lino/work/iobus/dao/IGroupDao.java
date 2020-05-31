package org.lino.work.iobus.dao;

import org.lino.work.base.bean.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupDao extends JpaRepository<Group, Integer> {

    public Group findByGroupName(String groupName);

    public Group findById(int id);

}
