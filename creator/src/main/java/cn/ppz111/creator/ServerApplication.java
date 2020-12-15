package cn.ppz111.creator;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yuki
 */
@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class ServerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("服务提供者---->启动完毕");
    }
    @NacosValue(value = "${service.name:1}", autoRefreshed = true)
    private String serverName;

    @GetMapping("test")
    @ResponseBody
    public String get() {
        return serverName;
    }
}