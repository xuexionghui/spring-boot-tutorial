package com.junlaninfo.service;

import com.junlaninfo.entity.Permission;
import com.junlaninfo.entity.User;
import com.junlaninfo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 辉 on 2020/2/29.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper  userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(s);
        if (user!=null){
           List<Permission> permissionByUsername = userMapper.findPermissionByUsername(s);
          List<GrantedAuthority>  authorities=new ArrayList<>();
           for (Permission permission : permissionByUsername) {
               authorities.add(new SimpleGrantedAuthority(permission.getPermTag()));
           }
           user.setAuthorities(authorities);
        }
        return user;
    }
}
