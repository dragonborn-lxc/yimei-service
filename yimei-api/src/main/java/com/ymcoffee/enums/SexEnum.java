package com.ymcoffee.enums;

public enum SexEnum {
    MALE("男"), FEMALE("女"), UNKNOW("未知");

    private String val;
    SexEnum(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
