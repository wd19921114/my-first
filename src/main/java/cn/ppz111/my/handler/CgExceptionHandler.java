package cn.ppz111.my.handler;


import cn.ppz111.my.exception.CgErrorException;
import cn.ppz111.my.viewentity.ResultVO;
import cn.ppz111.my.utils.ResultUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author : wanghao
 * @date : 2018-07-17
 */
@RestControllerAdvice
public class CgExceptionHandler {

    @ExceptionHandler(value = CgErrorException.class)
    public ResultVO handlerOrderException(CgErrorException e){
        return ResultUtil.error(e.getCode(),e.getMessage());
    }
}
