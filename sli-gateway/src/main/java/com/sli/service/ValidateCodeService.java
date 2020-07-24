package com.sli.service;

import com.sli.common.core.domain.AjaxResult;
import com.sli.common.core.exception.CaptchaException;

import java.io.IOException;

/**
 * 验证码处理
 *
 * @author sli
 */
public interface ValidateCodeService
{
    /**
     * 生成验证码
     */
    public AjaxResult createCapcha() throws IOException;

    /**
     * 校验验证码
     */
    public void checkCapcha(String key, String value) throws CaptchaException;
}
