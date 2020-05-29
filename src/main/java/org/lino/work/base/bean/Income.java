package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 月收入
 *
 * @author lino
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 收入
    private double income;

    // 支出
    private double payout;

    // 运费金额
    private double carriageFee;

    // 保险金额
    private double insuranceFee;

    private double truckFee;// 搬运费

    private double other;// 其他

    private double profit;// 利润

    private double wage;//工资

    private String month;//月份

}