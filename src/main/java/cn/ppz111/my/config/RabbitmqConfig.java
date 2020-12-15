package cn.ppz111.my.config;

import cn.ppz111.my.enums.QueueEnum;
import com.rabbitmq.client.impl.CredentialsProvider;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Description:
 *
 * @author : Yuki
 * @date : 2020/6/3
 */
@Configuration
public class RabbitmqConfig {

    private static final String  INSTANCE_ID="amqp-cn-m7r1okdpw123";

    private final RabbitProperties rabbitProperties;

    public RabbitmqConfig(RabbitProperties rabbitProperties) {
        this.rabbitProperties = rabbitProperties;
    }


    @Bean
    @Profile("prod")
    public ConnectionFactory getConnectionFactory() {
        com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory =
                new com.rabbitmq.client.ConnectionFactory();
        rabbitConnectionFactory.setHost(rabbitProperties.getHost());
        rabbitConnectionFactory.setPort(rabbitProperties.getPort());
        rabbitConnectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());
        rabbitConnectionFactory.setAutomaticRecoveryEnabled(true);
        rabbitConnectionFactory.setNetworkRecoveryInterval(5000);
        return new CachingConnectionFactory(rabbitConnectionFactory);
    }

    /**
     * 通用路由
     */
    @Bean
    public DirectExchange commonExchange(){
        return (DirectExchange)ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_QQ.getExchangeName())
                .durable(true)
                .build();
    }


    /**
     * 延迟队列路由
     */
    @Bean
    public DirectExchange delayExchange(){
        return (DirectExchange)ExchangeBuilder
                .directExchange(QueueEnum.DELAY_QQ.getExchangeName())
                .durable(true)
                .build();
    }
    /**
     * 延迟队列私信路由
     */
    @Bean
    public DirectExchange delayTtlExchange(){
        return (DirectExchange)ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_DELAY_QQ.getExchangeName())
                .durable(true)
                .build();
    }

    /**
     * 通用队列
     */
    @Bean
    public Queue commonQueue(){
        return QueueBuilder
                .durable(QueueEnum.QUEUE_QQ.getQueueName())
                .build();
    }


    /**
     * 延迟队列
     */
    @Bean
    public Queue delayQueue(){
        return QueueBuilder
                .durable(QueueEnum.DELAY_QQ.getQueueName())
                .build();
    }
    /**
     * 延迟私信队列
     */
    @Bean
    public Queue delayTtlQueue(){
        return QueueBuilder
                .durable(QueueEnum.QUEUE_DELAY_QQ.getQueueName())
                .withArgument("x-dead-letter-exchange",QueueEnum.DELAY_QQ.getExchangeName())
                .withArgument("x-dead-letter-routing-key",QueueEnum.DELAY_QQ.getPath())
                .build();
    }

    /**
     * 将通用交换绑定到通用队列
     */
    @Bean
    Binding commonQueueBinding(){
        return BindingBuilder
                .bind(commonQueue())
                .to(commonExchange())
                .with(QueueEnum.QUEUE_QQ.getPath());
    }


    /**
     * 将延迟交换绑定到延迟队列
     */
    @Bean
    Binding delayQueueBinding(){
        return BindingBuilder
                .bind(delayQueue())
                .to(delayExchange())
                .with(QueueEnum.DELAY_QQ.getPath());
    }
    /**
     * 将私信延迟交换绑定到私信延迟队列
     */
    @Bean
    Binding delayQueueTtlBinding(){
        return BindingBuilder
                .bind(delayTtlQueue())
                .to(delayTtlExchange())
                .with(QueueEnum.QUEUE_DELAY_QQ.getPath());
    }
}
