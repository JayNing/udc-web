package com.mascot.controller;

import com.interfaces.file.BasicService;
import com.interfaces.mascot.SpecialistManageService;
import com.mascot.bean.ResultInfo;
import com.mascot.bean.SessionInfo;
import com.mascot.constant.UdcConstant;
import com.mascot.utils.Constant;
import com.mascot.utils.DateTransferUtil;
import com.mascot.utils.SessionGetUtil;
import com.thrift.common.body.*;
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

/**
 * 专家模块
 *
 * @author jichao
 * 2018/4/16
 */
@Controller
@RequestMapping(value = "/specialistManager")
public class SpecialistManagerController {

    @Autowired
    private SpecialistManageService specialistManageService;

    private static final Log logger = LogFactory.getLog(SpecialistManagerController.class);

    /**
     * 新增专家
     *
     * @param headUrl    头像url地址
     * @param speName    专家姓名 --- 不为空
     * @param jobTitle   专家职称 --- 不为空
     * @param speProfile 专家简介 --- 不为空
     * @param honors     个人荣誉 --- 不为空
     * @return resultInfo
     */
    @RequestMapping(value = "/saveSpecialistInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo saveSpecialistInfo(HttpServletRequest request, String headUrl, String speName, String jobTitle, String speProfile, String honors) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("onlineInfo");
            UserInfo userInfo = null;
            if (sessionInfo != null) {
                userInfo = SessionGetUtil.getSession(request);
            }
            //检验传入参数的合法性
            if (null == userInfo || !StringUtils.hasText(speName) || !StringUtils.hasText(jobTitle) || !StringUtils.hasText(speProfile) || !StringUtils.hasText(honors) || !StringUtils.hasText(headUrl)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("saveSpecialistInfo---->userInfo==" + userInfo + "speName==" + speName + ",jobTitle==" + jobTitle + ",speProfile==" + speProfile + ",honors==" + honors);
                return resultInfo;
            }
            //调用server服务,检验返回值的合法性
            Integer result = specialistManageService.saveSpecialistInfo(userInfo, headUrl, speName, jobTitle, speProfile, honors);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.INSERT_SUCCESS_MSG);
                logger.info("saveSpecialistInfo----新增专家>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("saveSpecialistInfo----新增专家>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("saveSpecialistInfo---web新增专家异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 编辑专家
     *
     * @param speId      专家编号 --- 不为空
     * @param headUrl    头像url地址
     * @param speName    修改后专家姓名 --- 不为空
     * @param jobTitle   修改后专家职称 --- 不为空
     * @param speProfile 修改后专家简介 --- 不为空
     * @param honors     修改后个人荣誉 --- 不为空
     * @return resultInfo
     */
    @RequestMapping(value = "/updateSpecialistInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateSpecialistInfo(HttpServletRequest request, Integer speId, String headUrl, String speName, String jobTitle, String speProfile, String honors) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("onlineInfo");
            UserInfo userInfo = null;
            if (sessionInfo != null) {
                userInfo = SessionGetUtil.getSession(request);
            }
            //检验userInfo的合法性
            if (null == userInfo || null == speId || !StringUtils.hasText(headUrl) || !StringUtils.hasText(speName) || !StringUtils.hasText(jobTitle) || !StringUtils.hasText(speProfile) || !StringUtils.hasText(honors)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updateSpecialistInfo---->userInfo==" + userInfo + ",headUrl==" + headUrl + ",speName==" + speName + ",jobTitle==" + jobTitle + ",speProfile==" + speProfile + ",honors==" + honors);
                return resultInfo;
            }

            //调用server服务,检验返回值的合法性
            Integer result = specialistManageService.updateSpecialistInfo(userInfo, speId, headUrl, speName, jobTitle, speProfile, honors);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.UPFATE_SUCCESS_MSG);
                resultInfo.setResult(Constant.FILE_UPLOAD_URL_PATH + headUrl);
                logger.info("updateSpecialistInfo----编辑专家>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
            logger.info("saveSpecialistInfo----编辑专家>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updateSpecialistInfo---web编辑专家异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 删除专家信息
     *
     * @param speId 专家流水号
     * @return resultInfo
     */
    @RequestMapping(value = "/deleteSpecialistInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteSpecialistInfo(HttpServletRequest request, Integer speId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,speId的合法性
            if (null == userInfo || null == speId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("deleteSpecialistInfo参数不合法----userInfo==>" + userInfo + ",speId==" + speId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer result = specialistManageService.deleteSpecialistInfo(userInfo, speId);
            if (null != result && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_SUCCESS_MSG);
                logger.info("deleteSpecialistInfo---删除专家>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deleteSpecialistInfo----删除专家>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("deleteSpecialistInfo---web删除专家异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 新增专家咨询
     *
     * @param speId   专家编号
     * @param content 咨询内容
     * @return resultInfo
     */
    @RequestMapping(value = "/saveSpeConsult", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo saveSpeConsult(HttpServletRequest request, Integer speId, String content) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == speId || !StringUtils.hasText(content)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("saveSpeConsult---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数检验合法,调用server服务,对返回值进行检验
            Integer result = specialistManageService.saveSpeConsult(userInfo, speId, userInfo.getUserId(), content);
            if (null != result && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.INSERT_SUCCESS_MSG);
                logger.info("saveSpeConsult---新增专家咨询>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("saveSpeConsult---新增专家咨询>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("saveSpeConsult---web新增专家咨询异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 点赞专家
     *
     * @param speId 专家编号
     * @return resultInfo
     */
    @RequestMapping(value = "/likeSpecialist", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo likeSpecialist(HttpServletRequest request, Integer speId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == speId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("likeSpecialist---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数检验合法,调用server服务,验证返回值的合法性
            Integer result = specialistManageService.likeSpecialist(userInfo, speId, userInfo.getUserId());
            if (null != result) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.ADD_LIKE_SUCCESS);
                    logger.info("likeSpecialist---专家点赞>" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == 2) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.RECALL_LIKE_SUCCESS);
                    logger.info("likeSpecialist---专家点赞>" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            logger.info("likeSpecialist---专家点赞>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("likeSpecialist---web点赞专家异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询用户与专家点赞图标标志
     *
     * @param speId 专家编号
     * @return flag：1：点赞状态（蓝色图标），2：未赞状态（灰色图标），null：异常,resultInfo
     */
    @RequestMapping(value = "/getSpeLikeFlag", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getSpeLikeFlag(HttpServletRequest request, Integer speId) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || null == speId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getSpeLikeFlag---->userInfo==" + userInfo + ",speId==" + speId);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Integer result = specialistManageService.getSpeLikeFlag(userInfo, speId, userInfo.getUserId());
            if (result != null) {
                if (result == 1) {
                    resultInfo.setResult(1);
                    resultInfo.setMsg(UdcConstant.SPE_LIKE_FLAG_ALREADY);
                    logger.info("getSpeLikeFlag-->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == 2) {
                    resultInfo.setMsg(UdcConstant.SPE_LIKE_FLAG_NOT);
                    resultInfo.setResult(2);
                    logger.info("getSpeLikeFlag-->" + resultInfo.getMsg());
                    return resultInfo;
                }
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
                logger.info("getSpeLikeFlag-->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            logger.info("getSpeLikeFlag-->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getSpeLikeFlag---web查询点赞状态异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 获取专家列表信息总条数
     *
     * @return resultInfo
     */
    @RequestMapping(value = "/getSpecialistCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getSpecialistCount(HttpServletRequest request) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getSpecialistCount参数校验不合法---->userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Integer result = specialistManageService.getSpecialistCount(userInfo);
            if (result != null && result > 0) {
                //server返回值检验合法,返回count
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("getSpecialistCount----->>获取专家信息总条数成功.");
                return resultInfo;
            }
            //返回值检验不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getSpecialistCount----->>获取专家信息总条数成功.");
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getSpecialistCount-->" + resultInfo.getMsg());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 通过专家id查询专家详情信息
     *
     * @param speId 专家编号
     * @return resultInfo
     */
    @RequestMapping(value = "/getSpecialistInfoDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getSpecialistInfoDetail(HttpServletRequest request, Integer speId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == speId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getSpecialistInfoDetail---->userInfo==" + userInfo + ",speId==" + speId);
                return resultInfo;
            }
            //返回值校验合法,调用server服务,检验返回值的合法性
            SpecialistInfo specialistInfo = specialistManageService.getSpecialistInfoDetailBySpeId(userInfo, speId);
            if (specialistInfo != null) {
                //返回值校验合法,可使用
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(specialistInfo);
                logger.info("getSpecialistInfoDetail通过专家id查询专家详情信息----->>" + resultInfo.getMsg());
                return resultInfo;
            }
            //校验值检验不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getSpecialistInfoDetail通过专家id查询专家详情信息----->>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getSpecialistInfoDetail-->" + resultInfo.getMsg());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 获取专家列表信息
     *
     * @param pageIndex 当前页 --- 不可为空
     * @param pageSize  当前页数据条数 --- 不可为空
     * @return resultInfo, 返回结果集中count为专家的点赞总数
     */
    @RequestMapping(value = "/getSpecialistInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getSpecialistInfo(HttpServletRequest request, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("getSpecialistInfo---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 2;
            }
            //参数检验合法,调用server服务,对返回值进行检验
            List<SpecialistInfo> list = specialistManageService.getSpecialistInfo(userInfo, pageIndex, pageSize);
            if (null != list && !list.isEmpty()) {
                //返回值检验合法
                List<Map<String, Object>> list1 = new ArrayList<>();
                for (SpecialistInfo specialistInfo : list) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("speId", specialistInfo.getSpeId());
                    map.put("speName", specialistInfo.getSpeName());
                    map.put("jobTitle", specialistInfo.getJobTitle());
                    map.put("speProfile", specialistInfo.getSpeProfile());
                    map.put("honors", specialistInfo.getHonors());
                    map.put("headUrl", specialistInfo.getHeadUrl());
                    map.put("speLikeCount", specialistInfo.getSpeLikeCount());
                    Integer status = specialistManageService.getSpeLikeFlag(userInfo, specialistInfo.getSpeId(), userInfo.getUserId());
                    if (status != null) {
                        map.put("likeFlag", status);
                    } else {
                        map.put("likeFlag", 0);
                    }
                    list1.add(map);
                }
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list1);
                logger.info("getSpecialistInfo获取专家列表信息--->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getSpecialistInfo获取专家列表信息--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getSpecialistInfo---web获取专家列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询专家咨询
     *
     * @param title      搜索框参数
     * @param start1Time 起始时间
     * @param end1Time   截止时间
     * @param pageIndex  当前页
     * @param pageSize   每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/querySpeConsult", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo querySpeConsult(HttpServletRequest request, String title, String realName, String start1Time, String end1Time, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getSpecialistInfoDetail---->userInfo==" + userInfo);
                return resultInfo;
            }
            //对传入时间进行转换
            Date startTime = null;
            Date endTime = null;
            if (StringUtils.hasText(start1Time)) {
                startTime = DateTransferUtil.dateTransfer(start1Time);
            }
            if (StringUtils.hasText(end1Time)) {
                endTime = DateTransferUtil.dateTransfer(end1Time);
            }
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }

            //参数校验合法,调用server服务,验证返回值的合法性.
            List<Map<String, Object>> list = specialistManageService.querySpeConsult(userInfo, title, realName, startTime, endTime, pageIndex, pageSize);
            if (null != list && !list.isEmpty()) {
                //返回值校验合法,返回专家咨询信息
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("querySpeConsult查询专家咨询---->>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("querySpeConsult查询专家咨询---->>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("querySpeConsult-->" + resultInfo.getMsg());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 查询专家咨询总条数
     *
     * @param title      搜索框参数
     * @param start1Time 起始时间
     * @param end1Time   截止时间
     * @return resultInfo
     */
    @RequestMapping(value = "/querySpeConsultCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo querySpeConsultCount(HttpServletRequest request, String title, String realName, String start1Time, String end1Time) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("querySpeConsultCount---传入参数不合法userInfo" + userInfo);
                return resultInfo;
            }
            //对传入是时间进行转换
            Date startTime = null;
            Date endTime = null;
            if (StringUtils.hasText(start1Time)) {
                startTime = DateTransferUtil.dateTransfer(start1Time);
            }
            if (StringUtils.hasText(end1Time)) {
                endTime = DateTransferUtil.dateTransfer(end1Time);
            }
            //参数校验合法，调用server服务，检验返回值的合法性
            Integer count = specialistManageService.querySpeConsultCount(userInfo, title, realName, startTime, endTime);
            if (count != null && count > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(count);
                logger.info("querySpeConsultCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法，查询失败
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getBookListCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("querySpeConsultCount---web查询专家咨询总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 专家咨询详情
     *
     * @param consultId 咨询编号
     * @return resultInfo
     */
    @RequestMapping(value = "/querySpeConsultDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo querySpeConsultDetail(HttpServletRequest request, Integer consultId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == consultId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("querySpeConsultDetail---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法，调用server服务，检验返回值的合法性
            Map<String, Object> map = specialistManageService.querySpeConsultDetail(userInfo, consultId);
            if (map != null && !(map.isEmpty())) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(map);
                logger.info("querySpeConsultDetail--web专家咨询详情" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("querySpeConsultDetail--web专家咨询详情" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("querySpeConsultDetail---web专家咨询详情异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


}