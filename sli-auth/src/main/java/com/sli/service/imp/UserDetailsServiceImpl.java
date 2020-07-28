package com.sli.service.imp;

import com.sli.common.core.domain.R;
import com.sli.common.core.enums.UserStatus;
import com.sli.common.core.exception.BaseException;
import com.sli.common.core.utils.StringUtils;
import com.sli.domain.LoginUser;
import com.sli.domain.SysUser;
import com.sli.model.UserInfo;
import com.sli.service.RemoteUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RemoteUserService remoteUserService;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        R<UserInfo> userResult = remoteUserService.getUserInfo(username);
        checkUser(userResult, username);
        return getUserDetails(userResult);


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

    public void checkUser(R<UserInfo> userResult, String username) {
        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData())) {
            logger.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        } else if (UserStatus.DELETED.getCode().equals(userResult.getData().getSysUser().getDelFlag())) {
            logger.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(userResult.getData().getSysUser().getStatus())) {
            logger.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }
    }

    private UserDetails getUserDetails(R<UserInfo> result) {

        UserInfo info = result.getData();
        Set<String> dbAuthsSet = new HashSet<String>();
        if (StringUtils.isNotEmpty(info.getRoles())) {
            // 获取角色
            dbAuthsSet.addAll(info.getRoles());
            // 获取权限
            dbAuthsSet.addAll(info.getPermissions());
        }

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();

        return new LoginUser(user.getUserId(), user.getUserName(), user.getPassword(), true, true, true, true,
                authorities);
    }
}
