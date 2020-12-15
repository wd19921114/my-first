package cn.ppz111.my.viewentity.post;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Yuki
 */
@Data
public class PostVO {
    private Long postId;
    private Long userId;
    private String postName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private Integer status;
    private String postUrl;
    private String title;
    private Integer praiseNum;
    private Integer whetherPraise;
}
