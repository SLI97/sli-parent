package com.sli.api.system.service;

import com.sli.common.core.constant.ServiceNameConstants;
import com.sli.common.core.domain.R;
import com.sli.api.system.model.UserInfo;
import com.sli.api.system.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务
 *
 * @author sli
 */
@Component
@FeignClient(value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    public R<UserInfo> getUserInfo(@PathVariable("username") String username);
}
