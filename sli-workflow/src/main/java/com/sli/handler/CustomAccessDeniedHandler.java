package com.sli.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义访问无权限资源时的异常
 *
 * @author ruoyi
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler
{
    private final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException {
        logger.info("权限不足，请联系管理员 {}", request.getRequestURI());

        String msg = authException.getMessage();

        response.setStatus(555);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("没权限" + msg);

//        ServletUtils.renderString(response, JSON.toJSONString(R.fail(HttpStatus.FORBIDDEN, msg)));
    }
}
