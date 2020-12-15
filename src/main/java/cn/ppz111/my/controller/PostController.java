package cn.ppz111.my.controller;

import cn.ppz111.my.viewentity.ResultVO;
import cn.ppz111.my.service.PostService;
import cn.ppz111.my.utils.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yuki
 */
@RestController
@RequestMapping("post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("addPost")
    public ResultVO addPost(@RequestParam(value = "postName") String postName, String postUrl, String postDetail) {
        postService.addPost(postName, postUrl, postDetail);
        return ResultUtil.success();
    }

    @PostMapping("postList")
    public ResultVO postList() {
        return ResultUtil.success(postService.postList());
    }

    @PostMapping(value = "/login")
    public Object login() {
        return postService.login(1);
    }

    @PostMapping(value = "/hotList")
    public ResultVO hotList() {
        return ResultUtil.success(postService.hotList());
    }

    /**
     * 帖子点赞
     * @param postId post
     */
    @PostMapping(value = "praisePost")
    public ResultVO praisePost(@RequestParam Long postId) {
        return ResultUtil.success(postService.praisePost(postId));
    }

}
