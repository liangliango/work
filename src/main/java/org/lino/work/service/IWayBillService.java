package org.lino.work.service;

import org.lino.work.base.bean.WayBill;

import java.util.List;

public interface IWayBillService {

    WayBill findWayBillByBillId(String billId);

    boolean updateWayBillByBillId(String billId, WayBill wayBill);

    boolean deleteWayBillByBillId(String billId);

    WayBill findWayBillbyState(String state);

    List<WayBill> findAllWayBill();

    boolean addWayBIll(WayBill waybill);

    List<WayBill> findWayBillByState1();

}
