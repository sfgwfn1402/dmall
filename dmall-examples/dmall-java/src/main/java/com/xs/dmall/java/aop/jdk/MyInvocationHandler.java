package com.xs.dmall.java.aop.jdk;

import com.xs.dmall.java.aop.service.UserService;
import com.xs.dmall.java.aop.service.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

    /**
     * 目标对象
     */
    private Object target;

    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //(1)
        System.out.println("-----------------begin " + method.getName() + "-----------------");
        //(2)
        Object result = method.invoke(target, args);
        //(3)
        System.out.println("-----------------end " + method.getName() + "-----------------");
        return result;
    }

    public Object getProxy() {
        //(4)
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }


    public static void main(String[] args) {

        //(5)打开这个开关，可以把生成的代理类保存到磁盘
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //(6)创建目标对象（被代理对象）
        UserService service = new UserServiceImpl();
        //(7)创建一个InvocationHandler实例，并传递被代理对象
        MyInvocationHandler handler = new MyInvocationHandler(service);
        //(8)生成代理类
        UserService proxy = (UserService) handler.getProxy();
        proxy.add();
    }
}
