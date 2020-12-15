package cn.ppz111.my.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author Yuki
 */
@Getter
public enum  UserStatusEnum {
    /**
     *
     */
    NORMAL(0,"正常"),
    FORBID(1,"禁用"),
    ;

    @EnumValue
    private Integer code;

    private String message;

    UserStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }
}
