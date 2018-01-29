package com.mmall.common;

/**
 * Created by ausu on 2018/1/16.
 */
public enum ResponseCode {
    SUCCESS(0, "SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    public final int code;
    public final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode()
    {
        return code;
    }
    public String getDesc()
    {
        return desc;
    }

}
