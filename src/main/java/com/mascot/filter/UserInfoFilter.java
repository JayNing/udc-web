package com.mascot.filter;


import com.interfaces.usercenter.UserInfoService;
import com.mascot.bean.SessionInfo;
import com.mascot.utils.common.SpringBean;
import com.thrift.common.body.UserSessionInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "userInfoFilter", urlPatterns = "/*")
public class UserInfoFilter implements Filter {

    private static final Log logger = LogFactory.getLog(UserInfoFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("======================UserInfoFilter init====================");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getServletPath();

        //排除不受下线过滤器影响。
        String regex = getSkipUrlRegex();
        if (url.matches(regex)){
            chain.doFilter(request, response);
            return;
        }

        if (session.getAttribute("onlineInfo") == null){
            skip(req, resp, "offline", chain);
        }else {
            SessionInfo online = (SessionInfo) session.getAttribute("onlineInfo");
            if (online == null || !StringUtils.hasText(online.getTokenId())) {
                skip(req, resp, "offline", chain);
            }else {
                UserInfoService userInfoService = (UserInfoService) SpringBean.getObject("userInfoService");
                UserSessionInfo userSessionInfo = userInfoService.verifyLoginByTokenId(online.getTokenId());
                if (userSessionInfo != null){
                    chain.doFilter(request, response);
                }else {
                    session.removeAttribute("onlineInfo");
                    skip(req, resp, "offline", chain);
                }
            }
        }


    }
    /**
     * TODO 根据具体情况编写
     * @param req
     * @param resp
     * @param offline
     * @param chain
     * @throws IOException
     */
    private void skip(HttpServletRequest req, HttpServletResponse resp, String offline, FilterChain chain)
            throws IOException {
        //默认跳转至登录页面,/login
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    public void destroy() {
        logger.info("======================UserInfoFilter destroy====================");
    }

    //被忽略的URL的正则表达式
    private String getSkipUrlRegex(){
        return new StringBuilder(
                "(?i).*?((\\.(jpg|gif|bmp|png|js|css|txt|ico))"
                        + "|(((error/\\d{3})|login)\\.html)"
                        + "|(/(login|logOut)))"
        ).toString();
    }
}
