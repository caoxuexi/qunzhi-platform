package com.xidian.qunzhi.core.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xidian.qunzhi.exception.http.UnAuthenticatedException;
import com.xidian.qunzhi.pojo.User;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import com.xidian.qunzhi.utils.LoginUserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 打印请求信息
        LOG.info("------------- LoginInterceptor 开始 -------------");
        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);

        // OPTIONS请求不做校验,
        // 前后端分离的架构, 前端会发一个OPTIONS请求先做预检, 对预检请求不做校验
        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;
        }

        String path = request.getRequestURL().toString();
        LOG.info("接口登录拦截：，path：{}", path);

        //获取header的token参数
        String token = request.getHeader("token");
        LOG.info("登录校验开始，token：{}", token);
        if (token == null || token.isEmpty()) {
            LOG.info( "token为空，请求被拦截" );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCharacterEncoding("utf-8");
            throw new UnAuthenticatedException(10007);
        }
        Object object = redisTemplate.opsForValue().get(token);
        if (object == null) {
            LOG.warn( "token无效，请求被拦截" );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCharacterEncoding("utf-8");
            throw new UnAuthenticatedException(10004);
        } else {
            LOG.info("已登录：{}", object);
            //序列化回结构体，存入LoginUserContext的localthread中
            UserLoginVO user=JSON.parseObject((String) object, UserLoginVO.class);
            LoginUserContext.setUser(user);
            redisTemplate.opsForValue().set(token, JSONObject.toJSONString(user),
                    60 * 30, TimeUnit.SECONDS);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("requestStartTime");
        LOG.info("------------- LoginInterceptor 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        LOG.info("LogInterceptor 结束");
    }
}
