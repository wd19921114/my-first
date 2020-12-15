package cn.ppz111.my.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.ppz111.my.entity.MyUser;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @author Yuki
 */
@Repository
public interface UserMapper extends BaseMapper<MyUser> {
    /**
     *  ss
     * @param list s
     */
    @Insert("INSERT INTO my_user (nickname) VALUES  ${list}")
    void bigInsert(String list);
}
