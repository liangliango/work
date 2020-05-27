package org.lino.work.iobus.dao;

import org.lino.work.base.bean.UserWithGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserWithGroupDao extends JpaRepository<UserWithGroup, Long> {

    public UserWithGroup findByUserId(String userId);

}
