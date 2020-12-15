package cn.ppz111.my.enums;

import lombok.Getter;

/**
 * @author Yuki
 */
@Getter
public enum QueueEnum {
    /**
     *
     */
    QUEUE_QQ("asd","asd","asd"),
    QUEUE_DELAY_QQ("qwe","qwe","qwe"),
    DELAY_QQ("zxc","zxc","zxc"),

    ;
    private String exchangeName;
    private String queueName;
    private String path;
    QueueEnum(String exchangeName,String queueName,String path){
        this.exchangeName=exchangeName;
        this.queueName=queueName;
        this.path=path;
    }
}
