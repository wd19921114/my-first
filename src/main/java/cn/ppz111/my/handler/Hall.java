package cn.ppz111.my.handler;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yuki
 */
@Component
public class Hall implements HandlerFilterFunction {

    @Override
    public ServerResponse filter(ServerRequest serverRequest, HandlerFunction handlerFunction) throws Exception {
        System.out.println("filter.serverRequest"+serverRequest);
        System.out.println("filter.handlerFunction"+serverRequest);
        return null;
    }

    @Override
    public HandlerFilterFunction andThen(HandlerFilterFunction after) {
        System.out.println("andThen.after"+after);
        System.out.println("andThen.after"+after);
        return null;
    }

    @Override
    public HandlerFunction apply(HandlerFunction handler) {
        System.out.println("apply.handler"+handler);
        System.out.println("apply.handler"+handler);
        return null;
    }



}
