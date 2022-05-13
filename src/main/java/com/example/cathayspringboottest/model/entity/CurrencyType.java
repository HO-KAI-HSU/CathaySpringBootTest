package com.example.cathayspringboottest.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="CURRENCY_TYPE")
@Getter @Setter
public class CurrencyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    Integer id;

    @Column(name="CODE")
    String code;

    @Column(name="NAME")
    String name;

    @Column(name="STATUS", insertable = false, columnDefinition = "int default 1")
    Integer status = 1;

    @CreatedDate
    @Column(name="CREATE_TIME", updatable = true, nullable = false)
    Date createTime = new Date();

    @LastModifiedDate
    @Column(name="UPDATE_TIME", nullable = true)
    Date updateTime = new Date();
}
