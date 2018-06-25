package com.mascot.controller;

import com.interfaces.mascot.ModuleManageService;
import com.interfaces.usercenter.UserInfoService;
import com.mascot.bean.ResultInfo;
import com.mascot.bean.SessionInfo;
import com.mascot.constant.UdcConstant;
import com.thrift.common.body.*;
import com.thrift.common.define.AppType;
import com.thrift.common.define.PlatformType;
import com.thrift.common.define.ResponseCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 平台用户登录
 *
 * @author jichao
 * 2018/5/08
 */
@Controller
@RequestMapping(value = "/userLogin")
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ModuleManageService moduleManageService;

    private static final Log logger = LogFactory.getLog(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo userLogin(HttpServletRequest request, String loginName, String passwd, Integer platformType) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            //检验传入参数的合法性
            if (!StringUtils.hasText(loginName) || !StringUtils.hasText(passwd) || null == platformType) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("userLogin平台用户登录参数不合法---->loginName==" + loginName + ",passwd==" + passwd + ",platformType==" + platformType);
                return resultInfo;
            }
            //参数检验合法,调用userCenter服务,检验返回值合法性

            UserSessionInfo login = userInfoService.login(loginName, passwd, PlatformType.findByValue(platformType), AppType.AppType_UDC_SYN);
            if (login != null) {
                switch (login.getResponseCode()) {
                    case Succeed:
                        resultInfo.setCode(login.getResponseCode().getValue());
                        loginSucceedDeal(request, login);
                        break;
                    case UserNotFound:  // 账号不存在
                        resultInfo.setCode(login.getResponseCode().getValue());
                        resultInfo.setMsg(UdcConstant.ACCOUNT_NOT_EXIST);
                        break;
                    case UserPasswordError: // 密码错误
                        resultInfo.setCode(login.getResponseCode().getValue());
                        resultInfo.setMsg(UdcConstant.USER_LOGIN_FAIL_MSG);
                        break;
                    case UserNotAccess:     // 该平台无法访问
                        resultInfo.setCode(login.getResponseCode().getValue());
                        resultInfo.setMsg(UdcConstant.ACCOUNT_NOT_AUTH);
                        break;
                    case Define:
                        resultInfo.setCode(login.getResponseCode().getValue());
                        resultInfo.setMsg(UdcConstant.ACCOUNT_LOGIN_FAIL);
                        break;
                }
            } else {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.ACCOUNT_LOGIN_FAIL);
            }
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("userLogin---web平台用户登录异常!");
            resultInfo.setMsg(UdcConstant.USER_LOGIN_FAIL);
            return resultInfo;
        }
    }

    /**
     * 登录成功处理
     *
     * @param request
     */
    private void loginSucceedDeal(HttpServletRequest request, UserSessionInfo login) {

        SessionInfo sessionInfo = new SessionInfo();

        sessionInfo.setTokenId(login.getCurrentTokenId());
        sessionInfo.setAppType(AppType.AppType_UDC_SYN);
        Iterator<UserSessionBasic> basicIterator = login.getBasicIterator();
        List<PlatformType> platformTypeList = new ArrayList<>();
        while (basicIterator.hasNext()) {
            platformTypeList.add(basicIterator.next().getPlatformType());
        }
        sessionInfo.setPlatformType(platformTypeList);
        sessionInfo.setUserInfo(login.getUserInfo());

        // 获取用户权限权限码列表
        List<ModuleInfo> moduleInfoList = moduleManageService.getModuleInfoByUserId(login.getUserInfo(), login.getUserInfo().getUserId());
        List<String> moduleCodeList = new ArrayList<>();
        if (moduleInfoList != null)
            moduleInfoList.forEach(moduleInfo -> moduleCodeList.add(moduleInfo.getModuleCode()));
        sessionInfo.setAuthorityCodeList(moduleCodeList);


        request.getSession().setAttribute("onlineInfo", sessionInfo);
    }


}