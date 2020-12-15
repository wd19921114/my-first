package cn.ppz111.my.mapper;

import cn.ppz111.my.entity.MyPost;
import cn.ppz111.my.viewentity.post.PostVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yuki
 */
@Repository
public interface PostMapper extends BaseMapper<MyPost> {
    /**
     * 帖子列表
     * @param userId 用户id
     * @return post
     */
    @Select("SELECT " +
            "*,(SELECT COUNT(pp.id) FROM praise_post pp WHERE pp.post_id=mp.`post_id` AND pp.user_id=#{userId}) AS whetherPraise," +
            "(SELECT COUNT(*) FROM praise_post pp WHERE pp.post_id=mp.`post_id`) AS praiseNum  FROM my_post mp where mp.status=0")
    List<PostVO> postList(Long userId);
}
