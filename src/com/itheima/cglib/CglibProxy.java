package com.itheima.cglib;

import com.itheima.aspect.MyAspect;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    public Object createProxy(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
}
//21 * proxy CGlib 根据指定父类生成的代理对象
//22 * method 拦截的方法
//23 * args 拦截方法的参数数组
//24 * methodProxy 方法的代理对象，用于执行父类的方法
//25 *1
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        MyAspect myAspect = new MyAspect();
        myAspect.check_Permissions();
        Object obj = methodProxy.invokeSuper(proxy,args);
        myAspect.log();
        return obj;
    }
}
