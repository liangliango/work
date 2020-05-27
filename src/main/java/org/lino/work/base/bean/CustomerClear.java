package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 客户运单结算表
 *
 * @author lino
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customerclear")
public class CustomerClear {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "assigned")
    @Column(length = 50)
    private String id;

    @Column
    private String customerId;

    @Column
    private String wayBillId;

    private Double freight;

    private Double insurance;

    private Date writeDate;

    private Double money;

    private Boolean isClear;

    private Date clearDate;
}
