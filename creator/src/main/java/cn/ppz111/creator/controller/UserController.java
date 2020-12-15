package cn.ppz111.creator.controller;

import cn.ppz111.creator.service.UserService;
import cn.ppz111.creator.utils.AdminUtil;
import cn.ppz111.creator.utils.ResultUtil;
import cn.ppz111.creator.viewentity.ResultVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yuki
 */
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("getUserName")
    public ResultVO getUserName(){
        return ResultUtil.success(AdminUtil.getAdmin());
    }
}
