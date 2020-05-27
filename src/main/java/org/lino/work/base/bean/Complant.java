package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "complant")
public class Complant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String billId;

    @Column(length = 50)
    private String driverId;

    @Column(length = 50)
    private String desc;

    @Column(length = 50)
    private double money;
}
