package com.example.cathayspringboottest.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CoinDesk {

    Integer flag;

    CoinDeskBody body;
}


@Getter
@Setter
class CoinDeskBody {

    String chartName;

    Map<String, List<Map<String, Coin>>> bpi;

    Map<String, Updated> time;

    String disclaimer;
}

@Getter
@Setter
class Coin {

    String symbol;

    Double rate_float;

    String code;

    String rate;

    String description;
}

@Getter
@Setter
class Updated {

    String updateduk;

    String updatedISO;

    String updated;
}