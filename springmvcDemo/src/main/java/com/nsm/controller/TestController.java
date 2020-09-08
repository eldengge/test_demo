package com.nsm.controller;

import com.nsm.service.CommonAbstract;
import com.nsm.service.GetContextTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController extends CommonAbstract {

    @RequestMapping(value = "/log4jTest",method = RequestMethod.GET)
    public String testLog4j(){

        for(int i =0;i<100;i++){
            logger.debug("this is debug message");
            logger.info("this is info message");
            logger.warn("this is warn message");
            logger.error("this is error message");
        }

        return "testpage/testPage1";
    }

    @RequestMapping("/getContext")
    public String getApplicationContext(HttpServletRequest request, Model model){
        ServletContext servletContext = request.getSession().getServletContext();
        /**
         * spring容器相当于springmvc父容器
         * spring容器保存在ServeltContext和ContextLoder中各一份 ContextLoader类中的initWebApplicationContext()方法中可查看保存位置
         * springmvc容器保存在ServletContext中 FrameworkServlet类中的initWebApplicationContext()方法中可以查看保存位置
         */
        WebApplicationContext springContext1 = ContextLoader.getCurrentWebApplicationContext();

        WebApplicationContext springContext2 = (WebApplicationContext)servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

        WebApplicationContext mvcContext1 = WebApplicationContextUtils.getWebApplicationContext(servletContext, FrameworkServlet.SERVLET_CONTEXT_PREFIX + "dispatcher");

        GetContextTestService testService1 = springContext1.getBean("getContextTestServiceImpl", GetContextTestService.class);

        GetContextTestService testService2 = springContext2.getBean("getContextTestServiceImpl", GetContextTestService.class);

        TestController testController = mvcContext1.getBean("testController", TestController.class);

        String str1 = "spring容器保存在ServletContext中的名称："+WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;

        String str2 = "mvc容器中保存在ServletContext中的名称："+FrameworkServlet.SERVLET_CONTEXT_PREFIX + "dispatcher";

        System.out.println(str1);

        System.out.println(str2);

        System.out.println(testService1);

        System.out.println(testService2);

        System.out.println(testController);

        Map<String,Object> map = new HashMap<>();

        return "testpage/testPage1";
    }

    @RequestMapping("/testPage1")
    public String testPage (){
        return "testpage/testPage1";
    }

    @Override
    public String toString() {
        return "I'm TestController";
    }
}
