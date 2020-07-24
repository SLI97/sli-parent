package com.sli.service.imp;

import com.sli.common.core.domain.LoginUser;
import com.sli.common.core.enums.UserStatus;
import com.sli.common.core.exception.BaseException;
import com.sli.common.core.utils.StringUtils;
import com.sli.config.AuthUserDto;
import com.sli.config.SecurityUser;
import com.sli.entity.SysUser;
import com.sli.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);

        if (StringUtils.isNull(user))
        {
            logger.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            logger.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            logger.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }

        return createLoginUser(user);

//        userDto.setUsername("sli");
////        userDto.setPassword("123456");
//        userDto.setPassword(new BCryptPasswordEncoder().encode("123456"));
//        userDto.setAccountNonExpired(true);
//        userDto.setAccountNonLocked(true);
//        userDto.setCredentialsNonExpired(true);
//        userDto.setEnabled(true);
//
//        List<GrantedAuthority> authorityList = new ArrayList<>();
//
//        SecurityUser user = SecurityUser.Builder.create()
//                .username(userDto.getUsername())
//                .password(userDto.getPassword())
//                .accountNonExpired(userDto.isAccountNonExpired())
//                .accountNonLocked(userDto.isAccountNonLocked())
//                .credentialsNonExpired(userDto.isCredentialsNonExpired())
//                .enabled(userDto.isEnabled()).authorities(authorityList)
//                .authorities(authorityList).build();
//
//        System.out.println(user);
//        return user;
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }
}
