package com.example.cathayspringboottest.controller;

import com.example.cathayspringboottest.dto.CurrencyTypeDto;
import com.example.cathayspringboottest.mapper.CurrencyTypeMapper;
import com.example.cathayspringboottest.model.entity.CurrencyType;
import com.example.cathayspringboottest.service.CurrencyTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CurrencyTypeController {

    private Logger logger = LogManager.getLogger(CurrencyTypeController.class);

    //注入CurrencyTypeService
    @Autowired
    CurrencyTypeService currencyTypeService; // 取得Service物件

    @Autowired
    private CurrencyTypeMapper currencyTypeMapper;

    @GetMapping("/currencyTypes")
    public List<CurrencyTypeDto> getCurrencyTypeList () {
        List<CurrencyTypeDto> currencyTypeListDto = currencyTypeService.getCurrencyTypes();
        return currencyTypeListDto;
    }

    @GetMapping("/currencyTypes/{id}")
    public CurrencyTypeDto getCurrencyType(@PathVariable Integer id) {
        CurrencyTypeDto currencyTypeDto = currencyTypeService.findDtoById(id);
        return currencyTypeDto;
    }

    @PostMapping("/currencyTypes")
    public ResponseEntity createcCurrencyType(@RequestBody CurrencyTypeDto currencyTypeDto) {
        CurrencyType currencyType = currencyTypeMapper.toEntity(currencyTypeDto);
        Integer rlt = currencyTypeService.createCurrencyType(currencyType);
        return ResponseEntity.status(HttpStatus.CREATED).body(rlt);
    }

    @PutMapping("/currencyTypes/{id}")
    public ResponseEntity upadteCurrencyType(@PathVariable Integer id, @RequestBody CurrencyTypeDto currencyTypeDto) {
        CurrencyType currencyType = currencyTypeMapper.toEntity(currencyTypeDto);
        Boolean rlt = currencyTypeService.updateCurrencyType(id ,currencyType);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code, Name 欄位不能為空");
        }
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @DeleteMapping("/currencyTypes/{id}")
    public ResponseEntity deleteCurrencyType(@PathVariable Integer id) {
        Boolean rlt = currencyTypeService.deleteCurrencyType(id);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id 不存在");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

}
