package cn.ppz111.my.controller;

import cn.ppz111.my.viewentity.ResultVO;
import cn.ppz111.my.service.UserService;
import cn.ppz111.my.utils.ResultUtil;
import com.alibaba.nacos.api.annotation.NacosInjected;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yuki
 */

@RestController
@RequestMapping(value = "admin")
public class UserController {
    private final StringRedisTemplate redisTemplate;
    private final UserService userService;

    public UserController(StringRedisTemplate redisTemplate, UserService userService) {
        this.redisTemplate = redisTemplate;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResultVO login(@RequestParam(value = "phone") String phone, @RequestParam(value = "passWd") String passWd) {
        return ResultUtil.success(userService.login(phone, passWd));
    }

    @PostMapping("getUserInfo")
    public ResultVO getUserInfo() {
        return ResultUtil.success(userService.getUserInfo());
    }

    @PostMapping("addLogin")
    public ResultVO addLogin(@RequestParam(value = "phone") String phone, @RequestParam(value = "nickname") String nickname) {
        return ResultUtil.success(userService.addLogin(phone, nickname));
    }

    @PostMapping("bigInsert")
    public ResultVO bigInsert(String list) {
        userService.bigInsert(list);
        return ResultUtil.success();
    }



}

