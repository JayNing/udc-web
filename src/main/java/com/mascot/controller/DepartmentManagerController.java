package com.mascot.controller;

import com.interfaces.mascot.DepartmentManageService;
import com.mascot.bean.ResultInfo;
import com.mascot.constant.UdcConstant;
import com.mascot.utils.SessionGetUtil;
import com.thrift.common.body.Department;
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
import java.util.List;

/**
 * 部门管理
 *
 * @author jichao
 * 2018/4/27
 */
@Controller
@RequestMapping(value = "/departmentManager")
public class DepartmentManagerController {

    @Autowired
    private DepartmentManageService departmentManageService;

    private static final Log logger = LogFactory.getLog(DepartmentManagerController.class);

    /**
     * 获取部门列表
     *
     * @return resultInfo
     */
    @RequestMapping(value = "/getDepartmentList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getDepartmentList(HttpServletRequest request) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("getDepartmentList---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务查询部门信息
            List<Department> result = departmentManageService.getDepartmentList(userInfo);
            if (null != result && !result.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("getDepartmentList---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getDepartmentList---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getDepartmentList---web 获取部门列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 新增部门
     *
     * @param departmentName 部门名称
     * @return result -1:已存在同名部门,resultInfo
     */
    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addDepartment(HttpServletRequest request, String departmentName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,departmentName的合法性
            if (null == userInfo || !StringUtils.hasText(departmentName)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addDepartment参数不合法---->userInfo==" + userInfo + ",departmentName==" + departmentName);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Integer result = departmentManageService.addDepartment(userInfo, departmentName);
            if (result != null) {
                if (result == -1) {
                    //已存在同名部门
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.CLASSIFY_EXIST_MSG);
                    logger.info("addDepartment--->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == 1) {
                    //部门添加成功!
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.INSERT_SUCCESS_MSG);
                    logger.info("addDepartment添加部门分类--->" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("addDepartment添加部门分类--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addDepartment---web新增部门异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 编辑修改部门
     *
     * @param departmentId   部门编号
     * @param departmentName 部门名称
     * @return resultInfo
     */
    @RequestMapping(value = "/updateDepartment", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateDepartment(HttpServletRequest request, Integer departmentId, String departmentName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,departmentName的合法性,departmentName可为空
            if (null == userInfo || !StringUtils.hasText(departmentName) || null == departmentId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updateDepartment参数不合法---->userInfo==" + userInfo + ",departmentId==" + departmentId + ",departmentName==" + departmentName);
                return resultInfo;
            }
            Integer result = departmentManageService.updateDepartment(userInfo, departmentId, departmentName);
            //参数检验合法,调用server服务,验证返回值的合法性
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.UPFATE_SUCCESS_MSG);
                logger.info("updateDepartment编辑修改部门-->" + resultInfo.getMsg());
                return resultInfo;
            }
            //编辑修改失败.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
            logger.info("updateDepartment编辑修改部门-->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updateDepartment---web编辑修改部门异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 删除部门
     *
     * @param departmentId 部门编号
     * @return resultInfo
     */
    @RequestMapping(value = "/deleteDepartment", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteDepartment(HttpServletRequest request, Integer departmentId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,departmentId
            if (null == userInfo || null == departmentId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("deleteDepartment参数不合法---->userInfo==" + userInfo + ",departmentId==" + departmentId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,对返回值进行校验
            Integer result = departmentManageService.deleteDepartment(userInfo, departmentId);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_SUCCESS_MSG);
                logger.info("deleteDepartment删除部门---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,删除部门失败,返回失败信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deleteDepartment删除部门---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("deleteDepartment---web删除部门异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 获取部门详情
     *
     * @param departmentId 部门编号
     * @return resultInfo
     */
    @RequestMapping(value = "/getDepartmentDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getDepartmentDetail(HttpServletRequest request, Integer departmentId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,departmentId
            if (null == userInfo || null == departmentId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getDepartmentDetail参数不合法---->userInfo==" + userInfo + ",departmentId==" + departmentId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,对返回值进行校验
            Department department = departmentManageService.getDepartmentDetail(userInfo, departmentId);
            if (department != null) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(department);
                logger.info("getDepartmentDetail---web获取部门详情" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getDepartmentDetail---web获取部门详情" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getDepartmentDetail---web获取部门详情异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 根据部门Id获取部门成员列表
     *
     * @param departmentId 部门编号
     * @return resultInfo
     */
    @RequestMapping(value = "/getUserInfoByDepartment", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getUserInfoByDepartment(HttpServletRequest request, Integer departmentId) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == departmentId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getUserInfoByDepartment-根据部门Id获取部门成员列表----->departmentId==" + departmentId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            List<UserInfo> list = departmentManageService.getUserInfoByDepartment(userInfo, departmentId);
            if (list != null && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("getUserInfoByDepartment---根据部门Id获取部门成员列表>>>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值校验不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getUserInfoByDepartment---根据部门Id获取部门成员列表>>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getUserInfoByDepartment---根据部门Id获取部门成员列表!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }


}