package org.lino.work.iobus.dao;

import org.lino.work.base.bean.WayBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWayBillDao extends JpaRepository<WayBill, Long> {
    public WayBill findById(String billId);
}
