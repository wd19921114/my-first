package cn.ppz111.my.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description:
 *
 * @author : Yuki
 * @date : 2020/6/23
 */
@Data
@AllArgsConstructor
public class CommonDelayMsg {
    private DelayMsgEnum msgType;
    private Long targetId;
}
