package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "billroute")
public class BillRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int routeId;

    @Column(length = 50)
    private String billId;

    @Column(length = 50)
    private String start;

    @Column(length = 50)
    private String end;

    @Column(length = 50)
    private String pass;

    @Column(length = 50)
    private String now;

    private Date signDate;
}
