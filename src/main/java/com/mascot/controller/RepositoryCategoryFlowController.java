package com.mascot.controller;

import com.interfaces.mascot.RepositoryCategoryFlowService;
import com.mascot.bean.ResultInfo;
import com.mascot.constant.UdcConstant;
import com.mascot.utils.SessionGetUtil;
import com.thrift.common.body.RepositoryCategoryFlow;
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
import java.util.List;

/**
 * 知识仓库流程节点分类管理
 * Created by jichao on 2018/05/11
 */
@Controller
@RequestMapping(value = "/repositoryCategoryFlow")
public class RepositoryCategoryFlowController {

    @Autowired
    private RepositoryCategoryFlowService repositoryCategoryFlowService;

    private static final Log logger = LogFactory.getLog(RepositoryCategoryFlowController.class);

    /**
     * 获取知识流程分类目录列表
     *
     * @return resultInfo
     */
    @RequestMapping(value = "/getRepositoryCategoryFlowList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getRepositoryCategoryFlowList(HttpServletRequest request) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getRepositoryCategoryFlowList--->传入参数不合法,userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            List<RepositoryCategoryFlow> list = repositoryCategoryFlowService.getRepositoryCategoryFlowList(userInfo);
            if (null != list && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
                resultInfo.setResult(list);
                logger.info("getRepositoryCategoryFlowList获取知识流程分类目录列表--->" + resultInfo.getMsg());
                return resultInfo;
            }
            //获取zsck分类列表失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getRepositoryCategoryFlowList获取知识流程分类目录列表--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getRepositoryCategoryFlowList---web获取知识流程分类目录列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 新增流程分类目录
     *
     * @param flowName 目录名称
     * @return resultInfo
     */
    @RequestMapping(value = "/addRepositoryCategoryFlow", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addRepositoryCategoryFlow(HttpServletRequest request, String flowName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,flowName参数的合法性
            if (null == userInfo || !StringUtils.hasText(flowName)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addRepositoryCategoryFlow-参数校验不正确----->flowName==" + flowName + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数校验合法.调用server服务,检验返回值合法性
            Integer result = repositoryCategoryFlowService.addRepositoryCategoryFlow(userInfo, flowName);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.INSERT_SUCCESS_MSG);
                logger.info("addRepositoryCategoryFlow---web新增流程分类目录" + resultInfo.getMsg());
                return resultInfo;
            } else if (result == -1) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.CLASSIFY_EXIST_MSG);
                logger.info("addRepositoryCategoryFlow---web新增流程分类目录" + resultInfo.getMsg());
                return resultInfo;
            }
            //新增失败,返回失败信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("addRepositoryCategoryFlow---web新增流程分类目录" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addRepositoryCategoryFlow---web新增流程分类目录异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 获取知识流程分类详情
     *
     * @param flowId 流程分类编号
     * @return resultInfo
     */
    @RequestMapping(value = "/getRepositoryCategoryFlowDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getRepositoryCategoryFlowDetail(HttpServletRequest request, Integer flowId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验bookId,bookName,author,userInfo参数的合法性
            if (null == userInfo || null == flowId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getRepositoryCategoryFlowDetail-参数校验不正确----->flowId==" + flowId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数校验合法.调用server服务,检验返回值合法性
            RepositoryCategoryFlow repositoryCategoryFlow = repositoryCategoryFlowService.getRepositoryCategoryFlowDetail(userInfo, flowId);
            if (repositoryCategoryFlow != null) {
                List<RepositoryCategoryFlow> list = new ArrayList<>();
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                list.add(repositoryCategoryFlow);
                resultInfo.setResult(list);
                logger.info("getRepositoryCategoryFlowDetail---web获取知识流程分类详情" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询结果失败,返回失败信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getRepositoryCategoryFlowDetail---web获取知识流程分类详情" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getRepositoryCategoryFlowDetail---web获取知识流程分类详情异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 编辑修改知识流程分类
     *
     * @param flowId   流程分类编号
     * @param flowName 流程分类名称
     * @return resultInfo
     */
    @RequestMapping(value = "/updateRepositoryCategoryFlow", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateRepositoryCategoryFlow(HttpServletRequest request, Integer flowId, String flowName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数参数的合法性
            if (null == userInfo || null == flowId || !StringUtils.hasText(flowName)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updateRepositoryCategoryFlow参数校验不正确----->flowName==" + flowName + ",userInfo==" + userInfo + ",flowId==" + flowId);
                return resultInfo;
            }
            //参数校验合法.调用server服务,检验返回值合法性
            Integer result = repositoryCategoryFlowService.updateRepositoryCategoryFlow(userInfo, flowId, flowName);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.INSERT_SUCCESS_MSG);
                logger.info("updateRepositoryCategoryFlow---web编辑修改知识流程分类" + resultInfo.getMsg());
                return resultInfo;
            }
            //编辑修改失败,返回失败信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("updateRepositoryCategoryFlow---web编辑修改知识流程分类" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updateRepositoryCategoryFlow---web编辑修改知识流程分类异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 删除知识流程分类
     *
     * @param flowId 流程分类编号
     * @return resultInfo
     */
    @RequestMapping(value = "/deleteRepositoryCategoryFlow", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteRepositoryCategoryFlow(HttpServletRequest request, Integer flowId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数参数的合法性
            if (null == userInfo || null == flowId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("deleteRepositoryCategoryFlow-参数校验不正确--->userInfo==" + userInfo + ",flowId==" + flowId);
                return resultInfo;
            }
            //参数检验合法,调用server服务,验证返回值的合法性
            Integer result = repositoryCategoryFlowService.deleteRepositoryCategoryFlow(userInfo, flowId);
            if (null != result && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_SUCCESS_MSG);
                logger.info("deleteRepositoryCategoryFlow---web删除知识流程分类" + resultInfo.getMsg());
                return resultInfo;
            }
            //删除失败,返回失败信息!
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deleteRepositoryCategoryFlow---web删除知识流程分类" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("deleteRepositoryCategoryFlow---web删除知识流程分类异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


}