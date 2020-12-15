package cn.ppz111.my.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Yuki
 */
@Data
public class RedRacket {
    @TableId
    private Long id;
    private Long redPacketId;
    private Integer totalAmount;
    private Integer totalPacket;
    private Integer type;
    private Date createTime;
    private Integer version;
}
