package org.lino.work.iobus.dao;

import org.lino.work.base.bean.BillRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBillRouteDao extends JpaRepository<BillRoute, Long> {

    public BillRoute findByBillId(String billId);

}
