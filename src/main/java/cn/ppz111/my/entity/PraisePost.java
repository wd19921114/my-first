package cn.ppz111.my.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Yuki
 */
@Data
public class PraisePost {
    @TableId
    private Long id;
    private Long userId;
    private Long postId;
}
