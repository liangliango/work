package org.lino.work.service;

import org.lino.work.base.bean.BillRoute;
import org.lino.work.base.bean.CityRoute;

import java.util.List;

public interface ICityRouteService {
    List<CityRoute> findRouteByStartAndEnd(String start, String end);

    CityRoute findRouteByRouteId(int routeID);

    boolean addCityRoute(CityRoute cityRoute);

    List<CityRoute> findCityRouteByStartAndEnd(String startStation, String endStation);

    List<CityRoute> findAllCityRoute();
}
