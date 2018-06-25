package com.mascot.utils;

import com.mascot.bean.SessionInfo;
import com.thrift.common.body.UserInfo;

import javax.servlet.http.HttpServletRequest;

public class SessionGetUtil {
    public static UserInfo getSession(HttpServletRequest request) {
        UserInfo userInfo ;

        //从session中获取用户信息onlineInfo
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("onlineInfo");
        if (sessionInfo != null) {
            userInfo = sessionInfo.getUserInfo();
        }else{
            userInfo = null;
        }
        return userInfo;
    }
}