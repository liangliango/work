package org.lino.work.iobus.dao;

import org.lino.work.base.bean.DriverClear;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface IDriverClearDao extends JpaRepository<DriverClear, String> {

    public Page<DriverClear> findAllByDriverId(String driverId, Pageable pageable);

//    @Modifying
//    @Query(value = "select driverCode,sum(carryFee) as carryFeeTotal ,sum(addCarriage) as addCarriageTotal ,count(driverCode) as total from driverclear group by driverCode order by total DESC")
//    List<Object[]> find();

    public Page<DriverClear> findAllByDriverIdAndIsClear(String driverId, Boolean isClear, Pageable pageable);


    public DriverClear findByCarriageId(String billId);




}