package cn.ppz111.creator.enums;

import lombok.Getter;

/**
 * @author Yuki
 */
@Getter
public enum ErrorEnum {
    /**
     * 错误码
     */
    USER_NOT_EXIST(10000,"用户不存在"),
    PW_IS_WRONG(10001,"密码错误"),
    OSS_ERROR(52001,"OSS存储异常"),
    COMMENT_IS_EXIST(52002,"您已经留下过祝福啦"),
    ;

    private Integer code;

    private String message;

    ErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
