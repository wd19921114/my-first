package cn.ppz111.my.utils;

import cn.ppz111.my.viewentity.ResultVO;
import cn.ppz111.my.enums.ErrorEnum;

/**
 * @author Yuki
 */
public class ResultUtil {
    public static ResultVO<Object> success(Object object){
        ResultVO<Object> resultVO=new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setData(object);
        resultVO.setMsg("success");
        return resultVO;
    }
    public static ResultVO<Object> success(){
        return success(null);
    }
    public static ResultVO error(Integer code, String message) {
        ResultVO<Object> resultVO=new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(message);
        return  resultVO;
    }
    public static ResultVO<Object> error(ErrorEnum error) {
        ResultVO<Object> resultVO=new ResultVO<>();
        resultVO.setCode(error.getCode());
        resultVO.setMsg(error.getMessage());
        return  resultVO;
    }
}
