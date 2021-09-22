package com.itheima.jdk;

import com.itheima.aspect.MyAspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {
    private UserDao userDao;
    public Object createProxy(UserDao userDao) {
        this.userDao = userDao;
        ClassLoader classLoader = JdkProxy.class.getClassLoader();
        Class[] classes = userDao.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader,classes,this);
    }
     /*
      *所有动态代理类的方法调用，都会交由 invoke ()方法去处理
      * proxy 被代理后的对象
      * method 将要被执行的方法信息(反射)
      * args 执行方法时需要的参数
      */
     @Override
    public Object invoke(Object proxy, Method method,Object[] args) throws InvocationTargetException, IllegalAccessException {
         MyAspect myAspect = new MyAspect();
         myAspect.check_Permissions();
         Object obj = method.invoke(userDao,args);
         myAspect.log();
         return obj;
     }
}
