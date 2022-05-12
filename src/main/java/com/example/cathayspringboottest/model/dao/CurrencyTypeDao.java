package com.example.cathayspringboottest.model.dao;

import com.example.cathayspringboottest.model.entity.CurrencyType;
import org.springframework.data.repository.CrudRepository;

// 第一個參數為訪問的實體，第二參數是這個Entity ID的資料型態
public interface CurrencyTypeDao extends CrudRepository<CurrencyType, Integer> {

}
