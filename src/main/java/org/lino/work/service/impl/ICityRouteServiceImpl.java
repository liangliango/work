package org.lino.work.service.impl;

import org.lino.work.base.bean.CityRoute;
import org.lino.work.iobus.dao.ICityRouteDao;
import org.lino.work.service.ICityRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ICityRouteService")
public class ICityRouteServiceImpl implements ICityRouteService {


    @Autowired
    ICityRouteDao cityRouteDao;

    @Override
    public List<CityRoute> findRouteByStartAndEnd(String start, String end) {
        return cityRouteDao.findByStartStationAndEndStation(start,end);
    }

    @Override
    public CityRoute findRouteByRouteId(int routeId) {
        return cityRouteDao.findByRouteId(routeId);
    }

    @Override
    public boolean addCityRoute(CityRoute cityRoute) {
        try {
            cityRouteDao.save(cityRoute);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CityRoute> findCityRouteByStartAndEnd(String startStation, String endStation) {
        return cityRouteDao.findByStartStationAndEndStation(startStation,endStation);
    }

    @Override
    public List<CityRoute> findAllCityRoute() {
        return cityRouteDao.findAll();
    }
}
