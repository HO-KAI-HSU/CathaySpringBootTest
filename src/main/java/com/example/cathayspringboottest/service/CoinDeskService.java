package com.example.cathayspringboottest.service;

import java.util.Map;

public interface CoinDeskService {

    Map getCoindeskCurrencyTypeMatchList();

    Map getCoindeskList();

    String getCurrentTimeStamp();
}
