package cn.luxinhuo.concurrent_coding.stage1.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("经过拦截器的prehandle");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("经过拦截器afterCompletion，现在移除ThreadLocal中线程 {} 的id",Thread.currentThread().getId());
        RequestHolder.remove();
        log.info(RequestHolder.getRequestHolder().toString());
    }
}
