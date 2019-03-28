package com.du.block.protocol.enums;

/**
 * 终态类型
 * @author darwindu
 * @date 2019/3/14
 **/
public enum ResultTypeEnum {

    SUCCESS("success"),
    FAIL("fail"),
    PROCESSING("processing"),
    ;
    private String typeValue;

    ResultTypeEnum(String value) {
        this.typeValue = value;
    }

    public String getTypeValue() {
        return typeValue;
    }

}
