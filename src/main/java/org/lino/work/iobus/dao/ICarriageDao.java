package org.lino.work.iobus.dao;

import org.lino.work.base.bean.Carriage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICarriageDao extends JpaRepository<Carriage, Long> {

    public Carriage findByBillId(String billId);

    Carriage findByCarriageId(String carriageId);

    void deleteByCarriageId(String carriageId);

    Page<Carriage> findAllByState(String state, Pageable pageable);

    List<Carriage> findByState(String state);

    Page<Carriage> findAllByDriverIdAndState(String driverId, String state, Pageable pageable);

    Page<Carriage> findAllByDriverId(String driverId, Pageable pageable);

    List<Carriage> findAllByState(String state);
}
