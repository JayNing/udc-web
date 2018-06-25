package com.mascot.controller;

import com.interfaces.mascot.UserManageService;
import com.mascot.bean.ResultInfo;
import com.mascot.constant.UdcConstant;
import com.mascot.utils.SessionGetUtil;
import com.thrift.common.body.UserInfo;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 账号管理-员工账号
 *
 * @author jichao
 * 2018/4/28
 */
@Controller
@RequestMapping(value = "/accountManger")
public class AccountMangerController {

    @Autowired
    private UserManageService userManageService;

    private static final Log logger = LogFactory.getLog(AccountMangerController.class);

    /**
     * 新增用户
     *
     * @param loginName    用户名/登录名
     * @param passWd       密码
     * @param realName     真实姓名
     * @param departmentId 部门编号
     * @param roleIdList   角色编号字符串数组接收
     * @param moduleIdList 模块权限 --- 一期可为空 字符串数组接收
     * @return resultInfo
     */
    @RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo saveUserInfo(HttpServletRequest request, String loginName, String passWd, String realName, Integer departmentId, String roleIdList, String moduleIdList) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || !StringUtils.hasText(loginName) || !StringUtils.hasText(passWd) || !StringUtils.hasText(realName) || null == departmentId || !StringUtils.hasText(roleIdList) || !StringUtils.hasText(moduleIdList)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("saveUserInfo--参数异常loginName==" + loginName + ",passWd==" + passWd + ",realName==" + realName + ",departmentId==" + departmentId + ",userInfo" + userInfo + ",roleIdList==" + roleIdList + ",moduleIdList==" + moduleIdList);
                return resultInfo;
            }
            List<Integer> listIds = Arrays.asList(roleIdList.split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<Integer> midsList = Arrays.asList(moduleIdList.split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            //调用server服务,并检验返回值的合法性
            Integer result = userManageService.saveUserInfo(userInfo, loginName, passWd, realName, departmentId, listIds, midsList);
            if (null != result) {
                if (result == -2) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
                    logger.info("saveUserInfo-用户表插入失败" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -1) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.ACCOUNT_EXIST_MSG);
                    logger.info("saveUserInfo-已存在该有效用户" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.OPERATE_SUCCESS_MSG);
                    logger.info("saveUserInfo-用户表和部门均插入成功" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.ACCOUNT_INSERT_FAIL_MSG);
            logger.info("saveUserInfo-新增用户失败" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("saveUserInfo---web新增用户异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 删除用户
     *
     * @param userId 用户编号
     * @return resultInfo
     */
    @RequestMapping(value = "/deleteUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteUserInfo(HttpServletRequest request, Integer userId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验参数的合法性
            if (null == userInfo || null == userId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("deleteUserInfo----参数不合法-->userId==" + userId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,验证返回值合法性
            Integer result = userManageService.deleteUserInfo(userInfo, userId);
            if (null != result && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_ACCOUNT_SUCCESS_MSG);
                logger.info("deleteUserInfo-删除用户信息成功-->" + resultInfo.getMsg());
                return resultInfo;
            }
            //删除失败!
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deleteUserInfo----删除用户-->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("deleteUserInfo---web删除用户异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 获取账号管理列表信息
     *
     * @param name         用户姓名（登录名/别名）--可为空
     * @param departmentId 所属部门编号 --- 可为空
     * @param pageIndex    当前页 --- 不可为空
     * @param pageSize     当前页数据条数 --- 不可为空
     * @return resultInfo
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getUserInfo(HttpServletRequest request, String name, Integer departmentId, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("getUserInfo传入参数不合法---->userInfo==" + userInfo);
                return resultInfo;
            }
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //传入值检验合法,调用server服务
            if (null != userManageService.getUserInfo(userInfo, name, departmentId, pageIndex, pageSize) && !(userManageService.getUserInfo(userInfo, name, departmentId, pageIndex, pageSize)).isEmpty()) {
                //返回值校验合法
                List<Map<String, Object>> userInfoList = userManageService.getUserInfo(userInfo, name, departmentId, pageIndex, pageSize);
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_USERINFO_SUCCESS_MSG);
                resultInfo.setResult(userInfoList);
                logger.info("getUserInfo-账号信息查询成功--->" + resultInfo.getResult());
                return resultInfo;
            }
            //查询失败,返回信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.NILL_ACCOUNT_MSG);
            logger.info("账户信息查询失败-->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getUserInfo---web获取账户管理列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 根据UserId获取详情信息
     *
     * @param request 获取session
     * @return resultInfo
     */
    @RequestMapping(value = "/getUserInfoDetailByUserId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getUserInfoDetailByUserId(HttpServletRequest request) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getUserInfoDetailByUserId---->userInfo==" + userInfo);
                return resultInfo;
            }
            //参数验证合法,调用server服务
            if (null != userManageService.getUserInfoDetailByUserId(userInfo, userInfo.getUserId()) && !(userManageService.getUserInfoDetailByUserId(userInfo, userInfo.getUserId()).isEmpty())) {
                Map<String, Object> userInfoMap = userManageService.getUserInfoDetailByUserId(userInfo, userInfo.getUserId());
                List list = new ArrayList();
                list.add(userInfoMap);
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_USERINFO_ALL_SUCCESS);
                resultInfo.setResult(list);
                logger.info("getUserInfoDetailByUserId-查询用户详细信息成功--->" + resultInfo.getResult());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_USERINFO_ALL_FAIL);
            logger.info("getUserInfoDetailByUserId-查询用户信息失败-->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getUserInfoDetailByUserId---web根据UserId获取详情信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 根据UserId获取某个用户详情信息
     *
     * @return resultInfo
     */
    @RequestMapping(value = "/getUserInfosDetailByUserId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getUserInfosDetailByUserId(HttpServletRequest request, Integer userId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getUserInfosDetailByUserId---->userInfo==" + userInfo);
                return resultInfo;
            }
            //参数验证合法,调用server服务
            if (null != userManageService.getUserInfoDetailByUserId(userInfo, userInfo.getUserId()) && !(userManageService.getUserInfoDetailByUserId(userInfo, userInfo.getUserId()).isEmpty())) {
                Map<String, Object> userInfoMap = userManageService.getUserInfoDetailByUserId(userInfo, userId);
                List list = new ArrayList();
                list.add(userInfoMap);
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_USERINFO_ALL_SUCCESS);
                resultInfo.setResult(list);
                logger.info("getUserInfosDetailByUserId-查询用户详细信息成功--->" + resultInfo.getResult());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_USERINFO_ALL_FAIL);
            logger.info("getUserInfosDetailByUserId-查询用户信息失败-->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getUserInfosDetailByUserId---web根据UserId获取详情信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 编辑更改用户
     *
     * @param userId       用户编号
     * @param passWd       密码
     * @param realName     姓名/别名/真实姓名
     * @param departmentId 部门编号
     * @return result 1：修改成功；-1：用户中心修改失败
     */
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateUserInfo(HttpServletRequest request, Integer userId, String avatar, String passWd, String realName, Integer departmentId, String roleIdList, String moduleIdList) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == userId || null == departmentId || !StringUtils.hasText(passWd) || !StringUtils.hasText(realName) || !StringUtils.hasText(roleIdList) || !StringUtils.hasText(moduleIdList)) {
                //验证参数不合法
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updateUserInfo参数不合法-->userInfo==" + userInfo + ",userId==" + userId + ",departmentId==" + departmentId + ",passWd==" + passWd + ",realName==" + realName);
                return resultInfo;
            }
            //对传入的roleIdList,moduleIdList进行转化
            List<Integer> listIds = Arrays.asList(roleIdList.split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<Integer> midsList = Arrays.asList(moduleIdList.split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            //参数校验合法,调用server服务,验证返回值合法性
            Integer result = userManageService.updateUserInfo2(userInfo, userId, avatar, passWd, realName, departmentId, listIds, midsList);
            if (result != null) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.UPDATE_USERINFO_SUCCESS_MSG);
                    logger.info("updateUserInfo编辑更改用户成功-->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -1) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
                    logger.info("updateUserInfo用户中心----->" + resultInfo.getMsg());
                    return resultInfo;
                }
                //其情况,返回失败信息.
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.UPDATE_USERINFO_FAIL_MSG);
                logger.info("updateUserInfo编辑更改用户失败!-->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.UPDATE_USERINFO_FAIL_MSG);
            logger.info("updateUserInfo编辑更改用户失败!-->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updateUserInfo---web编辑更改用户异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 获取账号管理列表信息总条数
     *
     * @param name         用户姓名（登录名/别名）
     * @param departmentId 所属部门编号
     * @return list
     */
    @RequestMapping(value = "/getUserInfoCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getUserInfoCount(HttpServletRequest request, String name, Integer departmentId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("getUserInfoCount传入参数不合法---->userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            if (userManageService.getUserInfoCount(userInfo, name, departmentId) != null && userManageService.getUserInfoCount(userInfo, name, departmentId) > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(userManageService.getUserInfoCount(userInfo, name, departmentId));
                logger.info("getUserInfoCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getUserInfoCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getUserInfoCount---web编辑更改用户异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }
}