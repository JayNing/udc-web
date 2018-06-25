package com.mascot.controller;

import com.interfaces.mascot.ModuleManageService;
import com.interfaces.mascot.RoleManageService;
import com.mascot.bean.ResultInfo;
import com.mascot.constant.UdcConstant;


import com.mascot.utils.SessionGetUtil;
import com.thrift.common.body.ModuleInfo;
import com.thrift.common.body.RoleInfo;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色管理
 *
 * @author jichao
 * 2018/4/16
 */
@Controller
@RequestMapping(value = "/roleManager")
public class RoleManagerController {

    @Autowired
    private RoleManageService roleManageService;
    @Autowired
    private ModuleManageService moduleManageService;
    private static final Log logger = LogFactory.getLog(RoleManagerController.class);

    /**
     * 获取角色列表信息
     *
     * @param roleName  角色姓名
     * @param pageIndex 当前页 --- 不可为空
     * @param pageSize  当前页数据条数 --- 不可为空
     * @return resultInfo
     */
    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getRoleList(HttpServletRequest request, String roleName, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("getRoleList---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //传入参数检验合法,调用server服务,对返回值进行检验
            List<RoleInfo> roleInfoList = roleManageService.getRoleList(userInfo, roleName, pageIndex, pageSize);
            if (null != roleInfoList && !roleInfoList.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(roleInfoList);
                logger.info("getRoleList-查询角色信息成功--->");
                return resultInfo;
            }
            //查询失败,返回信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getRoleList-角色信息查询失败---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getRoleList---web获取角色列表信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 编辑修改角色信息
     *
     * @param roleName   角色名称
     * @param newModules 模块码权限码值数组---该角色所选码值
     * @return resultInfo
     */
    @RequestMapping(value = "/updateRoleInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateRoleInfo(HttpServletRequest request, Integer roleId, String roleName, String newModules) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == roleId || !StringUtils.hasText(roleName)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updateRoleInfo参数异常---->userInfo==" + userInfo + ",roleId==" + roleId + ",roleName==" + roleName);
                return resultInfo;
            }
            //对传入的newModules转换成list<Integer>
            List<Integer> listIds = new ArrayList<>();
            if (StringUtils.hasText(newModules)) {
                listIds = Arrays.asList(newModules.split(","))
                        .stream().map(s -> Integer.parseInt(s.trim()))
                        .collect(Collectors.toList());
            }
            //参数检验合法,调用server服务,检验返回值合法性
            Integer result = roleManageService.updateRoleInfo(userInfo, roleId, roleName, listIds);
            if (result != null) {
                if (result == -1) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
                    logger.info("updateRoleInfo--->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.UPFATE_SUCCESS_MSG);
                    logger.info("updateRoleInfo角色编辑修改成功------>");
                    return resultInfo;
                }
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
            logger.info("updateRoleInfo编辑修改角色信息----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updateRoleInfo---web编辑修改角色信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 删除角色信息
     *
     * @param roleId 角色流水号
     * @return resultInfo
     */
    @RequestMapping(value = "/deleteRoleInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteRoleInfo(HttpServletRequest request, Integer roleId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || null == roleId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("deleteRoleInfo参数不合法---->userInfo==" + userInfo + ",roleId==" + roleId);
                return resultInfo;
            }
            //参数校验合法,调用server服务
            Integer result = roleManageService.deleteRoleInfo(userInfo, roleId);
            if (result != null && result == 1) {
                //删除角色信息成功!
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_SUCCESS_MSG);
                logger.info("deleteRoleInfo删除角色信息--->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deleteRoleInfo删除角色信息--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("deleteRoleInfo---web删除角色信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 添加角色信息
     *
     * @param roleName 角色姓名---不可为空
     * @param modules  模块码权限码值---该角色所选码值
     * @return result >0：角色表插入成功，角色模块关联表操作成功记录总数。-1:权限为空。resultInfo
     */
    @RequestMapping(value = "/saveRoleInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo saveRoleInfo(HttpServletRequest request, String roleName, String modules) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,roleName的合法性
            if (null == userInfo || !StringUtils.hasText(roleName) || !StringUtils.hasText(modules)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("saveRoleInfo参数不合法---->userInfo==" + userInfo + ",roleName==" + roleName);
                return resultInfo;
            }
            //对传入的modules进行处理
            List<Integer> listIds = Arrays.asList(modules.split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            //参数检验合法,调用server服务,并检验返回值的合法性
            Integer result = roleManageService.saveRoleInfo(userInfo, roleName, listIds);
            if (result != null) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.INSERT_SUCCESS_MSG);
                    logger.info("saveRoleInfo角色信息---->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -2) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.ROLE_EXIST_MSG);
                    logger.info("saveRoleInfo角色信息---->" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("saveRoleInfo角色信息---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("saveRoleInfo---web添加角色信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 获取角色列表信息总条数
     *
     * @param roleName 角色姓名
     * @return resultInfo
     */
    @RequestMapping(value = "/getRoleListCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getRoleListCount(HttpServletRequest request, String roleName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("getRoleListCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer result = roleManageService.getRoleListCount(userInfo, roleName);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("getRoleListCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getRoleListCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getRoleListCount---web获取角色信息条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 根据 roleId 获取对应权限模块信息
     *
     * @param roleIdList 单个角色id
     * @return resultInfo
     */
    @RequestMapping(value = "/getModuleInfoByRoleId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getModuleInfoByRoleId(HttpServletRequest request, String roleIdList) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || !StringUtils.hasText(roleIdList)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getModuleInfoByRoleId---->" + resultInfo.getMsg());
                return resultInfo;
            }
            List<Integer> listIds = Arrays.asList(roleIdList.split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            //参数校验合法,调用server服务,检验返回值的合法性
            List<ModuleInfo> moduleInfos = roleManageService.getModuleInfoByRoleId(userInfo, listIds);

            if (null != moduleInfos && !moduleInfos.isEmpty()) {
                List<Integer> list = new ArrayList<>();
                for (ModuleInfo moduleInfo : moduleInfos) {
                    list.add(moduleInfo.getModuleId());
                }
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                logger.info("getModuleInfoByRoleId--web根据 roleId 获取模块信息>>" + resultInfo.getMsg());
                resultInfo.setResult(list);
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getModuleInfoByRoleId--web根据 roleId 获取模块信息>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getRoleListCount---web根据 roleId 获取模块信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 通过角色id查询角色信息
     *
     * @param roleId 角色编号
     * @return resultInfo
     */
    @RequestMapping(value = "/getRoleInfoDetailByRoleId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getRoleInfoDetailByRoleId(HttpServletRequest request, Integer roleId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || null == roleId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("getRoleInfoDetailByRoleId---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务,校验返回值的合法性
            Map<String, Object> map = roleManageService.getRoleInfoDetailByRoleId(userInfo, roleId);
            if (null != map && !map.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(map);
                logger.info("getRoleInfoDetailByRoleId---web根据 roleId 获取模块信息" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getRoleInfoDetailByRoleId---web根据 roleId 获取模块信息" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getRoleInfoDetailByRoleId---web根据 roleId 获取模块信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


}
