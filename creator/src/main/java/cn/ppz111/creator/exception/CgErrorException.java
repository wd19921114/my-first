package cn.ppz111.creator.exception;

import cn.ppz111.creator.enums.ErrorEnum;
import lombok.Getter;

/**
 * @author Yuki
 */
@Getter
public class CgErrorException extends RuntimeException {
    private static final long serialVersionUID = 6370710256089347961L;

    private Integer code;

    public CgErrorException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
    }

    public CgErrorException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
