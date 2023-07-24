package com.moveon.oauth2.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName User
 * @Description TODO
 * @Author Moveon
 * @Date 2023/7/18 22:13
 * @Version 1.0
 **/
@Data
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;


    @Column(name = "passwd")
    private String passwd;

    @Column(name = "user_role")
    private String userRole;
}
