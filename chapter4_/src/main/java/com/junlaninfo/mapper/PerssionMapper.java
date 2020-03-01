package com.junlaninfo.mapper;

import com.junlaninfo.entity.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 辉 on 2020/2/29.
 */
public interface PerssionMapper {
    //查询所有权限
    @Select("SELECT  *  FROM sys_permission; ")
    List<Permission>   getAllPermission();
}
