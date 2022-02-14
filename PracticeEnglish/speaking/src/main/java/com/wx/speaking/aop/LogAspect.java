package com.wx.speaking.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect //声明为切面类
@Component //将其加入IOC容器中
public class LogAspect {

    /**
     * 定义切入点
     * @Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * com.wx.speaking.controller.PostController.*(..))")
    public void LogPointCut(){

    }

    /**
     * 在连接点执行之前的通知
     *
     */
    @Before("LogPointCut()")
    public void BeforeLog(JoinPoint joinPoint){
        System.out.println("前置方法");
        Object[] obj = joinPoint.getArgs();
        for(Object argItem:obj){
            System.out.println("arg: " + argItem);
        }
    }

    /**
     * 在连接点之后执行的通知（返回通知和异常通知的异常）
     */
    @After("LogPointCut()")
    public void AfterLog(){
        System.out.println("帖子函数运行完毕");
    }


}
