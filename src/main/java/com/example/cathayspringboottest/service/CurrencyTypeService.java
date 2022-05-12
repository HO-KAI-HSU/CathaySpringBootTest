package com.example.cathayspringboottest.service;

import com.example.cathayspringboottest.dto.CurrencyTypeDto;
import com.example.cathayspringboottest.model.entity.CurrencyType;

import java.util.List;

public interface CurrencyTypeService {
    List<CurrencyTypeDto> getCurrencyTypes();

    Integer createCurrencyType(CurrencyType currencyType);

    Boolean updateCurrencyType(Integer id,CurrencyType currencyType);

    CurrencyTypeDto findDtoById(Integer id);

    Boolean deleteCurrencyType(Integer id);
}
