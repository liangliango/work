package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 客户基本信息表
 *
 * @author lino
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "assigned")
    @Column(length = 50)
    private String customerId;

    @Column(length = 50)
    private String customerName;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String address;

    @Column(length = 50)
    private String customerType;

    @Column(length = 50)
    private String linkman;

    @Column(length = 50)
    private String linkmanMobile;

    @Column(length = 50)
    private String businessLicenSe;

    @Column(length = 50)
    private String idCard;

}
