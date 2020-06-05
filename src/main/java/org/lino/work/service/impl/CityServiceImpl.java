package org.lino.work.service.impl;

import org.lino.work.base.bean.BillRoute;
import org.lino.work.base.bean.City;
import org.lino.work.base.bean.CityLink;
import org.lino.work.base.bean.CityRoute;
import org.lino.work.iobus.dao.IBillRouteDao;
import org.lino.work.iobus.dao.ICityDao;
import org.lino.work.iobus.dao.ICityLinkDao;
import org.lino.work.iobus.dao.ICityRouteDao;
import org.lino.work.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service("ICityService")
public class CityServiceImpl implements ICityService {

    @Autowired
    private ICityDao cityDao;

    @Autowired
    private ICityLinkDao cityLinkDao;

    @Autowired
    private IBillRouteDao billRouteDao;

    @Autowired
    private ICityRouteDao cityRouteDao;


    @Override
    public List<City> findCity() {
        return cityDao.findAll();
    }

    @Override
    public Page<CityLink> findCityLink(Pageable pageable) {
        return cityLinkDao.findAll(pageable);
    }

    @Override
    public BillRoute findBillRoute(String id) {
        return billRouteDao.findByBillId(id);
    }

    @Override
    public boolean deleteCityLink(long id) {
        try {
            cityLinkDao.deleteById(id);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CityLink findById(int id) {
        return cityLinkDao.findById(id);
    }

    @Override
    public boolean updateLink(CityLink cityExpand) {
        try {
            cityLinkDao.save(cityExpand);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    @Override
    public boolean addCity(String city) {

        try {
                City city1 = new City();
                city1.setCity(city);
                cityDao.save(city1);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addLink(CityLink link) {

        try {
            cityLinkDao.save(link);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addCityRoute(CityRoute cityRoute) {
        try {
            cityRouteDao.save(cityRoute);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addBillRoute(BillRoute billRoute) {
        try {
            billRouteDao.save(billRoute);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<City> findAllCity() {
        return cityDao.findAll();
    }
}
