package com.mmall.common;

/**
 * Created by ausu on 2018/1/16.
 */
public class Const {
    public static final String CURRENTUSER = "currentuser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员
    }
}
