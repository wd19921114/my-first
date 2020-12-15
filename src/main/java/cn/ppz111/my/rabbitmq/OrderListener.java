package cn.ppz111.my.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author : Yuki
 * @date : 2020/6/3
 */
@Component
@RabbitListener(queues = "asd")
public class OrderListener {


    @RabbitHandler
    public void handle(String msg){
        System.out.println("接收到了消息:"+msg);
    }
}
