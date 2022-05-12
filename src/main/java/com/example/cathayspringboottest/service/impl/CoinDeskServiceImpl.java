package com.example.cathayspringboottest.service.impl;

import com.example.cathayspringboottest.dto.CoinDeskCurrencyTypeMatchDto;
import com.example.cathayspringboottest.dto.CurrencyTypeDto;
import com.example.cathayspringboottest.service.CoinDeskService;
import com.example.cathayspringboottest.service.CurrencyTypeService;
import com.example.cathayspringboottest.service.HttpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CoinDeskServiceImpl implements CoinDeskService {

    private String url = "https://api.coindesk.com/v1/bpi/currentprice.json";

    //注入CurrencyTypeService
    @Autowired
    CurrencyTypeService currencyTypeService;

    //注入HttpRequestService
    @Autowired
    HttpRequestService httpRequestService;

    public Map getCoindeskCurrencyTypeMatchList() {
        Map coindeskInfo = this.getCoindeskList();
        Map body = (Map) coindeskInfo.get("body");
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
        String dateTime = this.getCurrentTimeStamp();
        res.put("coinDeskCurrencyTypeMatchList", coinDeskCurrencyTypeMatchDtos);
        res.put("time", dateTime);
        return res;
    }

    public Map getCoindeskList() {
        Map coindeskInfo = httpRequestService.httpGetResuest(url);
        return coindeskInfo;
    }

    private String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
