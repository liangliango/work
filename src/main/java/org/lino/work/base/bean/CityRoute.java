package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 城市路由表(距离)
 *
 * @author lino
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cityroute")
public class CityRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int startStation;

    @Column(length = 100)
    private String passStation;

    @Column
    private int endStation;

    private double distance;

    private double fetchTime;
}
