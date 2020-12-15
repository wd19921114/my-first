package cn.ppz111.my.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.ppz111.my.constant.RedisConstant;
import cn.ppz111.my.exception.CgErrorException;
import cn.ppz111.my.viewentity.user.AdminLoginVO;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ppz111.my.entity.MyUser;
import cn.ppz111.my.enums.ErrorEnum;
import cn.ppz111.my.mapper.UserMapper;
import cn.ppz111.my.service.UserService;
import cn.ppz111.my.utils.AdminUtil;
import cn.ppz111.my.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Yuki
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, MyUser> implements UserService  {
    private final StringRedisTemplate redisTemplate;

    public UserServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String login(String phone, String passWd){
        MyUser one = getOne(Wrappers.<MyUser>lambdaQuery().eq(MyUser::getPhone, phone));
        if(ObjectUtils.isEmpty(one)){
            throw new CgErrorException(ErrorEnum.USER_NOT_EXIST);
        }
        if(!DigestUtil.md5Hex(passWd).equals(one.getPassWd())){
            throw new CgErrorException(ErrorEnum.USER_NOT_EXIST);
        }
        AdminLoginVO vo=new AdminLoginVO();
        BeanUtils.copyProperties(one,vo);
        String token = JwtUtil.getToken(one.getUserId().toString());
        vo.setToken(token);
        redisTemplate.opsForValue().set(String.format(RedisConstant.USER, one.getUserId()), JSON.toJSONString(vo),2592000, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(String.format("life_value%s", one.getUserId()),"100");
        return token;



    }

    @Override
    public String addLogin(String phone, String nickname) {
        Integer integer = baseMapper.selectCount(Wrappers.<MyUser>lambdaQuery().eq(MyUser::getPhone, phone));
        if(integer>0){
            throw new CgErrorException(555,"用户已存在");
        }
        MyUser user=new MyUser();
        user.setPhone(phone);
        user.setNickname(nickname);
        user.setPassWd(DigestUtil.md5Hex("123456"));
        baseMapper.insert(user);
        return "账号:"+phone+",密码:"+"123456";
    }

    @Override
    public void bigInsert(String list) {
        for(int a=0;a<20000;a++){
            list=list.concat(",").concat(String.valueOf(a));
        }
        String[] split = list.split(",");
        String join = String.join("),(", split);
        baseMapper.bigInsert("("+join+")");
        StringBuilder stringBuilder=new StringBuilder();
        StringBuffer stringBuffer=new StringBuffer();
        TreeMap map=new TreeMap();
        Pageable pageable= PageRequest.of(1,2);
        NativeSearchQueryBuilder builder=new NativeSearchQueryBuilder();
        builder.withPageable(pageable);
        builder.withFields("test");
    }

    @Override
    public AdminLoginVO getUserInfo() {
        return AdminUtil.getAdmin();
    }


}
