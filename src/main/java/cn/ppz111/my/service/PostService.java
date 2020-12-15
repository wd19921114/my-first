package cn.ppz111.my.service;

import cn.ppz111.my.viewentity.post.PostVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ppz111.my.entity.MyPost;

import java.util.List;

/**
 * @author Yuki
 */
public interface PostService extends IService<MyPost> {
    /**
     * xinz
     * @param postName s
     * @param postUrl s
     * @param postDetail s
     */
    void addPost(String postName,String postUrl,String postDetail);

    /**
     * ssss
     * @return s
     */
    List<PostVO> postList();

    Object login(int i);

    /**
     * 排行榜
     * @return list
     */
    List<String> hotList();

    /**
     * 帖子点赞
     * @param postId postId
     */
    Integer praisePost(Long postId);
}
