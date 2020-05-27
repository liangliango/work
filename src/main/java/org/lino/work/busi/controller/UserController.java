package org.lino.work.busi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.lino.work.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@Api(value = "用户Controller")
@ControllerAdvice
public class UserController extends ReturnType {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户登录验证", notes = "根据用户Id验证用户密码是否正确，进行登录验证; 登录成功后，置为上线")
    @ApiImplicitParam(name = "loginId", value = "用户Id", paramType = "Query", required = true, dataType = "String")
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
    public Map<?, ?> login(String loginId, String password) {

        Map<?, ?> result = userService.userLogin(loginId, password);
        return result;
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public String change(String loginId, String oldPassword, String newPassword) {

        System.out.println(loginId + " " + oldPassword + " " + newPassword);
        boolean flag = false;
        flag = userService.changePassword(loginId, oldPassword, newPassword);
        if (!flag) {
            return ERROR;
        }
        return SUCCESS;
    }

}
