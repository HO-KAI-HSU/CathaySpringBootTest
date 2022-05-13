package com.example.cathayspringboottest.service.impl;

import com.example.cathayspringboottest.mapper.CurrencyTypeMapper;
import com.example.cathayspringboottest.model.dao.CurrencyTypeDao;
import com.example.cathayspringboottest.dto.CurrencyTypeDto;
import com.example.cathayspringboottest.model.entity.CurrencyType;
import com.example.cathayspringboottest.service.CurrencyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CurrencyTypeServiceImpl implements CurrencyTypeService {

    //注入CurrencyTypeDao
    @Autowired
    CurrencyTypeDao currencyTypeDao;

    //注入CurrencyTypeMapper
    @Autowired
    private CurrencyTypeMapper currencyTypeMapper;

    public List<CurrencyTypeDto> getCurrencyTypes() {
        Iterable<CurrencyType> currencyTypeList = currencyTypeDao.findAll();
        List<CurrencyTypeDto> currencyTypeListDto = new ArrayList<CurrencyTypeDto>();;
        for(CurrencyType currencyType : currencyTypeList) {
            currencyTypeListDto.add(currencyTypeMapper.toDto(currencyType));
        }
        return currencyTypeListDto;
    }

    public Integer createCurrencyType(CurrencyType currencyType) {
        CurrencyType newCurrencyType = currencyTypeDao.save(currencyType);
        return newCurrencyType.getId();
    }

    public CurrencyTypeDto findDtoById(Integer id) {
        Optional<CurrencyType> currencyType = this.findById(id);
        if (!currencyType.isPresent()) {
            return null;
        }
        CurrencyTypeDto currencyTypeDto = currencyTypeMapper.toDto(currencyType.get());
        return currencyTypeDto;
    }

    private Optional<CurrencyType> findById(Integer id) {
        Optional<CurrencyType> currencyType = currencyTypeDao.findById(id);
        return currencyType;
    }

    public Boolean deleteCurrencyType(Integer id) {
        Optional<CurrencyType> findCurrencyType = findById(id);
        if (!findCurrencyType.isPresent()) {
            return false;
        }
        currencyTypeDao.deleteById(id);
        return true;
    }

    public Boolean updateCurrencyType(Integer id, CurrencyType currencyType) {
        Optional<CurrencyType> isExistCurrencyType = findById(id);
        if (! isExistCurrencyType.isPresent()) {
            return false;
        }
        CurrencyType oldCurrencyType = isExistCurrencyType.get();
        if (oldCurrencyType.getName().equals(currencyType.getName()) && oldCurrencyType.getCode().equals(currencyType.getCode())) {
            return true;
        }
        CurrencyType newCurrencyType = oldCurrencyType;
        newCurrencyType.setCode(currencyType.getCode());
        newCurrencyType.setName(currencyType.getName());
        currencyTypeDao.save(newCurrencyType);
        return true;
    }

}
