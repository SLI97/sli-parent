package com.example.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthHeaderFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //过滤器类型
        return FilterConstants.PRE_TYPE;
    }


    @Override
    public int filterOrder() {
        //同类型的过滤器，数字越小，优先级越高
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        //希望这个过滤器发挥作用就设置为true，不希望发挥作用就设置为false
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader("authorization");
        System.out.println(token);

//        doSomething();
        if (token != null) {
            //登录过
            System.out.println("过！");
            return "过！";
        } else {
            //无效
            //告诉后面的过滤器，在当前就已经出问题了，没必要去进行路由转发了
//            context.setSendZuulResponse(false);
//            context.setResponseStatusCode(401);
//            context.setResponseBody("{\"msg\":\"401,access without permission,please login first.\"}");
            System.out.println("不过！！");
            try {
                throw new Exception("没有token");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "access denied";
        }
    }

//    private void doSomething(RequestContext requestContext) throws {
//
//    }
}