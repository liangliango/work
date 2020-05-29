package org.lino.work.service.impl;

import org.lino.work.base.bean.CityLink;
import org.lino.work.base.bean.CityRoute;
import org.lino.work.iobus.dao.ICityLinkDao;
import org.lino.work.service.ICityLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ICityLinkService")
public class ICityLinkServiceImpl implements ICityLinkService {

    @Autowired
    ICityLinkDao cityLinkDao;

    @Override
    public boolean addCityLink(int cityId ,int cityLink) {

        CityLink cityLink1 = new CityLink();
        cityLink1.setCityId(cityId);
        cityLink1.setLinkCity(cityLink);
        cityLinkDao.save(cityLink1);

        return false;
    }
}
