package cn.ppz111.my.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Yuki
 */
@Data
public class ViewPager {
    @TableId
    private Long id;
    private String name;
    private String src;
}
