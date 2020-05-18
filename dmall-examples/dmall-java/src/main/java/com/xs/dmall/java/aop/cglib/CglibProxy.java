package com.xs.dmall.java.aop.cglib;

import com.xs.dmall.java.aop.service.UserService;
import com.xs.dmall.java.aop.service.UserServiceImpl;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    //(10)
    private Enhancer enhancer = new Enhancer();

    //(11)
    public Object getProxy(Class clazz) {
        //（12）设置被代理类的Class对象
        enhancer.setSuperclass(clazz);
        // (13)设置拦截器回调
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println(obj.getClass().getName() + "." + method.getName());
        Object result = proxy.invokeSuper(obj, args);
        return result;
    }

    public static void main(String[] args) {
        //（14）生成代理类到本地
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/duwei/Documents/workspace/dmall/dmall-examples/dmall-java/src/main/java/com/xs/dmall/java/aop/cglib");
        //(15)生成目标对象
        UserServiceImpl service = new UserServiceImpl();
        //（16）创建CglibProxy对象
        CglibProxy cp = new CglibProxy();
        //(17)生成代理类
        UserService proxy = (UserService) cp.getProxy(service.getClass());
        proxy.add();
    }
}
