package org.lino.work.iobus.dao;

import org.lino.work.base.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IUserDao extends JpaRepository<User, Integer> {

    public User findByLoginId(String loginId);

    @Transactional
    @Modifying
    @Query(value = "update user set if_online = ?1 where login_id = ?2", nativeQuery = true)
    public void updateOnline(boolean status, String loginId);

    void deleteByLoginId(String driverId);
}
