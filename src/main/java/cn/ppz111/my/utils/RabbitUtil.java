package cn.ppz111.my.utils;

import cn.ppz111.my.enums.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Yuki
 */
@Component
@Slf4j
public class RabbitUtil {
    private static AmqpTemplate amqpTemplate;
    @Autowired
    public void getRabbitUtil(AmqpTemplate amqpTemplate) {
        RabbitUtil.amqpTemplate = amqpTemplate;
    }

    /**
     * 不同消息
     */
    public static void sendMq(String msg){
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_QQ.getExchangeName(),QueueEnum.QUEUE_QQ.getPath(),msg);
    }
    /**
     * 延迟消息
     */
    public static void sendLateMq(String msg,Long time){
        System.out.println(time.toString());
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_DELAY_QQ.getExchangeName(),QueueEnum.QUEUE_DELAY_QQ.getPath(),msg,message -> {
            message.getMessageProperties().setExpiration(time.toString());
            return message;
        });
    }
}
