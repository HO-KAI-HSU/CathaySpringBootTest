package com.example.cathayspringboottest.controller;

import com.example.cathayspringboottest.service.CoinDeskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CoinDeskController {

    private Logger logger = LogManager.getLogger(CurrencyTypeController.class);

    //注入CoinDeskService
    @Autowired
    CoinDeskService coinDeskService;

    @GetMapping("/coinDesks")
    public Map getCoindeskList () {
        Map coindesks= coinDeskService.getCoindeskList();
        return coindesks;
    }

    @GetMapping("/coinDeskCurrencyTypeMatchs")
    public Map getCoinDeskCurrencyTypeMatchList () {
        Map coindeskCurrencyTypeMatchList = coinDeskService.getCoindeskCurrencyTypeMatchList();
        return coindeskCurrencyTypeMatchList;
    }
}
