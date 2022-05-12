package com.example.cathayspringboottest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class CurrencyTypeDto {

    Integer id;

    String code;

    String name;

    Integer status = 1;

    Date createTime;

    Date updateTime;
}
