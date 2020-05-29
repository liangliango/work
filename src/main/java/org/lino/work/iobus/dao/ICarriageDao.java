package org.lino.work.iobus.dao;

import org.lino.work.base.bean.Carriage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICarriageDao extends JpaRepository<Carriage, Long> {

    public Carriage findByBillId(String billId);

    Carriage findByCarriageId(String carriageId);

    void deleteByCarriageId(String carriageId);

    List<Carriage> findAllByState(String state);

}
