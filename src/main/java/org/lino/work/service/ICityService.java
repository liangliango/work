package org.lino.work.service;

import org.lino.work.base.bean.BillRoute;
import org.lino.work.base.bean.City;
import org.lino.work.base.bean.CityLink;
import org.lino.work.base.bean.CityRoute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICityService {


    public List<City> findCity();

    public Page<CityLink> findCityLink(Pageable pageable);

    public BillRoute findBillRoute(String id);

    public boolean deleteCityLink(long id);

    public CityLink findById(int id);

    public boolean updateLink(CityLink cityExpand);

    public boolean addCity(String city);

    public boolean addLink(CityLink link);

    public boolean addCityRoute(CityRoute cityRoute);

    public boolean addBillRoute(BillRoute billRoute);

    List<City> findAllCity();

}
