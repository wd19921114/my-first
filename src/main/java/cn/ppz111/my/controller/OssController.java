package cn.ppz111.my.controller;

import cn.ppz111.my.viewentity.ResultVO;
import cn.ppz111.my.utils.OssUtils;
import cn.ppz111.my.utils.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yuki
 */
@RestController
@RequestMapping(value = "oss")
public class OssController {
    private final OssUtils ossUtil;

    public OssController(OssUtils ossUtil) {
        this.ossUtil = ossUtil;
    }
    /**
     * 服务端上传图片
     * @param img 图片文件
     * @param dir 目录
     * @return 图片链接
     */
    @PostMapping("uploadImg")
    public ResultVO uploadImg(@RequestParam("img") MultipartFile[] img, @RequestParam("dir") String dir){
        return ResultUtil.success(ossUtil.upLoadImg(dir,img));
    }

}
