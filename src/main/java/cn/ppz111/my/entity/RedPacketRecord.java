package cn.ppz111.my.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author Yuki
 */
@Data
public class RedPacketRecord {
    @TableId
    private Long id;
    private Integer amount;
    private Integer money;
    private Long redPacketId;
    private Integer uid;
    private Date createTime;
}
