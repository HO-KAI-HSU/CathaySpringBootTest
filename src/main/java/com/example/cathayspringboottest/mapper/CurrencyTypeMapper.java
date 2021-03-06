package com.example.cathayspringboottest.mapper;


import com.example.cathayspringboottest.dto.CurrencyTypeDto;
import com.example.cathayspringboottest.model.entity.CurrencyType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 配置對應
 * ========================
 * Created with IntelliJ IDEA.
 * User：GeorgeHsu
 * Date：2022/05/10
 * ========================
 */

@Mapper(componentModel = "spring")
public interface CurrencyTypeMapper {

    CurrencyTypeMapper INSTANCE = Mappers.getMapper(CurrencyTypeMapper.class);

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "code",target = "code"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "status",target = "status"),
            @Mapping(source = "currencyType.createTime",target = "createTime"),
            @Mapping(source = "currencyType.updateTime",target = "updateTime")
    })
    CurrencyTypeDto toDto(CurrencyType currencyType);

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "code",target = "code"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "status",target = "status"),
            @Mapping(source = "currencyTypeDto.createTime",target = "createTime", ignore = true),
            @Mapping(source = "currencyTypeDto.updateTime",target = "updateTime", ignore = true)
    })
    CurrencyType toEntity(CurrencyTypeDto currencyTypeDto);
}
