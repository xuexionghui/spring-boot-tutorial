package com.junlaninfo.service;

import com.junlaninfo.dao.UserDao;
import com.junlaninfo.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 辉 on 2020/2/21.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;
    private Log  log = LogFactory.getLog(UserService.class);

    public void addUser(User user) {
        userDao.save(user);
        log.info("保存成功");
    }

    public void deleteUser(Long id) {
        User user = new User();
        user.setId(id);
        userDao.delete(user);
        log.info("删除成功");
    }

    public void updateUser(Long id, User user) {
        user.setId(id);
        userDao.save(user);
    }

    public List<User> queryUser() {
      return    userDao.findAll();
    }
}
