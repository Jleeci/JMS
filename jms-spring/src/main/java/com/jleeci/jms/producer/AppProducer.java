package com.jleeci.jms.producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Jleeci on 2018/5/10.
 */
public class AppProducer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("producer.xml");
        ProducerService service=context.getBean(ProducerService.class);
        for (int i = 0; i < 100; i++) {
            service.sendMessage("test"+i);
        }
        context.close();
    }
}
