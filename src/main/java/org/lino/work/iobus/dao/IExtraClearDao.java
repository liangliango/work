package org.lino.work.iobus.dao;

import org.lino.work.base.bean.ExtraClear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface IExtraClearDao extends JpaRepository<ExtraClear, Long> {

    @Query(value = "from extraclear where balanceDate between  ?1 and  ?2 and subjectName = '搬运费'")
    public List<ExtraClear> findByBalanceDate(Date beginTime, Date endTime);
}
