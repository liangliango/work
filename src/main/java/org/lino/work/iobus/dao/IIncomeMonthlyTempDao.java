package org.lino.work.iobus.dao;

import org.lino.work.base.bean.IncomeMonthlyTemp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIncomeMonthlyTempDao extends JpaRepository<IncomeMonthlyTemp, Long> {

    public IncomeMonthlyTemp findByMonth(String month);
}
