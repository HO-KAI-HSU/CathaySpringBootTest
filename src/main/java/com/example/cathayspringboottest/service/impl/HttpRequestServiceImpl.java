package com.example.cathayspringboottest.service.impl;

import com.example.cathayspringboottest.service.HttpRequestService;
import com.example.cathayspringboottest.utils.HttpUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HttpRequestServiceImpl implements HttpRequestService {

    public Map httpGetResuest(String url) {
        HttpUtil httpUtil = new HttpUtil();
        Map httpResponse = httpUtil.jsonGETNotAuth(url);
        return httpResponse;
    }
}
