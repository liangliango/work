package org.lino.work.service;

import org.lino.work.base.bean.BillRoute;

public interface IBillRouteService {
    String addBillRoute(BillRoute billRoute);

    BillRoute findBillRouteByBillId(String billId);

    String savaNowbyBillId(String billId, String now);
}
