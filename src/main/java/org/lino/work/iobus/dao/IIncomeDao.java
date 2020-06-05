package org.lino.work.iobus.dao;

import org.lino.work.base.bean.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IIncomeDao extends JpaRepository<Income,Integer> {

    @Query(value = "select sum(carriage_fee) from income where month = :month ",nativeQuery = true)
    double countcarriageFee(String month);

    @Query(value = "select sum(insurance_fee) from income where month = :month",nativeQuery = true)
    int countinsuranceFee(String month);

    @Query(value = "select sum(truck_fee) from income where month = :month",nativeQuery = true)
    int counttruckFee(String month);

    @Query(value = "select sum(other) from income where month = :month",nativeQuery = true)
    int countother(String month);

    @Query(value = "select sum(profit) from income where month = :month",nativeQuery = true)
    int counprofit(String month);

    @Query(value = "select sum(wage) from income where month = :month",nativeQuery = true)
    int countwage(String month);

    @Query(value = "select sum(income) from income where month = :month",nativeQuery = true)
    int counincome(String month);

    @Query(value = "select sum(payout) from income where month = :month",nativeQuery = true)
    int countpayout(String month);
}


