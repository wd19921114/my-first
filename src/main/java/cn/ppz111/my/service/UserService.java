package cn.ppz111.my.service;

import cn.ppz111.my.viewentity.user.AdminLoginVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ppz111.my.entity.MyUser;

/**
 * @author Yuki
 */
public interface UserService extends IService<MyUser> {
    /**
     * 登陆
     * @param phone 电话
     * @param passWd 密码
     * @return String
     */
    String  login(String phone, String passWd);

    /**
     * 新增
     * @param phone 电话
     * @param nickname 昵称
     * @return return
     */
    String addLogin(String phone, String nickname);

    /**
     * 列表
     * @param list list
     */
    void bigInsert(String list);

    /**
     * info
     * @return info
     */
    AdminLoginVO getUserInfo();
}
