package org.lino.work.iobus.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IExtraIncomeDao extends JpaRepository<ExtraIncome, Long> {

    public List<ExtraIncome> findByIncomeMonth(String incomeMonth);


}
