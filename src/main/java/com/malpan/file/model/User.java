package com.malpan.file.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@Table(name = "user")
@Entity
@TableName("user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    @Column(columnDefinition = "bigint(20) comment '用户id'")
    private Long userId;

    @Column(columnDefinition = "varchar(30) comment '用户名'")
    private String username;

    @Column(columnDefinition = "varchar(35) comment '密码'")
    private String password;

    @Column(columnDefinition = "varchar(55) comment 'Mailbox'")
    private String mailbox;

    @Column(columnDefinition = "varchar(20) comment '盐值'")
    private String salt;

    @Column(columnDefinition = "varchar(30) comment '注册时间'")
    private String registerTime;

}