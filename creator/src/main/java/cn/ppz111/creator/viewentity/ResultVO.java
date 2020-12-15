package cn.ppz111.creator.viewentity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Yuki
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = 6472058107952096432L;
    /** 错误码*/
    private  Integer code;

    private String msg;

    private T data;
}
