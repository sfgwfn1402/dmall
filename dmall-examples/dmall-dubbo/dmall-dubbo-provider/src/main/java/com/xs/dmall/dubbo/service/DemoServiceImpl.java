package com.xs.dmall.dubbo.service;


import org.springframework.stereotype.Service;


@Service("demoService")
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return String.format("[%s] : Hello, %s", "111", name);
    }
}
