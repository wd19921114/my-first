package cn.ppz111.my.service.impl;

import cn.ppz111.my.service.EchoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Yuki
 */
@Service
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String string) {
        return "welcome --" + string;
    }

    @Override
    public String echoDubboMix(int id) {
        System.out.println("id:"+id);
        return null;
    }

    @Override
    public String echoMQConsumer(String str) {
        System.out.println("订阅消息:"+str);
        return null;
    }
}
