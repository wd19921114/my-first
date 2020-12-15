package cn.ppz111.my.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2020/1/14
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMaProperties {
    /**
     * 设置微信小程序的appid.
     */
    private String appid;

    /**
     * 设置微信小程序的Secret.
     */
    private String secret;

}
