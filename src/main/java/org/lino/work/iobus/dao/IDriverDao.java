package org.lino.work.iobus.dao;

import org.lino.work.base.bean.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDriverDao extends JpaRepository<Driver, Long> {

    public Driver findByDriverId(String id);

    public Boolean deleteByDriverId(String id);

    @Query(value = "select driverId from driver")
    public List<String> findAllId();

}
