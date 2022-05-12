package com.example.cathayspringboottest.service.impl;

import com.example.cathayspringboottest.mapper.CurrencyTypeMapper;
import com.example.cathayspringboottest.dto.CurrencyTypeDto;
import com.example.cathayspringboottest.model.dao.CurrencyTypeDao;
import com.example.cathayspringboottest.model.entity.CurrencyType;
import com.example.cathayspringboottest.service.CurrencyTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CurrencyTypeServiceImplTest {

    @Autowired
    private CurrencyTypeService currencyTypeService;

    @Autowired
    private CurrencyTypeMapper currencyTypeMapper;

    //注入CurrencyTypeDao
    @Autowired
    CurrencyTypeDao currencyTypeDao;

    @Test
    void getCurrencyTypes() {
        System.out.println("----------" + "getCurrencyTypes()" + "----------");
        //given
        CurrencyType firstCurrencyType = new CurrencyType();
        firstCurrencyType.setId(1);
        firstCurrencyType.setCode("USD");
        firstCurrencyType.setName("美元");
        currencyTypeDao.save(firstCurrencyType);

        CurrencyType secondCurrencyType = new CurrencyType();
        secondCurrencyType.setId(2);
        secondCurrencyType.setCode("GBP");
        secondCurrencyType.setName("英國鎊");
        currencyTypeDao.save(secondCurrencyType);

        CurrencyType thirdCurrencyType = new CurrencyType();
        thirdCurrencyType.setId(3);
        thirdCurrencyType.setCode("EUR");
        thirdCurrencyType.setName("歐元");
        currencyTypeDao.save(thirdCurrencyType);

        List<CurrencyTypeDto> addCurrencyTypeDtoList = new ArrayList<CurrencyTypeDto>();
        //entity to dto, then add to list
        addCurrencyTypeDtoList.add(currencyTypeMapper.toDto(firstCurrencyType));
        addCurrencyTypeDtoList.add(currencyTypeMapper.toDto(secondCurrencyType));
        addCurrencyTypeDtoList.add(currencyTypeMapper.toDto(thirdCurrencyType));

        //when
        List<CurrencyTypeDto> storedCurrencyTypeDtoList = currencyTypeService.getCurrencyTypes();
        System.out.println("AddCurrencyTypeDtoList : " + addCurrencyTypeDtoList);
        System.out.println("StoredCurrencyTypeDtoList : " + storedCurrencyTypeDtoList);

        //then
//        Assertions.assertEquals(addCurrencyTypeDtoList, storedCurrencyTypeDtoList);
        System.out.println("----------" + "getCurrencyTypes()" + "----------");
    }

    @Test
    void createCurrencyType() {
        System.out.println("----------" + "createCurrencyType()" + "----------");
        //given
        CurrencyType firstCurrencyType = new CurrencyType();
        firstCurrencyType.setId(1);
        firstCurrencyType.setCode("USD");
        firstCurrencyType.setName("美元");
        currencyTypeService.createCurrencyType(firstCurrencyType);

        CurrencyType secondCurrencyType = new CurrencyType();
        secondCurrencyType.setId(2);
        secondCurrencyType.setCode("GBP");
        secondCurrencyType.setName("英國鎊");
        currencyTypeService.createCurrencyType(secondCurrencyType);

        CurrencyType thirdCurrencyType = new CurrencyType();
        thirdCurrencyType.setId(3);
        thirdCurrencyType.setCode("EUR");
        thirdCurrencyType.setName("歐元");
        currencyTypeService.createCurrencyType(thirdCurrencyType);

        //when
        List<CurrencyTypeDto> storedCurrencyTypeDtoList = currencyTypeService.getCurrencyTypes();

        System.out.println("Size : " + storedCurrencyTypeDtoList.size());
        System.out.println("StoredCurrencyTypeDtoList : " + storedCurrencyTypeDtoList);
        System.out.println("----------" + "createCurrencyType()" + "----------");
    }

    @Test
    void findDtoById() {
        System.out.println("----------" + "findDtoById()" + "----------");
        //given
        CurrencyType firstCurrencyType = new CurrencyType();
        firstCurrencyType.setId(1);
        firstCurrencyType.setCode("USD");
        firstCurrencyType.setName("美元");
        currencyTypeDao.save(firstCurrencyType);

        //entity to dto
        CurrencyTypeDto addCurrencyTypeDto = currencyTypeMapper.toDto(firstCurrencyType);

        //when
        CurrencyTypeDto storedCurrencyTypeDto = currencyTypeService.findDtoById(1);

        System.out.println("AddCurrencyTypeDto : " + addCurrencyTypeDto);
        System.out.println("StoredCurrencyTypeDto : " + storedCurrencyTypeDto);

        //then
        Assertions.assertEquals(addCurrencyTypeDto, storedCurrencyTypeDto);
        System.out.println("----------" + "findDtoById()" + "----------");
    }

    @Test
    void deleteCurrencyType() {
        System.out.println("----------" + "deleteCurrencyType()" + "----------");
        //given
        CurrencyType firstCurrencyType = new CurrencyType();
        firstCurrencyType.setId(1);
        firstCurrencyType.setCode("USD");
        firstCurrencyType.setName("美元");
        currencyTypeDao.save(firstCurrencyType);

        CurrencyType secondCurrencyType = new CurrencyType();
        secondCurrencyType.setId(2);
        secondCurrencyType.setCode("GBP");
        secondCurrencyType.setName("英國鎊");
        currencyTypeDao.save(secondCurrencyType);

        //when
        // 取得尚未進行刪除動作列表
        List<CurrencyTypeDto> nonDelCurrencyTypeDtoList = currencyTypeService.getCurrencyTypes();

        // 刪除第二筆資料
        currencyTypeService.deleteCurrencyType(2);

        // 取得刪除動作後的列表
        List<CurrencyTypeDto> delCurrencyTypeDtoList = currencyTypeService.getCurrencyTypes();

        System.out.println("nonDelCurrencyTypeDtoList : " + nonDelCurrencyTypeDtoList);
        System.out.println("delCurrencyTypeDtoList : " + delCurrencyTypeDtoList);

        //then
//        Assertions.assertEquals(nonDelCurrencyTypeDtoList, delCurrencyTypeDtoList);
        System.out.println("----------" + "deleteCurrencyType()" + "----------");
    }

    @Test
    void updateCurrencyType() {
        System.out.println("----------" + "updateCurrencyType()" + "----------");
        //given
        CurrencyType firstCurrencyType = new CurrencyType();
        firstCurrencyType.setId(1);
        firstCurrencyType.setCode("USD");
        firstCurrencyType.setName("美元");
        currencyTypeDao.save(firstCurrencyType);

        CurrencyType secondCurrencyType = new CurrencyType();
        secondCurrencyType.setId(2);
        secondCurrencyType.setCode("GBP");
        secondCurrencyType.setName("英國鎊");
        currencyTypeDao.save(secondCurrencyType);

        //when
        CurrencyType firstUpdateCurrencyType = new CurrencyType();
        firstUpdateCurrencyType.setCode("GBP22");
        firstUpdateCurrencyType.setName("英國鎊222");
        currencyTypeService.updateCurrencyType(2, firstUpdateCurrencyType);

        //then
        CurrencyTypeDto storedCurrencyTypeDto = currencyTypeService.findDtoById(2);


        //entity to dto

        CurrencyTypeDto addCurrencyTypeDto = currencyTypeMapper.toDto(secondCurrencyType);

        System.out.println("AddCurrencyTypeDto : " + addCurrencyTypeDto);
        System.out.println("updatedCurrencyTypeDto : " + storedCurrencyTypeDto);
        System.out.println("----------" + "updateCurrencyType()" + "----------");
    }
}