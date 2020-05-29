package org.lino.work.iobus.dao;

import org.lino.work.base.bean.Income;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface IIncomeDao extends Repository<Income,Integer> {

    @Query(value = "select count(carriageFee) from income where month = :month ",nativeQuery = true)
    int countcarriageFee(String month);

    @Query(value = "select count(insuranceFee) from income where month = :month",nativeQuery = true)
    int countinsuranceFee(String month);

    @Query(value = "select count(truckFee) from income where month = :month",nativeQuery = true)
    int counttruckFee(String month);

    @Query(value = "select count(other) from income where month = :month",nativeQuery = true)
    int countother(String month);

    @Query(value = "select count(income)-count(payout) from income where month = :month",nativeQuery = true)
    int counprofit(String month);

    @Query(value = "select count(wage) from income where month = :month",nativeQuery = true)
    int countwage(String month);

    @Query(value = "select count(income) from income where month = :month",nativeQuery = true)
    int counincome(String month);

    @Query(value = "select count(payout) from income where month = :month",nativeQuery = true)
    int countpayout(String month);
}


