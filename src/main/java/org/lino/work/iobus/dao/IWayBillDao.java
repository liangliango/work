package org.lino.work.iobus.dao;

import org.lino.work.base.bean.WayBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IWayBillDao extends JpaRepository<WayBill, String> {

    public WayBill findByBillId(String billId);

    @Query("select w from waybill w where w.billId = ?1")
    WayBill findWayBillByBillId(String billId);

    void deleteByBillId(String billId);

    WayBill findByState(String state);

    @Query("select w from waybill w where w.state = :state")
    List<WayBill> findWayBillByState(String state);
}
