package com.mascot.controller;

import com.interfaces.mascot.BbsManageService;
import com.interfaces.mascot.MessageManageService;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 消息管理
 * Created by jichao on 2018/05/11
 */

@Controller
@RequestMapping(value = "/messageManage")
public class MessageManageController {


    @Autowired
    private BbsManageService bbsManageService;


    @Autowired
    private UserManageService userManageService;

    @Autowired
    private MessageManageService messageManageService;


    private static final Log logger = LogFactory.getLog(MessageManageController.class);

    /**
     * 发布社区公告,社区
     *
     * @param messageContent 消息内容
     * @return resultInfo
     */
    @RequestMapping(value = "/addBbsNotice", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addBbsNotice(HttpServletRequest request, String messageContent) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || !StringUtils.hasText(messageContent)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addBbsNotice---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务
            Integer result = bbsManageService.addBbsNotice(userInfo, messageContent);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.NOTICE_ISSUE_SUCCESS);
                logger.info("addBbsNotice---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.NOTICE_ISSUE_FAIL);
            logger.info("addBbsNotice---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addBbsNotice---web发布社区公告异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 发布平台公告,平台
     *
     * @param title          标题
     * @param messageContent 消息内容
     * @return resultInfo
     */
    @RequestMapping(value = "/addPlatformNotice", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addPlatformNotice(HttpServletRequest request, String title, String messageContent) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || !StringUtils.hasText(messageContent) || !StringUtils.hasText(title)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addPlatformNotice---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务
            Integer result = userManageService.addNotice(userInfo, title, messageContent);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.NOTICE_ISSUE_SUCCESS);
                logger.info("addPlatformNotice---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.NOTICE_ISSUE_FAIL);
            logger.info("addPlatformNotice---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addPlatformNotice---web发布平台公告异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }


    /**
     * 查询消息
     *
     * @param pageIndex 当前页
     * @param pageSize  每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryMessage", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryMessage(HttpServletRequest request, String modules, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || !StringUtils.hasText(modules)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryMessage---->" + resultInfo.getMsg());
                return resultInfo;
            }
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //对传入的modules进行处理
            List<Integer> messageTypeList = Arrays.asList(modules.split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            //参数校验合法,调用server服务
            List<Map<String, Object>> result = messageManageService.queryMessage(userInfo, messageTypeList, pageIndex, pageSize);
            if (result != null && !result.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryMessage---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryMessage---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryMessage---web查询消息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询消息总条数
     *
     * @return resultInfo
     */
    @RequestMapping(value = "/queryMessageCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryMessageCount(HttpServletRequest request, String modules) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || !StringUtils.hasText(modules)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryMessageCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //对传入的modules进行处理
            List<Integer> messageTypeList = Arrays.asList(modules.split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            //传入值检验合法,返回信息
            Integer result = messageManageService.queryMessageCount(userInfo, messageTypeList);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryMessageCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryMessageCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryMessageCount---web查询消息总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 展示最新一条消息
     *
     * @param messageType 消息类型
     * @return resultInfo
     */
    @RequestMapping(value = "/queryNewestMessage", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryNewestMessage(HttpServletRequest request, Integer messageType) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryNewestMessage---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务
            Map<String, Object> result = messageManageService.queryNewestMessage(userInfo, messageType);
            if (result != null && !result.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryNewestMessage---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryNewestMessage---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryNewestMessage---web展示最新一条消息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 展示消息详情
     *
     * @param messageId 消息Id
     * @return map
     */
    @RequestMapping(value = "/queryMessageDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryMessageDetail(HttpServletRequest request, Integer messageId) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryMessageDetail---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务
            Map<String, Object> result = messageManageService.queryNewestMessage(userInfo, messageId);
            if (result != null && !result.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryMessageDetail---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryMessageDetail---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryMessageDetail---web展示消息详情异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }


}
