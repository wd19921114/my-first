package cn.ppz111.my.controller;

import cn.ppz111.my.viewentity.ResultVO;
import cn.ppz111.my.service.ViewPagerService;
import cn.ppz111.my.utils.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yuki
 */
@RestController
@RequestMapping("page")
public class ViewPagerController {
    private final ViewPagerService pagerService;

    public ViewPagerController(ViewPagerService pagerService) {
        this.pagerService = pagerService;
    }
    @PostMapping("addViewPage")
    public ResultVO addViewPage(@RequestParam(value = "src")String src, @RequestParam(value = "name")String name){
        pagerService.addViewPage(src,name);
        return ResultUtil.success();
    }
    @PostMapping("viewPageList")
    public ResultVO viewPageList(){
        return ResultUtil.success(pagerService.viewPageList());
    }
    @PostMapping("updateViewPage")
    public ResultVO<Object> updateViewPage(@RequestParam(value = "index")Integer index, String src, String name){
        pagerService.updateViewPage(index,src,name);
        return ResultUtil.success();
    }
}
