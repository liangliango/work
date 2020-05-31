package org.lino.work.service.impl;

import cn.hutool.crypto.SecureUtil;
import org.lino.work.base.bean.PageWithGroup;
import org.lino.work.base.bean.User;
import org.lino.work.iobus.dao.IPageWithGroupDao;
import org.lino.work.iobus.dao.IUserDao;
import org.lino.work.iobus.dao.IUserWithGroupDao;
import org.lino.work.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IUserWithGroupDao userWithGroupDao;

    @Autowired
    private IPageWithGroupDao pageWithGroupDao;

    @Override
    public Map<?, ?> userLogin(String loginId, String password) {

        Map<String, Object> result = new HashMap<>();

        boolean ifExist = ifExist(loginId);

        if (ifExist == false){
            result.put("STATUS", "ERROR");
            return result;
        }

        User user = userDao.findByLoginId(loginId);

        System.out.println(user.getPassword()+"    "+SecureUtil.md5(password));

        if (!user.getPassword().equals(SecureUtil.md5(password))){
            System.err.println("密码错误");
            result.put("STATUS", "ERROR");
            return result;
        }

//        if (user.isIfOnline()){
//            System.err.println("用户已在线");
//            result.put("STATUS", "ERROR");
//            return result;
//        }
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

    @Override
    public String changePwd(String loginId, String password) {

        try {
            User user = userDao.findByLoginId(loginId);
            user.setPassword(password);
            userDao.save(user);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @Override
    public List<PageWithGroup> findPageByLoginId(String loginId) {
        int groupId = userWithGroupDao.findByUserId(loginId).getGroupId();
        return pageWithGroupDao.findByGroupId(groupId);
    }
}
