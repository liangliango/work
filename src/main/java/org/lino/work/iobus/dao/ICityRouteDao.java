package org.lino.work.iobus.dao;

import org.lino.work.base.bean.CityRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICityRouteDao extends JpaRepository<CityRoute, Integer> {

    @Modifying
    @Query(value = "truncate table routeinfo", nativeQuery = true)
    public void truncateTable();

    public List<CityRoute> findByStartStationAndEndStation(String startStation, String endStation);


    CityRoute findByRouteId(String routeId);
}
