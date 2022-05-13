package com.example.cathayspringboottest.service.impl;

import com.example.cathayspringboottest.dto.CoinDeskCurrencyTypeMatchDto;
import com.example.cathayspringboottest.mapper.CurrencyTypeMapper;
import com.example.cathayspringboottest.dto.CurrencyTypeDto;
import com.example.cathayspringboottest.model.dao.CurrencyTypeDao;
import com.example.cathayspringboottest.model.entity.CurrencyType;
import com.example.cathayspringboottest.service.CoinDeskService;
import com.example.cathayspringboottest.service.CurrencyTypeService;
import com.example.cathayspringboottest.service.HttpRequestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class CurrencyTypeServiceImplTest {

    @Autowired
    private CurrencyTypeService currencyTypeService;

    @Autowired
    private CoinDeskService coinDeskService;

    @Autowired
    private HttpRequestService httpRequestService;

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

        List<CurrencyTypeDto> addCurrencyTypeDtoList = new ArrayList<CurrencyTypeDto>();
        //entity to dto, then add to list
        addCurrencyTypeDtoList.add(currencyTypeMapper.toDto(firstCurrencyType));
        addCurrencyTypeDtoList.add(currencyTypeMapper.toDto(secondCurrencyType));
//        addCurrencyTypeDtoList.add(currencyTypeMapper.toDto(thirdCurrencyType));

        //when
        List<CurrencyTypeDto> storedCurrencyTypeDtoList = currencyTypeService.getCurrencyTypes();
        System.out.println("AddCurrencyTypeDtoList : " + addCurrencyTypeDtoList);
        System.out.println("StoredCurrencyTypeDtoList : " + storedCurrencyTypeDtoList);

        //then
        Assertions.assertEquals(addCurrencyTypeDtoList.get(0).getName(), storedCurrencyTypeDtoList.get(0).getName());
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
        CurrencyTypeDto firstStoredCurrencyTypeDto = currencyTypeService.findDtoById(1);
        CurrencyTypeDto secondStoredCurrencyTypeDto = currencyTypeService.findDtoById(5);

        System.out.println("AddCurrencyTypeDto : " + addCurrencyTypeDto);
        System.out.println("FirstStoredCurrencyTypeDto : " + firstStoredCurrencyTypeDto);
        System.out.println("SecondStoredCurrencyTypeDto : " + secondStoredCurrencyTypeDto);

        //then
        Assertions.assertEquals(addCurrencyTypeDto, firstStoredCurrencyTypeDto);
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

        CurrencyType thirdCurrencyType = new CurrencyType();
        thirdCurrencyType.setId(3);
        thirdCurrencyType.setCode("EUR");
        thirdCurrencyType.setName("歐元");
        currencyTypeDao.save(thirdCurrencyType);

        //when
        // 取得尚未進行刪除動作列表
        List<CurrencyTypeDto> nonDelCurrencyTypeDtoList = currencyTypeService.getCurrencyTypes();

        // 刪除第三筆資料(存在)
        Boolean delRes3 = currencyTypeService.deleteCurrencyType(3);
        // 刪除第三筆資料(不存在)
        Boolean delRes6 = currencyTypeService.deleteCurrencyType(6);

        // 取得刪除動作後的列表
        List<CurrencyTypeDto> delCurrencyTypeDtoList = currencyTypeService.getCurrencyTypes();

        System.out.println("nonDelCurrencyTypeDtoList : " + nonDelCurrencyTypeDtoList);
        System.out.println("delCurrencyTypeDtoList : " + delCurrencyTypeDtoList);

        //then
        Assertions.assertFalse(delRes6);
        Assertions.assertTrue(delRes3);
        Assertions.assertNotEquals(nonDelCurrencyTypeDtoList.size(), delCurrencyTypeDtoList.size());
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
        //更新第二筆資料，改變變數內容
        CurrencyType firstUpdateCurrencyType = new CurrencyType();
        firstUpdateCurrencyType.setCode("GBP22");
        firstUpdateCurrencyType.setName("英國鎊222");

        //更新第一筆資料，不改變變數內容
        CurrencyType secondUpdateCurrencyType = new CurrencyType();
        secondUpdateCurrencyType.setCode("USD");
        secondUpdateCurrencyType.setName("美元");

        //更新第二筆資料(存在)
        Boolean updateRes2 = currencyTypeService.updateCurrencyType(2, firstUpdateCurrencyType);

        //更新第二筆資料(存在)
        Boolean updateRes1 = currencyTypeService.updateCurrencyType(1, secondUpdateCurrencyType);

        //更新第五筆資料(不存在)
        Boolean updateRes5 = currencyTypeService.updateCurrencyType(5, firstUpdateCurrencyType);

        //then
        CurrencyTypeDto storedCurrencyTypeDto = currencyTypeService.findDtoById(2);

        //entity to dto
        CurrencyTypeDto addCurrencyTypeDto = currencyTypeMapper.toDto(secondCurrencyType);

        System.out.println("AddCurrencyTypeDto : " + addCurrencyTypeDto);
        System.out.println("updatedCurrencyTypeDto : " + storedCurrencyTypeDto);
        Assertions.assertTrue(updateRes2);
        Assertions.assertTrue(updateRes1);
        Assertions.assertFalse(updateRes5);
        Assertions.assertNotEquals(addCurrencyTypeDto.getName(), storedCurrencyTypeDto.getName());
        System.out.println("----------" + "updateCurrencyType()" + "----------");
    }

    @Test
    void getCoinDeskApi() {
        System.out.println("----------" + "getCoinDeskApi()" + "----------");

        //given
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";

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

        //when(用http request GET方法測試)
        Map coinDeskRes;
        try {
            coinDeskRes = httpRequestService.httpGetResuest(url);
        } catch (Exception e) {
            coinDeskRes = null;
            System.out.println("Exception : " +   e);
            return ;
        }

        Map body = (Map) coinDeskRes.get("body");
        Map bpi = (Map) body.get("bpi");

        //then
        System.out.println("coinDeskRes : " + coinDeskRes);
        Assertions.assertEquals(bpi.size(), 3);
        System.out.println("----------" + "getCoinDeskApi()" + "----------");
    }

    @Test
    void getCoinDeskCurrencyMatchApi() {
        System.out.println("----------" + "getCoinDeskCurrencyMatchApi()" + "----------");

        //given
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";

        //when
        // (用 coinDeskService 的getCoindeskList 方法)
        // (用 coinDeskService 的getCoindeskCurrencyTypeMatchList 方法)
        Map coinDeskRes;
        Map coindeskCurrencyTypeMatchRes;
        try {
            coinDeskRes = coinDeskService.getCoindeskList();
            coindeskCurrencyTypeMatchRes = coinDeskService.getCoindeskCurrencyTypeMatchList();
        } catch (Exception e) {
            coinDeskRes = null;
            coindeskCurrencyTypeMatchRes = null;
            System.out.println("Exception : " +   e);
            return ;
        }

        Map body = (Map) coinDeskRes.get("body");
        Map bpi = (Map) body.get("bpi");
        List<CoinDeskCurrencyTypeMatchDto> coinDeskCurrencyTypeMatchDtos = new ArrayList<CoinDeskCurrencyTypeMatchDto>();
        List<CurrencyTypeDto> currencyTypeDtos = currencyTypeService.getCurrencyTypes();
        for(CurrencyTypeDto currencyTypeDto : currencyTypeDtos) {
            String code = currencyTypeDto.getCode();
            Map coinInfo = (Map) bpi.get(code);
            String rate = coinInfo.get("rate").toString();
            CoinDeskCurrencyTypeMatchDto coinDeskCurrencyTypeMatchDto = new CoinDeskCurrencyTypeMatchDto();
            coinDeskCurrencyTypeMatchDto.setName(currencyTypeDto.getName());
            coinDeskCurrencyTypeMatchDto.setCode(code);
            coinDeskCurrencyTypeMatchDto.setRate(rate);
            coinDeskCurrencyTypeMatchDtos.add(coinDeskCurrencyTypeMatchDto);
        }
        Map<String, Object> res = new HashMap<>();
        String dateTime = coinDeskService.getCurrentTimeStamp();
        res.put("coinDeskCurrencyTypeMatchList", coinDeskCurrencyTypeMatchDtos);
        res.put("time", dateTime);

        //then
        System.out.println("coinDeskCurrencyTypeMatchRes : " + res);
        System.out.println("ApiCoinDeskCurrencyTypeMatchRes : " + coindeskCurrencyTypeMatchRes);
        Assertions.assertEquals(coinDeskCurrencyTypeMatchDtos.size(), 3);
        Assertions.assertEquals(res, coindeskCurrencyTypeMatchRes);
        System.out.println("----------" + "getCoinDeskCurrencyMatchApi()" + "----------");
    }
}