package com.jleeci.jms.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Jleeci on 2018/5/11.
 */
public class AppConsumer {

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("consumer.xml");
    }
}
