package com.junlaninfo.mapper;

import com.junlaninfo.entity.Permission;
import com.junlaninfo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.UUID;

/**
 * Created by 辉 on 2020/2/29.
 */
public interface UserMapper {
    /*
      根据用户名查询用户
     */
       @Select("SELECT *  FROM sys_user WHERE username=#{userName};")
       User findByUsername(@Param("userName") String  userName);

       /*
         根据用户名查询用户的权限
        */
       @Select("SELECT   sys_permission.*   FROM sys_user  INNER  JOIN sys_user_role   ON sys_user.id = sys_user_role.user_id\n" +
               "INNER  JOIN sys_role  ON sys_user_role.role_id = sys_role.id\n" +
               "INNER  JOIN sys_role_permission ON sys_role.id = sys_role_permission.role_id\n" +
               "INNER JOIN sys_permission  ON sys_role_permission.perm_id = sys_permission.id\n" +
               "WHERE username =#{userName};")
       List<Permission> findPermissionByUsername(@Param("userName") String  userName);

}
