package com.sli.service.imp;

import com.sli.config.AuthUserDto;
import com.sli.config.SecurityUser;
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
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthUserDto userDto = new AuthUserDto();
        if(!"sli".equals(s)){
            throw new InternalAuthenticationServiceException("账号不存在");
        }
        userDto.setUsername("sli");
//        userDto.setPassword("123456");
        userDto.setPassword(new BCryptPasswordEncoder().encode("123456"));
        userDto.setAccountNonExpired(true);
        userDto.setAccountNonLocked(true);
        userDto.setCredentialsNonExpired(true);
        userDto.setEnabled(true);

        List<GrantedAuthority> authorityList = new ArrayList<>();

        SecurityUser user = SecurityUser.Builder.create()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .accountNonExpired(userDto.isAccountNonExpired())
                .accountNonLocked(userDto.isAccountNonLocked())
                .credentialsNonExpired(userDto.isCredentialsNonExpired())
                .enabled(userDto.isEnabled()).authorities(authorityList)
                .authorities(authorityList).build();

        System.out.println(user);
        return user;
    }
}
