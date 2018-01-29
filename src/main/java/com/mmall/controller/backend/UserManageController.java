package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by ausu on 2018/1/17.
 */
@Controller
@RequestMapping("/manage/user")
public class UserManageController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session)
    {
        ServerResponse serverResponse = iUserService.login(username,password);
        if(serverResponse.isSuccess())
        {
            User user = (User)serverResponse.getData();
            if(user.getRole() == Const.Role.ROLE_ADMIN)
            {
                //管理员角色
                session.setAttribute(Const.CURRENTUSER,user);
                return serverResponse;
            }
            else {
                return ServerResponse.createByErrorMessage("不是管理员，无法登陆");
            }
        }
        return serverResponse;
    }
}
