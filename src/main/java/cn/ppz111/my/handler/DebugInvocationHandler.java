package cn.ppz111.my.handler;

import cn.ppz111.my.mapper.PraisePostMapper;
import cn.ppz111.my.service.impl.PostServiceImpl;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Yuki
 */
public class DebugInvocationHandler implements InvocationHandler {
    /**
     * 代理类中的真实对象
     */
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object invoke = method.invoke(target, objects);
        return invoke;
    }
    public static Object getProxy(Object target){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (java.lang.reflect.InvocationHandler) new DebugInvocationHandler( target)
        );
    }

    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
    }
}
