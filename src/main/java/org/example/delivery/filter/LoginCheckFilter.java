package org.example.delivery.filter;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.delivery.common.BaseContext;
import org.example.delivery.common.R;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        String requestURI=request.getRequestURI();
        String[] urls=new String[]{
          "/employee/login",
          "/employee/logout",
          "/backend/**",
          "/front/**"
        };
        boolean check=check(urls,requestURI);

        if (check){
            filterChain.doFilter(request,response);
            return;
        }
        if (request.getSession().getAttribute("employee")!=null){
            log.info("用户已登录,用户id为:{}",request.getSession().getAttribute("employee"));
            Long empId=(Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }

        response.getWriter().write(JSON.toJSONString(R.error("NOT LOGIN")));
        return;
    }
    public boolean check(String []urls,String requestURI){
        for (String url : urls) {
           boolean match= PATH_MATCHER.match(url,requestURI);
            if (match){
                return true;
            }

        }
        return false;
    }
}
