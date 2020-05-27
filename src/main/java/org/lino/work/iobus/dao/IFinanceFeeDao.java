package org.lino.work.iobus.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFinanceFeeDao extends JpaRepository<FinanceFee, Long> {

    public List<FinanceFee> findByPayoutMonth(String PayoutMonth);
}
