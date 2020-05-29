package org.lino.work.service;

import io.swagger.annotations.Api;
import org.lino.work.base.bean.Carriage;
import org.lino.work.base.bean.WayBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequestMapping(value = "/carriage")
@ControllerAdvice
@RestController
@Api(value = "运输合同 controller")
public interface ICarriageService {
    String addCarriage(Carriage carriage);

    Carriage findCarriageByCarriageId(String carriageId);

    String updateWayBillByBillId(String billId, WayBill wayBill);

    String deleteCarriageByCarriageId(String carriageId);

    List<Carriage> findAllCarriageByState(String state);

    Page<Carriage> findAllCarriage(Pageable pageable);

    String updateCarriage(Carriage carriage);
}
