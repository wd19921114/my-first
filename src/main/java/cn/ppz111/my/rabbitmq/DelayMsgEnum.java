package cn.ppz111.my.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:
 *
 * @author : Yuki
 * @date : 2020/6/23
 */
@Getter
@AllArgsConstructor
public enum DelayMsgEnum {
    /**
     * 延迟消息类型枚举
     */
    VIP_START(0,"会员生效"),
    VIP_END(1,"会员结束"),
    ;
    private Integer code;
    private String msg;
}
