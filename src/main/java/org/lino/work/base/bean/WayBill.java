package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 货运单表
 *
 * @author lino
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "waybill")
public class WayBill {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "assigned")
    private String billId;

    @Column(length =50)
    private String sendId;

    @Column(length = 50)
    private String send;

    @Column(length = 50)
    private String sendAdreass;

    @Column(length = 50)
    private String sendPhone;

    @Column(length = 50)
    private String reciverId;

    @Column(length = 50)
    private String reciver;

    @Column(length = 50)
    private String reciverAdreass;

    @Column(length = 50)
    private String reciverPhone;

    @Column(length = 50)
    private String payCusomer;

    @Column(length = 50)
    private String payType;

    @Column(length = 50)
    private String billName;

    private Double length;

    private Double width;

    private Double height;

    private Double weight;

    private int number;

    private Date startDate;

    private Date exceptDate;

    private Double freight;

    private Double insurance;

    @Column(length = 50)
    private String state;

    @Column(columnDefinition = "bit(1) default 0")
    private boolean isClear;

    private Date arriveDate;

    @Column(length = 200)
    private String desc;

    @Column(length = 50)
    private String err;

    private Date writeDate;

    private Date clearDate;

}
