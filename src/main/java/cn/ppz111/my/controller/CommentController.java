package cn.ppz111.my.controller;

import cn.ppz111.my.viewentity.ResultVO;
import cn.ppz111.my.service.CommentService;
import cn.ppz111.my.utils.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yuki
 */
@RestController
@RequestMapping(value = "comment")

public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("setComment")
    public ResultVO setComment(@RequestParam(value = "userId") Long userId, @RequestParam(value = "msg") String msg) {
        commentService.setComment(userId, msg);
        return ResultUtil.success();
    }

    @PostMapping("commentList")
    public ResultVO commentList() {
        return ResultUtil.success(commentService.commentList());
    }
}
