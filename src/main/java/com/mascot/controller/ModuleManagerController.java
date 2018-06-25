package com.mascot.controller;

import com.interfaces.mascot.ModuleManageService;
import com.mascot.bean.ResultInfo;
import com.mascot.constant.UdcConstant;
import com.mascot.utils.SessionGetUtil;
import com.thrift.common.body.ModuleInfo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 模块管理
 *
 * @author jichao
 * 2018/5/2
 */
@Controller
@RequestMapping(value = "/moduleManager")
public class ModuleManagerController {

    @Autowired
    private ModuleManageService moduleManageService;


    private static final Log logger = LogFactory.getLog(ModuleManagerController.class);

    /**
     * 获取所有模块列表
     *
     * @return resultInfo
     */
    @RequestMapping(value = "/getAllModuleInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getAllModuleInfo(HttpServletRequest request) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getAllModuleInfo---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值对的合法性
            List<ModuleInfo> list = moduleManageService.getAllModuleInfo(userInfo);
            if (null != list && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("getAllModuleInfo----获取所有模块信息>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getAllModuleInfo----获取所有模块信息>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getAllModuleInfo---web获取所有模块信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 根据 userId 获取模块信息
     *
     * @param userId 用户Id,resultInfo
     */
    @RequestMapping(value = "/getModuleInfoByUserId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getModuleInfoByUserId(HttpServletRequest request, Integer userId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == userId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getModuleInfoByUserId参数不合法---->userInfo==" + userInfo + ",userId==" + userId);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            List<ModuleInfo> list = moduleManageService.getModuleInfoByUserId(userInfo, userId);
            if (null != list && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("getModuleInfoByUserId--->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getModuleInfoByUserId--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getModuleInfoByUserId---web获取模块信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 新增权限模块
     *
     * @param parentId   模块父类id
     * @param moduleCode 模块码
     * @param ModuleName 模块名
     * @return resultInfo
     */
    @RequestMapping(value = "/addModuleInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addModuleInfo(HttpServletRequest request, Integer parentId, String moduleCode, String ModuleName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == parentId || !StringUtils.hasText(moduleCode) || !StringUtils.hasText(ModuleName)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addModuleInfo参数不合法---->userInfo==" + userInfo + ",parentId==" + parentId + ",moduleCode==" + moduleCode + ",ModuleName==" + ModuleName);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer result = moduleManageService.addModuleInfo(userInfo, parentId, moduleCode, ModuleName);
            if (result != null) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.INSERT_SUCCESS_MSG);
                    logger.info("addModuleInfo新增权限模块-->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -1) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.MODULE_FAIL_MSG1);
                    logger.info("addModuleInfo新增权限模块-->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -2) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.MODULE_FAIL_MSG2);
                    logger.info("addModuleInfo新增权限模块-->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -3) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.MODULE_FAIL_MSG3);
                    logger.info("addModuleInfo新增权限模块-->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -4) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.MODULE_FAIL_MSG4);
                    logger.info("addModuleInfo新增权限模块-->" + resultInfo.getMsg());
                    return resultInfo;
                }
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
                logger.info("addModuleInfo新增权限模块-->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,调用server服务,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("addModuleInfo新增权限模块-->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addModuleInfo---web获新增权限模块异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 更改模块
     *
     * @param parentId   模块父类id
     * @param moduleCode 模块码
     * @param ModuleName 模块名
     * @param moduleId   模块id
     * @return resultInfo
     */
    @RequestMapping(value = "/updateModuleInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateModuleInfo(HttpServletRequest request, Integer parentId, Integer moduleId, String moduleCode, String ModuleName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == parentId || !StringUtils.hasText(moduleCode) || !StringUtils.hasText(ModuleName) || null == moduleId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updateModuleInfo参数不合法---->userInfo==" + userInfo + ",parentId==" + parentId + ",moduleCode==" + moduleCode + ",ModuleName==" + ModuleName + ",moduleId==" + moduleId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer result = moduleManageService.updateModuleInfo(userInfo, parentId, moduleId, moduleCode, ModuleName);
            if (result != null) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.UPFATE_SUCCESS_MSG);
                    logger.info("updateModuleInfo,web更改模块-->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -1) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.MODULE_FAIL_MSG1);
                    logger.info("updateModuleInfo更改权限模块-->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -2) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.MODULE_FAIL_MSG2);
                    logger.info("updateModuleInfo更改权限模块-->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -3) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.MODULE_FAIL_MSG3);
                    logger.info("updateModuleInfo更改权限模块-->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -4) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.MODULE_FAIL_MSG4);
                    logger.info("updateModuleInfo更改权限模块-->" + resultInfo.getMsg());
                    return resultInfo;
                }
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
                logger.info("updateModuleInfo更改权限模块-->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,调用server服务,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
            logger.info("updateModuleInfo,web更改模块-->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updateModuleInfo---web更改模块异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 删除模块
     *
     * @param moduleId 模块id
     * @return resultInfo
     */
    @RequestMapping(value = "/deleteModuleInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteModuleInfo(HttpServletRequest request, Integer moduleId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,moduleId
            if (null == userInfo || null == moduleId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("deleteModuleInfo参数不合法---->userInfo==" + userInfo + ",moduleId==" + moduleId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,对返回值进行校验
            Integer result = moduleManageService.deleteModuleInfo(userInfo, moduleId);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_SUCCESS_MSG);
                logger.info("deleteModuleInfo删除模块" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,返回删除失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deleteModuleInfo删除模块" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("删除模块---web删除模块异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 根据 moduleId 获取模块信息
     *
     * @param moduleId 模块码自增主键
     * @return resultInfo
     */
    @RequestMapping(value = "/getModuleInfoByModuleId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getModuleInfoByModuleId(HttpServletRequest request, Integer moduleId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,moduleId
            if (null == userInfo || null == moduleId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getModuleInfoByModuleId参数不合法---->userInfo==" + userInfo + ",moduleId==" + moduleId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,对返回值进行校验
            ModuleInfo moduleInfo = moduleManageService.getModuleInfoByModuleId(userInfo, moduleId);
            if (moduleInfo != null) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(moduleInfo);
                logger.info("getModuleInfoByModuleId---web获取模块信息" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值校验不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getModuleInfoByModuleId---web获取模块信息" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getModuleInfoByModuleId---web获取模块信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 根据上级目录获取所有下级目录
     *
     * @param moduleId 一级分类编号
     * @return resultInfo
     */
    @RequestMapping(value = "/getModuleInfoByParentId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getModuleInfoByParentId(HttpServletRequest request, Integer moduleId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,moduleId
            if (null == userInfo || null == moduleId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getModuleInfoByParentId参数不合法---->userInfo==" + userInfo + ",moduleId==" + moduleId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,对返回值进行校验
            List<Integer> list = moduleManageService.getModuleInfoByParentId(userInfo, moduleId);
            if (null != list && !list.isEmpty()) {
                //参数校验合法,调用server服务,检验返回值的合法性
                List<Map<String, Object>> list1 = new ArrayList<>();
                List<Integer> list2 = new ArrayList<>();
                for (Integer iten : list) {
                    ModuleInfo moduleInfo = moduleManageService.getModuleInfoByModuleId(userInfo, iten);
                    if (moduleInfo != null) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("moduleId", iten);
                        map.put("moduleName", moduleInfo.getModuleName());
                        map.put("moduleCode", moduleInfo.getModuleCode());
                        list1.add(map);
                    }
                }
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list1);
                logger.info("getModuleInfoByParentId===>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getModuleInfoByParentId===>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getModuleInfoByParentId---web获取模块信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 显示父模块名称
     *
     * @param moduleId
     * @return resultInfo
     */
    @RequestMapping(value = "/getParentName", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getParentName(HttpServletRequest request, Integer moduleId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,moduleId
            if (null == userInfo || null == moduleId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getParentName参数不合法---->userInfo==" + userInfo + ",moduleId==" + moduleId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,对返回值进行校验
            ModuleInfo moduleInfo = moduleManageService.getModuleInfoByModuleId(userInfo, moduleId);
            if (moduleInfo != null) {
                Integer parentId = moduleInfo.getParentId();
                String parentName = "";
                //根据父模块id查询父模块名
                if (parentId != 0) {
                    ModuleInfo moduleInfo1 = moduleManageService.getModuleInfoByModuleId(userInfo, parentId);
                    if (moduleInfo1 != null) {
                        parentName = moduleInfo1.getModuleName();
                    }
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setResult(parentName);
                    resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                    logger.info("getParentName--显示父模块名称>>" + resultInfo.getMsg());
                    return resultInfo;
                } else {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.NOT_HAVE_PARENT_NAME);
                    logger.info("getParentName--显示父模块名称>>" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getParentName--显示父模块名称>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getParentName---web显示父模块名称异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 根据上级目录获取下一级目录
     *
     * @param moduleId 上级分类编号
     * @return resultInfo
     */
    @RequestMapping(value = "/getNextModuleInfoByParentId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getNextModuleInfoByParentId(HttpServletRequest request, Integer moduleId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,moduleId
            if (null == userInfo || null == moduleId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getNextModuleInfoByParentId参数不合法---->userInfo==" + userInfo + ",moduleId==" + moduleId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> list = moduleManageService.getNextModuleInfoByParentId(userInfo, moduleId);
            if (list != null && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("getNextModuleInfoByParentId---web根据上级目录获取下一级目录" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getNextModuleInfoByParentId---web根据上级目录获取下一级目录" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getNextModuleInfoByParentId---web根据上级目录获取下一级目录异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


}