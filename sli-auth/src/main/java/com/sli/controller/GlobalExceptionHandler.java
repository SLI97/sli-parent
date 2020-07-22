package com.sli.controller;


import com.sli.enums.ErrorCodeEnum;
import com.sli.util.wrapper.Json;
import com.sli.util.wrapper.JsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局的的异常拦截器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 无权限访问.
     *
     * @param e the e
     *
     * @return the wrapper
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Json unAuthorizedException(AccessDeniedException e) {
//        logger.error("业务异常={}", e.getMessage(), e);
        return JsonBuilder.build(ErrorCodeEnum.SY1000401.code(),
                ErrorCodeEnum.SY1000401.msg());
    }
}
