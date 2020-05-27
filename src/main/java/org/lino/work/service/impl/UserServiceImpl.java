package org.lino.work.service.impl;

import org.lino.work.base.bean.User;
import org.lino.work.iobus.dao.IUserDao;
import org.lino.work.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public Map<?, ?> userLogin(String loginId, String password) {

        Map<String, Object> result = new HashMap<>();

        boolean ifExist = ifExist(loginId);

        if (ifExist == false){
            result.put("STATUS", "ERROR");
            return result;
        }

        User user = userDao.findByLoginId(loginId);
        if (user.getPassword() != password){
            System.err.println("密码错误");
            result.put("STATUS", "ERROR");
            return result;
        }

        if (user.isIfOnline()){
            System.err.println("用户已在线");
            result.put("STATUS", "ERROR");
            return result;
        }
        userDao.updateOnline(true, loginId);
        result.put("STATUS", "SUCCESS");
        result.put("USER", user);

        return result;
    }

    @Override
    public boolean ifExist(String loginId) {
        User user = userDao.findByLoginId(loginId);
        if (user == null){
            System.err.println("用户不存在");
            return false;
        }
        return true;
    }

    @Override
    public boolean changePassword(String loginId, String oldPassword, String newPassword) {

        User user = userDao.findByLoginId(loginId);

        if (user.getPassword() == oldPassword){
            user.setPassword(newPassword);
            userDao.save(user);
            return true;
        }

        return false;
    }
}
