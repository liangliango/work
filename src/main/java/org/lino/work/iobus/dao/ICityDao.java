package org.lino.work.iobus.dao;

import org.lino.work.base.bean.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICityDao extends JpaRepository<City, Long> {

    public City findById(int id);

    public City findByCity(String city);

    @Query(value = "select * from City where id not in (select city_id from cityexpand)", nativeQuery = true)
    public List<City> findLeftRegions();

}
