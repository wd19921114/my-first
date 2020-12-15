package cn.ppz111.my.service;

/**
 * @author Yuki
 */
public interface EchoService {
    String echo(String string);

    String echoDubboMix(int id);

    String echoMQConsumer(String str);
}
