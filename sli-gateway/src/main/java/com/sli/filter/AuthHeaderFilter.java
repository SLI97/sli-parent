package com.sli.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
import com.google.common.collect.Maps;
import com.sun.org.apache.xml.internal.security.keys.KeyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.core.core.Authentication;
//import org.springframework.core.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Map;

@Component("RouterFunctionConfiguration")
public class AuthHeaderFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(AuthHeaderFilter.class);
    private static final String BEARER_TOKEN_TYPE = "Bearer ";
    private static final String CURRENT_USER_NAME = "CURRENT_USER_NAME";
    private static final String TOKEN_USER_DTO = "TOKEN_USER_DTO";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        logger.info("first pre filter");

        if (token == null || token.isEmpty()) {
            ServerHttpResponse response = exchange.getResponse();

            // 封装错误信息
            Map<String, Object> responseData = Maps.newHashMap();
            responseData.put("code", 401);
            responseData.put("message", "非法请求");
            responseData.put("cause", "Token is empty");

            try {
                // 将信息转换为 JSON
                ObjectMapper objectMapper = new ObjectMapper();
                byte[] data = objectMapper.writeValueAsBytes(responseData);

                // 输出错误信息到页面
                DataBuffer buffer = response.bufferFactory().wrap(data);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return response.writeWith(Mono.just(buffer));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return chain.filter(exchange);
    }

    /**
     * 设置过滤器的执行顺序
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

//    @Resource
//    private ObjectMapper objectMapper;
//
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @Override
//    public String filterType() {
//        //过滤器类型
//        return FilterConstants.PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        //同类型的过滤器，数字越小，优先级越高
//        return 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        //希望这个过滤器发挥作用就设置为true，不希望发挥作用就设置为false
//        return true;
//    }
//
//    @Override
//    public Object run() {
//        logger.info("AuthHeaderFilter - 开始鉴权...");
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        requestContext.set("startTime", System.currentTimeMillis());
//        doSomething(requestContext);
//        return null;
//    }
//
//    private void doSomething(RequestContext requestContext) {
//        HttpServletRequest request = requestContext.getRequest();
//        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        String method = request.getMethod();
//        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(method)) {
//            return;
//        }
//
//     	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authHeader.startsWith(BEARER_TOKEN_TYPE)) {
//			logger.info("authHeader={} ", authHeader);
//            requestContext.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, authHeader);
//			String token = StringUtils.substringAfter(authHeader, BEARER_TOKEN_TYPE);
//
//			requestContext.addZuulRequestHeader(CURRENT_USER_NAME, authentication.getName());
//
//			UserDto userDto = objectMapper.convertValue(
//					redisTemplate.opsForValue().get(KeyUtils.getAccessTokenKey(token.trim())), UserDto.class);
//
//			//if (!HttpMethod.GET.toString().equalsIgnoreCase(request.getMethod())) {
//			logger.info("userDto={} ", userDto);
//			requestContext.addZuulRequestHeader(TOKEN_USER_DTO, URLEncoder.encode(objectMapper.writeValueAsString(userDto),"UTF-8"));
//			//}
//		}
//    }

}