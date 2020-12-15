package cn.ppz111.my.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Yuki
 */
@Data
public class MyPost {
    @TableId
    private Long postId;
    private Long userId;
    private String postName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private Integer status;
    private String postUrl;
    private String title;
}
