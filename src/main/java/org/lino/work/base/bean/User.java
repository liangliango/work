package org.lino.work.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;

/**
 * 用户表
 *
 * @author lino
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(strategy = "assigned", name = "id")
    @Column(length = 50)
    private String loginId;// 登录ID

    @Column(length = 50)
    private String password;

    @Column(columnDefinition = "bit(1) default 0")
    private boolean ifOnline;// 是否在线
}
