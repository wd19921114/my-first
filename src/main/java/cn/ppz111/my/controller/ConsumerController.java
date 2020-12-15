package cn.ppz111.my.controller;

import cn.ppz111.my.service.EchoService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yuki
 */
@RestController
@RequestMapping(value = "/poc")
@AllArgsConstructor
public class ConsumerController {

//    @Reference
//    private EchoService echoService;
//
//    @RequestMapping(value = "/dubbo-echo-mid", method = RequestMethod.GET)
//    public String echo(@RequestParam("str") String str) {
//        return echoService.echo(str) + "\r\n";
//    }
//
//    @RequestMapping(value = "/dubbo-echo-mix", method = RequestMethod.GET)
//    public String echoDubboMix(@RequestParam("id") int id) {
//        return echoService.echoDubboMix(id) + "\r\n";
//    }
//
//    @RequestMapping(value = "/dubbo-echo-mq", method = RequestMethod.GET)
//    public String echoMQConsumer(@RequestParam("str") String str) {
//        return echoService.echoMQConsumer(str) + "\r\n";
//    }
//
//    @RequestMapping(value = "/echo-version", method = RequestMethod.GET)
//    public String echoVersion() {
//        return "This is dubbo spring boot version old" + "\r\n";
//    }
}