package org.lino.work.service;

import org.lino.work.base.bean.WayBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IWayBillService {

    WayBill findWayBillByBillId(String billId);

    boolean updateWayBillByBillId(String billId, WayBill wayBill);

    boolean deleteWayBillByBillId(String billId);

    org.springframework.data.domain.Page<WayBill> findWayBillbyState(String state, Pageable pageable);

    Page<WayBill> findAllWayBill(Pageable pageable);

    boolean addWayBIll(WayBill waybill);

    List<WayBill> findWayBillByState1();

}
