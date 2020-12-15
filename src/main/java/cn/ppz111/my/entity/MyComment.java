package cn.ppz111.my.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.context.annotation.Scope;

/**
 * @author Yuki
 */
@Data
public class MyComment {
    @TableId
    private Long commentId;
    private Long userId;
    private String msg;
}
