package cn.ppz111.my.controller;

import cn.ppz111.my.service.RedRacketService;
import cn.ppz111.my.utils.ResultUtil;
import cn.ppz111.my.viewentity.ResultVO;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * @author Yuki
 */
@RestController
@AllArgsConstructor
@RequestMapping("red")
public class RedRacketController {
    private final RedRacketService redRacketService;
    private final StringRedisTemplate redisTemplate;
    /**
     * 抢红包
     */
    @PostMapping("startTwo")
    public ResultVO startTwo(long redPacketId){
        redRacketService.startTwo(redPacketId);
        return ResultUtil.success();
    }
    /**
     * 抢红包自己来
     */
    @PostMapping("getRedPacket")
    public ResultVO getRedPacket(long redPacketId){
        redRacketService.getRedPacket(redPacketId);
        return ResultUtil.success();
    }
}
