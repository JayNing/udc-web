package com.mascot.bean;

import com.thrift.common.body.UserInfo;
import com.thrift.common.define.AppType;
import com.thrift.common.define.PlatformType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * session
 * Created by Jerry on 2017/7/27.
 */
public class SessionInfo {

    private String tokenId;
    private UserInfo userInfo;
    private Integer flag = 1;               // 状态标记, 1: 正常, 2: 临时
    private List<PlatformType> platformType;    // 已登录的平台列表
    private AppType appType;
    private List<String> authorityCodeList = new ArrayList<>();  // 权限码列表

    public SessionInfo() {
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<PlatformType> getPlatformType() {
        return platformType;
    }

    public void setPlatformType(List<PlatformType> platformType) {
        this.platformType = platformType;
    }

    public AppType getAppType() {
        return appType;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
    }

    public List<String> getAuthorityCodeList() {
        return authorityCodeList;
    }

    public void setAuthorityCodeList(List<String> authorityCodeList) {
        this.authorityCodeList = authorityCodeList;
    }
}
