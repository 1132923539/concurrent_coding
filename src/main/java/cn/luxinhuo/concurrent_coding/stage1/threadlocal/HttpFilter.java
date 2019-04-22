package cn.luxinhuo.concurrent_coding.stage1.threadlocal;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("过滤器进行 do httpFilter，并存入线程id ThreadId:{}, url: {}",Thread.currentThread().getId(),request.getRequestURI());

        //将当前线程的Id存入ThreadLocal变量中
        RequestHolder.add(Thread.currentThread().getId());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("销毁HttpFilter");
    }
}
