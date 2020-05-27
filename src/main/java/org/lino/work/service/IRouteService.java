package org.lino.work.service;

import org.lino.work.base.bean.CityRoute;

import java.util.List;

public interface IRouteService {

    public void generateRoute();

    public List<CityRoute> findAllRouteInfos();

}
