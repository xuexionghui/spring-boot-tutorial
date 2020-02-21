package com.junlaninfo.controller;

import com.junlaninfo.entity.User;
import com.junlaninfo.service.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 辉 on 2020/2/21.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

  private Log  log= LogFactory.getLog(UserController.class);

    /*
    增加用户
     */
    @PostMapping(value = "/addUser")
    public   String  addUser(@RequestBody User user){
        userService.addUser(user);
        log.info("增加成功");
        return   "success";
    }

    /*
    删除用户
     */
    @DeleteMapping(value = "/deleteUser")
    private  String  deleteUser(Long  id){
        userService.deleteUser(id)
;        return  "删除成功";
    }

    /*
    修改用户信息
     */
    @PostMapping(value = "/updateUser")
    private  String  updateUser(Long  id,@RequestBody User  user
    ){
        userService.updateUser(id,user)
        ;        return  "修改成功";
    }
    /*
    查询用户信息
     */
    @GetMapping(value = "/queryUser")
    private List<User> queryUser(
    ){
        log.info("查询用户");
      return   userService.queryUser();
    }
}
