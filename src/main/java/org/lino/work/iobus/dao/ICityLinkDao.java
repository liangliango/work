package org.lino.work.iobus.dao;

import org.lino.work.base.bean.CityLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityLinkDao extends JpaRepository<CityLink, Long> {

    public CityLink findByCityId(int cityId);

    public CityLink findById(int id);
}
