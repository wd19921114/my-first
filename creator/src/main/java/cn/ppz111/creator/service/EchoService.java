package cn.ppz111.creator.service;

/**
 * @author Yuki
 */
public interface EchoService {
    String echo(String string);

    String echoDubboMix(int id);

    String echoMQConsumer(String str);
}
