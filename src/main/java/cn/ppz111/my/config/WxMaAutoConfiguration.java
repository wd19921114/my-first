package cn.ppz111.my.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2020/1/14
 */
@Configuration
@ConditionalOnClass(WxMaService.class)
@EnableConfigurationProperties(WxMaProperties.class)
public class WxMaAutoConfiguration {

    private final WxMaProperties wxMaProperties;

    @Autowired
    public WxMaAutoConfiguration(WxMaProperties wxMaProperties) {
        this.wxMaProperties = wxMaProperties;
    }

    /**
     * 小程序service.
     *
     * @return 小程序service
     */
    @Bean
    @ConditionalOnMissingBean(WxMaService.class)
    public WxMaService service() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(StringUtils.trimToNull(this.wxMaProperties.getAppid()));
        config.setSecret(StringUtils.trimToNull(this.wxMaProperties.getSecret()));
        WxMaServiceImpl service = new WxMaServiceImpl();
        service.setWxMaConfig(config);
        return service;
    }
}
