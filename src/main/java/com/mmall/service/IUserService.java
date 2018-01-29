package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ausu on 2018/1/16.
 */
public interface IUserService {
     ServerResponse<User> login(String username,String password);
    public ServerResponse<String> register(User user);
    public ServerResponse<String> checkVaild(String str,String type);
    public ServerResponse<String> selectQuestion(String username);
    public ServerResponse<String> checkAnswer(String username,String password,String answer);
    public ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);
    public ServerResponse<User> updateUserInfo(User user);
    public ServerResponse<User> getInformation(Integer userId);
    public ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);
    public ServerResponse checkAdminRole(User user);
}
