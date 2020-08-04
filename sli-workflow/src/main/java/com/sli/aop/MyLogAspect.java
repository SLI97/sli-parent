package com.sli.aop;

import com.sli.annotation.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@SuppressWarnings("all")
@Aspect // AOP 切面
@Component
public class MyLogAspect {
    // 切入点
    @Pointcut(value = "@annotation(com.sli.annotation.SysLog)")
    private void pointcut() {
    }

    /**
     * 在方法执行前后
     *
     * @param point
     * @param myLog
     * @return
     */
    @Around(value = "pointcut() && @annotation(sysLog)")
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {
        System.out.println("++++执行了around方法++++");
        String requestUrl = sysLog.value();
        // 拦截的类名
        Class clazz = point.getTarget().getClass();
        // 拦截的方法
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod;
        try {
            currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
            System.out.println("执行了类:" + clazz.getSimpleName());
            System.out.println("方法:" + currentMethod.getName());
            System.out.println("自定义注解:" + requestUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return point.proceed(); // 执行程序
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
    }

    /**
     * 方法执行后
     *
     * @param joinPoint
     * @param myLog
     * @param result
     * @return
     */
    @AfterReturning(value = "pointcut() && @annotation(sysLog)", returning = "result")
    public Object afterReturning(JoinPoint joinPoint, SysLog sysLog, Object result) {
        // HttpServletRequest request = ((ServletRequestAttributes)
        // RequestContextHolder.getRequestAttributes()).getRequest();
        // HttpSession session = request.getSession();
        System.out.println("++++执行了afterReturning方法++++");
        System.out.println("执行结果：" + result);
        return result;
    }

    /**
     * 方法执行后 并抛出异常
     *
     * @param joinPoint
     * @param myLog
     * @param ex
     */
    @AfterThrowing(value = "pointcut() && @annotation(sysLog)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, SysLog sysLog, Exception ex) {
        System.out.println("++++执行了afterThrowing方法++++");
        System.out.println("请求：" + sysLog.value() + " 出现异常");
    }
}
