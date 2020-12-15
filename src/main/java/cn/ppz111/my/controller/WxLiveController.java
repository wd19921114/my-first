package cn.ppz111.my.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.ppz111.my.viewentity.ResultVO;
import cn.ppz111.my.viewentity.live.CreateRoomVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.ppz111.my.utils.AccessTokenUtil;
import cn.ppz111.my.utils.ResultUtil;
import cn.ppz111.my.viewentity.live.AddGoodsToRoomVO;
import cn.ppz111.my.viewentity.live.GoodsListVO;
import cn.ppz111.my.viewentity.live.GoodsVO;
import cn.ppz111.my.viewentity.live.goodsInfo;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

/**
 * @author Yuki
 */
@RestController
@RequestMapping("live")
@AllArgsConstructor
public class WxLiveController {
    private final WxMaService service;
    public static void main(String[] args) {
        long l = System.currentTimeMillis() / 1000;
        LocalDate date = LocalDate.now();
        long m = l + 1200;
        long n = l + 3600 * 6;
        System.out.println(l);
        System.out.println("20分钟后" + m);
        System.out.println("2小时后" + n);
        System.out.println("6小时后" + n);
    }

    @PostMapping("createRoom")
    public ResultVO createRoom(@Valid CreateRoomVO vo) throws WxErrorException {
        String accessToken = AccessTokenUtil.getAccessToken();
        String url = "https://api.weixin.qq.com/wxaapi/broadcast/room/create";

        service.post(url,JSONObject.toJSONString(vo));
        url = url.concat("?access_token=" + accessToken);
        String s = JSONObject.toJSONString(vo);
        return ResultUtil.success(JSON.parseObject(AccessTokenUtil.doPost(url, s)).get("roomId"));
    }

    @PostMapping("getAccessTokenFromRedis")
    public ResultVO getAccessTokenFromRedis() {
        return ResultUtil.success(AccessTokenUtil.getAccessToken());
    }

    @PostMapping("getRoom")
    public ResultVO getRoom() {
        String accessToken = AccessTokenUtil.getAccessToken();
        String url = "http://api.weixin.qq.com/wxa/business/getliveinfo?access_token=" + accessToken + "&start=0&limit=10";
        System.out.println(url);
        return ResultUtil.success(JSON.parseObject(AccessTokenUtil.doPost(url, "{}")));
    }

    @PostMapping("addGoods")
    public ResultVO addGoods(@Valid goodsInfo list) {
        String accessToken = AccessTokenUtil.getAccessToken();
        String url = "https://api.weixin.qq.com/wxaapi/broadcast/goods/add?access_token=" + accessToken;
        GoodsVO vo = new GoodsVO();
        vo.setGoodsInfo(list);
        String s = JSONObject.toJSONString(vo);
        return ResultUtil.success(JSON.parseObject(AccessTokenUtil.doPost(url, s)));
    }
    @PostMapping("addGoodsToRoom")
    public ResultVO addGoodsToRoom(@Valid AddGoodsToRoomVO list) {
        String accessToken = AccessTokenUtil.getAccessToken();
        String url = "https://api.weixin.qq.com/wxaapi/broadcast/goods/add?access_token=" + accessToken;
        String s = JSONObject.toJSONString(list);
        return ResultUtil.success(JSON.parseObject(AccessTokenUtil.doPost(url, s)));
    }
    @PostMapping("getGoodsList")
    public ResultVO getGoodsList(Integer offset, Integer limit,Integer status) {
        String accessToken = AccessTokenUtil.getAccessToken();
        String url = "https://api.weixin.qq.com/wxaapi/broadcast/goods/getapproved?access_token=" + accessToken+"&status="+status;
        JSON.parseObject(AccessTokenUtil.doGet(url)).get("data");
        GoodsListVO vo=new GoodsListVO();
        return ResultUtil.success(JSON.parseObject(AccessTokenUtil.doGet(url)));
    }

}
