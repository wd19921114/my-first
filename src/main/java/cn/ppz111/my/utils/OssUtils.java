package cn.ppz111.my.utils;

import cn.ppz111.my.config.OssConfig;
import cn.ppz111.my.enums.ErrorEnum;
import cn.ppz111.my.exception.CgErrorException;
import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Yuki
 */


@Component
@Slf4j
@Data
public class OssUtils {
    private final String REGION_ID = "cn-beijing";
    private final OssConfig setting;
    private final int REP_OK = 200;

    @Autowired
    public OssUtils(OssConfig setting) {
        this.setting = setting;
    }

    /**
     * 从服务器上传图片
     *
     * @param bucketName 存储空间名称
     * @param fileName   文件相对路径名称
     * @param input      输入流
     * @return 文件的url地址
     */
    private String uploadImg(String bucketName, String fileName, InputStream input) {
        OSSClient client = new OSSClient(setting.getEndpoint(), setting.getAccessKeyId(), setting.getAccessKeySecret());
        try {
            if (!client.doesBucketExist(bucketName)) {
                log.error("OSS异常bucketName:{}不存在", bucketName);
                throw new CgErrorException(ErrorEnum.OSS_ERROR);
            }
            client.putObject(bucketName, fileName, input);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.shutdown();
        }
        return setting.getPrefix().concat("/").concat(fileName);
    }

    /**
     * 批量上传图片
     *
     * @param dir   目录
     * @param files 文件
     * @return 文件路径集合
     */
    public List<String> upLoadImg(String dir, MultipartFile[] files) {
        List<String> imgUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            String newFileName = dir + "/" + IdWorker.getId();
            File tempFile = null;
            FileInputStream is = null;
            try {
                tempFile = File.createTempFile(newFileName, ".jpeg");
                file.transferTo(tempFile);
                is = new FileInputStream(tempFile);
                imgUrls.add(uploadImg(setting.getBucketName(), newFileName + ".jpeg", is));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Objects.requireNonNull(tempFile).delete();
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imgUrls;
    }

}


