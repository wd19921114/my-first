package cn.ppz111.my.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author Yuki
 */
@Data
@Configuration
@ConfigurationProperties(prefix="oss")
public class OssConfig {
    /**
     * accessKey
     */
    private String accessKeyId;
    /**
     * accessKeySecret
     */
    private String accessKeySecret;
    /**
     * 节点
     */
    private String endpoint;
    /**
     * 存储空间名称
     */
    private String bucketName;
    /**
     * 文件大小上限,单位M
     */
    private Integer maxSize;
    /**
     * 回调地址
     */
    private String callback;
    /**
     * 签名有效时间,单位s
     */
    private Integer expire;
    /**
     * 图片目录
     */
    private String dir;
    /**
     * 路径前缀
     */
    private String prefix;
}
