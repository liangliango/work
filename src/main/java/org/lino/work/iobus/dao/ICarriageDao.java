package org.lino.work.iobus.dao;

import org.lino.work.base.bean.Carriage;
import org.lino.work.base.bean.WayBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarriageDao extends JpaRepository<Carriage, Long> {

    public Carriage findByBillId(String billId);

}
