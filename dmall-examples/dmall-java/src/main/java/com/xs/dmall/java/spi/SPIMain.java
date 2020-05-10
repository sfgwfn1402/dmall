package com.xs.dmall.java.spi;

import org.slf4j.Logger;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SPIMain {
    public static void main(String[] args){
        ServiceLoader<Logger> serviceLoader = ServiceLoader.load(Logger.class);
//        serviceLoader.forEach(Logger::getName);
        Iterator<Logger> ite = serviceLoader.iterator();
        while (ite.hasNext()){
            Logger logger = ite.next();
            logger.getName();
        }
    }
}
