package com.sli;

//import com.sli.common.core.domain.BaseEntity;

import com.sli.annotation.SysLog;
//import com.sli.dao.TestDao;
//import com.sli.entity.MyTest;
//import com.sli.service.CacheService;
//import com.sli.service.TestService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

//@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@MapperScan("com.sli.mapper")
public class WorkflowApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }

    @RestController
    class HelloController {

//        @Autowired
//        private CacheService cacheService;

        @SysLog("我是hello控制器")
        @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ROOT')")
        @RequestMapping(value = "/hello", method = RequestMethod.GET)
        public String hello(HttpServletResponse response, HttpSession session) {
//            session.setAttribute("hello","123123123");
//            Cookie cookie=new Cookie("sessionId","CookieTestInfo");
//            response.addCookie(cookie);
            return "hello";
//            return restTemplate.getForObject("http://sli-GATEWAY/gateway/hello", String.class);
        }

        @SysLog("我是admin控制器")
        @PreAuthorize("hasRole('ROLE_USER')")
        @RequestMapping(value = "/admin/1", method = RequestMethod.GET)
        public String admin(HttpServletResponse response) {
            return "admin1";
        }

        @SysLog("我是user控制器")
        @PreAuthorize("hasRole('ROLE_ROOT')")
        @RequestMapping(value = "/user/1", method = RequestMethod.GET)
        public String user(HttpServletResponse response) {
            return "user";
        }

//        @RequestMapping("/user/haha/{id}")
//        public MyTest getUser(@PathVariable int id) {
//            return cacheService.getUser(id);
//        }
//
//        @Autowired
//        private TestService testService;
//
//        @RequestMapping("/user/add")
//        public void add() throws Exception {
//            MyTest t = new MyTest();
//            t.setAge(11);
//            t.setInhere(false);
//            t.setName("DesrCat");
//            testService.add(t);
//        }
//
//        @RequestMapping("/user/update")
//        public void update() throws Exception {
//            MyTest t = new MyTest();
//            t.setAge(180);
//            t.setInhere(false);
//            t.setName("昴先生111");
//            t.setId(1L);
//            testService.update(t);
//        }
    }

}
