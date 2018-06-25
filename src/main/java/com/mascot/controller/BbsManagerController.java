package com.mascot.controller;


import com.interfaces.mascot.BbsManageService;
import com.interfaces.mascot.UserManageService;
import com.mascot.bean.ResultInfo;
import com.mascot.constant.UdcConstant;
import com.mascot.utils.DateTransferUtil;
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
import java.util.*;

/**
 * 社区管理接口
 *
 * @author jichao
 * 2018/5/21
 */
@Controller
@RequestMapping(value = "/bbsManager")
public class BbsManagerController {


    @Autowired
    private BbsManageService bbsManageService;

    @Autowired
    private UserManageService userManageService;

    private static final Log logger = LogFactory.getLog(BbsManagerController.class);

    /**
     * 查询社区管理列表
     *
     * @param title      社区贴文/提问标题
     * @param disType    社区帖子类型：1，贴文；2，提问
     * @param typId      分类Id
     * @param start1Time 起始时间
     * @param end1Time   截止时间
     * @param pageIndex  当前页
     * @param pageSize   每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryDiscussionManageList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryDiscussionManageList(HttpServletRequest request, String title, Integer disType, Integer typId, String start1Time,
                                                String end1Time, Integer pageIndex, Integer pageSize) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //校验传入值的合法性
            if (null == userInfo || null == disType) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryDiscussionInfo---->userInfo===" + userInfo + ",disType==" + disType);
                return resultInfo;
            }
            //对传入分页参数进行校验
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //传入日期转换
            Date startTime = null;
            Date endTime = null;
            if (StringUtils.hasText(start1Time)) {
                startTime = DateTransferUtil.dateTransfer2(start1Time);
            }
            if (StringUtils.hasText(end1Time)) {
                endTime = DateTransferUtil.dateTransfer(end1Time);
            }
            //参数校验合法,调用server,检验返回值的合法性
            if (null != bbsManageService.queryDiscussionManageList(userInfo, title, disType, typId, startTime, endTime, pageIndex, pageSize) && !(bbsManageService.queryDiscussionManageList(userInfo, title, disType, typId, startTime, endTime, pageIndex, pageSize)).isEmpty()) {
                List<Map<String, Object>> bbslist = bbsManageService.queryDiscussionManageList(userInfo, title, disType, typId, startTime, endTime, pageIndex, pageSize);
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(bbslist);
                logger.info("queryDiscussionInfo----->" + resultInfo.getResult());
                return resultInfo;
            }
            //记录查询失败!
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryDiscussionInfo----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryDiscussionInfo---web查询社区管理列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询社区管理列表总条数
     *
     * @param title      社区贴文/提问标题
     * @param disType    社区帖子类型：1，贴文；2，提问
     * @param typId      分类Id
     * @param start1Time 起始时间
     * @param end1Time   截止时间
     * @return resultInfo
     */
    @RequestMapping(value = "/queryDiscussionInfoCounts", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryDiscussionInfoCounts(HttpServletRequest request, String title, Integer disType, Integer typId, String start1Time, String end1Time) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入值的合法性
            if (null == userInfo || null == disType) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryDiscussionInfoCounts---->userInfo===" + userInfo + ",disType==" + disType);
                return resultInfo;
            }
            Date startTime = null;
            Date endTime = null;
            if (StringUtils.hasText(start1Time)) {
                startTime = DateTransferUtil.dateTransfer2(start1Time);
            }
            if (StringUtils.hasText(end1Time)) {
                endTime = DateTransferUtil.dateTransfer(end1Time);
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer result = bbsManageService.queryDiscussionManageListCount(userInfo, title, disType, typId, startTime, endTime);
            if (result != null && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryDiscussionInfoCounts---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryDiscussionInfoCounts---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryDiscussionInfoCounts---web查询社区管理列表总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 社区置顶
     *
     * @param disId 社区帖子Id
     * @return resultInfo
     */
    @RequestMapping(value = "/addTopDiscussion", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addTopDiscussion(HttpServletRequest request, Integer disId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //校验传入值 的合法性
            if (null == userInfo || null == disId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addTopDiscussion---->userInfo===" + userInfo + ",disId==" + disId);
                return resultInfo;
            }
            //传入值校验合法,调用server服务,检验返回值的合法性
            Integer result = bbsManageService.addTopDiscussion(userInfo, disId);
            if (result != null) {
                if (result == 2) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.ISTOP_SUCCESS_MSG);
                    logger.info("addTopDiscussion---->>" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.ISTOP_REFOSE_MSG);
                    logger.info("addTopDiscussion---->>" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.ISTOP_FAIL_MSG);
            logger.info("addTopDiscussion---->>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addTopDiscussion---web社区置顶异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 删除帖子
     *
     * @param disId 社区帖子Id
     * @return result 1:成功；其他：失败,resultInfo
     */
    @RequestMapping(value = "/deleteDiscussion", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteDiscussion(HttpServletRequest request, Integer disId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //校验传入值 的合法性
            if (null == userInfo || null == disId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("deleteDiscussion---->userInfo===" + userInfo + ",disId==" + disId);
                return resultInfo;
            }
            //传入值检验合法,调用server服务,检验返回值的合法性
            Integer result = bbsManageService.deleteDiscussion(userInfo, disId);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_SUCCESS_MSG);
                logger.info("deleteDiscussion---->>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deleteDiscussion---->>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("deleteDiscussion---web删除帖子异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 我的帖子
     *
     * @param pageIndex 当前页
     * @param pageSize  每页条数
     * @param disType   1:  贴文/分享;2: 提问
     * @return resultInfo
     */
    @RequestMapping(value = "/queryMyDiscussion", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryMyDiscussion(HttpServletRequest request, Integer pageIndex, Integer pageSize, Integer disType) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //校验传入值的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryMyDiscussion---->userInfo===" + userInfo);
                return resultInfo;
            }
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //传入值校验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> dislist = bbsManageService.queryMyDiscussion(userInfo, pageIndex, pageSize, disType);
            if (null != dislist && !dislist.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(dislist);
                logger.info("queryMyDiscussion-查询我的帖子----->" + resultInfo.getResult());
                return resultInfo;
            }
            //查询失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryMyDiscussion-查询我的帖子----->" + resultInfo.getResult());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryMyDiscussion---web查询我的帖子异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询我收藏的帖子
     *
     * @param pageIndex 当前页
     * @param pageSize  每页条数
     * @param disType   1:  贴文/分享;2: 提问
     * @return resultInfo
     */
    @RequestMapping(value = "/queryCollectDiscussion", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryCollectDiscussion(HttpServletRequest request, Integer pageIndex, Integer pageSize, Integer disType) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //校验传入值的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryCollectDiscussion---->userInfo===" + userInfo);
                return resultInfo;
            }
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //传入值校验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> dislist = bbsManageService.queryCollectDiscussion(userInfo, pageIndex, pageSize, disType);
            if (null != dislist && !dislist.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(dislist);
                logger.info("queryCollectDiscussion-查询我收藏的帖子----->" + resultInfo.getResult());
                return resultInfo;
            }
            //查询失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryCollectDiscussion-查询我收藏的帖子----->" + resultInfo.getResult());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryCollectDiscussion---web查询我的收藏异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 我关注的人
     *
     * @param pageIndex 当前页
     * @param pageSize  每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryFollowUser", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryFollowUser(HttpServletRequest request, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //校验传入值的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryFollowUser---->userInfo===" + userInfo);
                return resultInfo;
            }
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //传入值校验合法,调用server服务,检验返回值的合法性
            List<UserInfo> userInfoList = bbsManageService.queryFollowUser(userInfo, pageIndex, pageSize);
            if (null != userInfoList && !userInfoList.isEmpty()) {
                List<Map<String, Object>> list = new ArrayList<>();
                for (UserInfo userInfo1 : userInfoList) {
                    //拼接我关注的人
                    Map<String, Object> map = new HashMap<>();
                    map.put("realName", userInfo1.getRealName());
                    map.put("avatar", userInfo1.getAvatar());
                    Map<String, Object> map1 = userManageService.getUserInfoDetailByUserId(userInfo, userInfo1.getUserId());
                    if (map1 != null) {
                        map.put("departMentName", map1.get("DepartmentName"));
                    } else {
                        map.put("departMentName", null);
                    }
                    list.add(map);
                }

                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("queryFollowUser====>>" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryFollowUser查询我关注的人====>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryFollowUser---web查询我关注的人异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 积分排行榜
     *
     * @param pageIndex 当前页
     * @param pageSize  每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryIntegralRanking", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryIntegralRanking(HttpServletRequest request, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryIntegralRanking---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //传入值校验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> rankList = bbsManageService.queryIntegralRanking(userInfo, pageIndex, pageSize);
            if (null != rankList && !rankList.isEmpty()) {
                //返回值校验合法
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(rankList);
                logger.info("queryIntegralRanking-积分排行榜----->" + resultInfo.getResult());
                return resultInfo;
            }
            //返回值检验不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryIntegralRanking-积分排行榜----->" + resultInfo.getResult());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryIntegralRanking---web积分排行榜异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 提问题
     *
     * @param title       标题
     * @param queDesc     问题描述
     * @param tagStrings  标签名(字符串)
     * @param essayTypeId 帖子分类
     * @param repCatId1   一级知识分类
     * @param repCatId2   二级知识分类,可为空
     * @param flowId      流程分类
     * @return resultInfo
     */
    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addQuestion(HttpServletRequest request, String title, String queDesc, String tagStrings, Integer essayTypeId, Integer repCatId1, Integer repCatId2, Integer flowId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //校验传入值的合法性
            if (userInfo == null || !StringUtils.hasText(title) || !StringUtils.hasText(queDesc) || !StringUtils.hasText(tagStrings) || essayTypeId == null || repCatId1 == null || flowId == null) {
                //参数校验不合法
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("addQuestion---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //逗号替换,去空格
            String tagStrings1 = tagStrings.replaceAll("，", ",");
            String tagStrings2 = tagStrings1.replaceAll(" ", "");
            repCatId2 = repCatId2 == 0 ? null : repCatId2;
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer result = bbsManageService.addQuestion(userInfo, title, queDesc, tagStrings2, essayTypeId, repCatId1, repCatId2, flowId);
            if (result != null) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.QUESTION_ADD_SUCCESS);
                    logger.info("addQuestion---web社区提问题>>" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -1) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.TAG_NOT_RIGHT);
                    logger.info("addQuestion---web社区提问题>>" + resultInfo.getMsg());
                    return resultInfo;
                }
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.SUBMIT_FAIL_MSG);
                logger.info("addQuestion---web社区提问题>>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.SUBMIT_FAIL_MSG);
            logger.info("addQuestion---web社区提问题>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addQuestion---web社区提问题异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 分享知识/帖子
     *
     * @param title       标题
     * @param artContent  问题描述
     * @param tagStrings  标签名(字符串)
     * @param essayTypeId 帖子分类
     * @param repCatId1   一级知识分类
     * @param repCatId2   二级知识分类
     * @param flowId      流程分类
     * @return resultInfo
     */
    @RequestMapping(value = "/addEssayInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addEssayInfo(HttpServletRequest request, String title, String artContent, String tagStrings, Integer essayTypeId, Integer repCatId1, Integer repCatId2, Integer flowId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //校验传入参数的合法性
            if (userInfo == null || !StringUtils.hasText(title) || !StringUtils.hasText(artContent) || !StringUtils.hasText(tagStrings) || essayTypeId == null || repCatId1 == null || flowId == null) {
                //参数校验不合法
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("addEssayInfo---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //逗号替换,去空格
            String tagStrings1 = tagStrings.replaceAll("，", ",");
            String tagStrings2 = tagStrings1.replaceAll(" ", "");
            repCatId2 = repCatId2 == 0 ? null : repCatId2;
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer result = bbsManageService.addEssayInfo(userInfo, title, artContent, tagStrings2, essayTypeId, repCatId1, repCatId2, flowId);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.ESSAY_ADD_SUCCESS);
                logger.info("addEssayInfo---web分享知识/帖子>>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
            logger.info("addQuestion---web分享知识/帖子>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addEssayInfo---web分享知识/帖子异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询社区界面搜索列表
     *
     * @param param     搜索框参数
     * @param pageIndex 当前页
     * @param pageSize  每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryDiscussionList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryDiscussionList(HttpServletRequest request, String param, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //验证传入值的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryDiscussionList-查询社区界面搜索列表----->userInfo==" + userInfo);
                return resultInfo;
            }
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //传入值合法,调用server服务,验证返回值的合法性
            List<Map<String, Object>> list = bbsManageService.queryDiscussionList(userInfo, param, pageIndex, pageSize);
            if (null != list && !(list.isEmpty())) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("queryDiscussionList===查询社区界面搜索列表>>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryDiscussionList===查询社区界面搜索列表>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryDiscussionList---web查询社区界面搜索列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询社区界面搜索列表总条数
     *
     * @param param 搜索框参数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryDiscussionListCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryDiscussionListCount(HttpServletRequest request, String param) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验bookId,bookName,author,userInfo参数的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryDiscussionListCount-参数校验不正确----->userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值合法性
            Integer count = bbsManageService.queryDiscussionListCount(userInfo, param);
            if (count != null && count > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(count);
                logger.info("queryDiscussionListCount===查询社区界面搜索列表>>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryDiscussionListCount===查询社区界面搜索列表>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryDiscussionListCount---web查询社区界面搜索列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 关注/取消关注
     *
     * @param userId 被关注者Id
     * @return resultInfo
     */
    @RequestMapping(value = "/addFollow", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addFollow(HttpServletRequest request, Integer userId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验bookId,bookName,author,userInfo参数的合法性
            if (null == userInfo || null == userId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addFollow-参数校验不正确----->userInfo==" + userInfo + ",userId==" + userId);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值合法性
            Integer result = bbsManageService.addFollow(userInfo, userId);
            if (result != null) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.ADD_FOLLOW_SUCCESS_MSG);
                    logger.info("addFollow--关注用户>>" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == 2) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.CANCEL_FOLLOW_SUCCESS);
                    logger.info("addFollow--关注用户>>" + resultInfo.getMsg());
                    return resultInfo;
                }
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.CANCEL_FOLLOW_SUCCESS);
                logger.info("addFollow--关注用户>>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            logger.info("addFollow--关注用户>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addFollow---web关注用户异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 收藏/取消收藏
     *
     * @param targetType 类型：1，贴文，2，提问
     * @param disId      社区Id
     * @return resultInfo
     */
    @RequestMapping(value = "/addCollect", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addCollect(HttpServletRequest request, Integer targetType, Integer disId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || null == targetType || null == disId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("addCollect---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer result = bbsManageService.addCollect(userInfo, targetType, disId);
            if (result != null) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.COLLECT_SUCCESS_MSG);
                    logger.info("addCollect收藏/取消收藏---web收藏/取消收藏" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == 2) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.CANCEL_COLLECT_SUCCESS);
                    logger.info("addCollect收藏/取消收藏---web收藏/取消收藏" + resultInfo.getMsg());
                    return resultInfo;
                }
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
                logger.info("addCollect收藏/取消收藏---web收藏/取消收藏" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            logger.info("addCollect收藏/取消收藏---web收藏/取消收藏" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addCollect收藏/取消收藏---web收藏/取消收藏异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 评论贴文
     *
     * @param comContent 评论内容
     * @param essayId    贴文Id
     * @param disId      社区Id
     * @return resultInfo
     */
    @RequestMapping(value = "/addCommentInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addCommentInfo(HttpServletRequest request, String comContent, Integer essayId, Integer disId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对输入参数进行检验
            if (null == userInfo || null == essayId || !StringUtils.hasText(comContent)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addCommentInfo-评论贴文essayId----->" + essayId + ",userInfo==" + userInfo + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,对返回值进行校验
            Integer result = bbsManageService.addCommentInfo(userInfo, comContent, essayId, disId);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.COMMENT_ADD_SUCCESS);
                logger.info("addCommentInfo---web评论贴文>>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.COMMENT_ADD_FAIL);
            logger.info("addCommentInfo---web评论贴文>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addCommentInfo---web评论贴文异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 回答提问
     *
     * @param ansContent 回答内容
     * @param queId      提问Id
     * @return resultInfo
     * @paramrequest 获取session
     */
    @RequestMapping(value = "/addAnswerInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addAnswerInfo(HttpServletRequest request, String ansContent, Integer queId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == queId || !StringUtils.hasText(ansContent)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addAnswerInfo-回答提问" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Integer result = bbsManageService.addAnswerInfo(userInfo, ansContent, queId);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.ANSWER_SUCCESS_MSG);
                logger.info("addCommentInfo---web回答提问>>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            logger.info("addCommentInfo---web回答提问>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addAnswerInfo---web回答提问异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 设为最佳答案
     *
     * @param ansId 回答Id
     * @param queId 提问Id
     * @param disId 社区Id
     * @return result -1：已有最佳答案，不可再设;1:成功 ,resultInfo
     */
    @RequestMapping(value = "/addBestAnswer", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addBestAnswer(HttpServletRequest request, Integer ansId, Integer queId, Integer disId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == queId || null == ansId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addBestAnswer-设为最佳答案" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Integer result = bbsManageService.addBestAnswer(userInfo, ansId, queId, disId);
            if (result != null) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.OPERATE_SUCCESS_MSG);
                    logger.info("addBestAnswer---web设为最佳答案>>" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -1) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.BESTANSWER_ALREADY_EXIST);
                    logger.info("addBestAnswer---web设为最佳答案>>" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            logger.info("addBestAnswer---web设为最佳答案>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addBestAnswer---web设为最佳答案异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询评论/回答列表
     *
     * @param pageIndex 当前页
     * @param pageSize  每页条数
     * @param disId     社区Id
     * @return resultInfo
     */
    @RequestMapping(value = "/queryCommentInfoList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryCommentInfoList(HttpServletRequest request, Integer pageIndex, Integer pageSize, Integer disId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == disId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryCommentInfoList-disId----->" + disId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> list = bbsManageService.queryComOrAnsList(userInfo, pageIndex, pageSize, disId);
            if (list != null && !(list.isEmpty())) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("queryCommentInfoList---web查询评论/回答列表!" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Succeed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
            logger.info("queryCommentInfoList---web查询评论/回答列表!" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryCommentInfoList---web查询评论/回答列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询评论/回答列表总条数
     *
     * @param disId 社区Id
     * @return resultInfo
     */
    @RequestMapping(value = "/queryCommentInfoListCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryCommentInfoListCount(HttpServletRequest request, Integer disId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || disId == null) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryCommentInfoListCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //传入值检验合法,返回信息
            Integer result = bbsManageService.queryComOrAnsListCount(userInfo, disId);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(bbsManageService.queryComOrAnsListCount(userInfo, disId));
                logger.info("queryCommentInfoListCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryCommentInfoListCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryCommentInfoListCount---queryCommentInfoListCount异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 点赞/取消点赞
     *
     * @param UsefulTyp  点赞对象：1.贴文评论；2.提问回答
     * @param relationId 对象Id
     * @return resultInfo
     */
    @RequestMapping(value = "/addDisctLike", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addDisctLike(HttpServletRequest request, Integer UsefulTyp, Integer relationId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || null == UsefulTyp || null == relationId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("addDisctLike---->" + resultInfo.getMsg());
                return resultInfo;
            }
            Map<String, Object> result = bbsManageService.addDisctLike(userInfo, UsefulTyp, relationId);
            if (result != null && !result.isEmpty()) {
                if ((Integer) result.get("result") == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.ADD_LIKE_SUCCESS);
                    resultInfo.setResult(result.get("likeCount"));
                    logger.info("addDisctLike--点赞/取消点赞>>" + resultInfo.getMsg());
                    return resultInfo;
                }
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.RECALL_LIKE_SUCCESS);
                resultInfo.setResult(result.get("likeCount"));
                logger.info("addDisctLike--点赞/取消点赞>>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            logger.info("addDisctLike--点赞/取消点赞>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addDisctLike---web点赞/取消点赞异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 有用
     *
     * @param UsefulTyp  有用对象：1.贴文；2.最佳回答
     * @param relationId 对象Id
     * @param disId      社区Id
     * @return result 1：成功，其他：失败
     */
    @RequestMapping(value = "/addUseful", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addUseful(HttpServletRequest request, Integer UsefulTyp, Integer relationId, Integer disId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || null == UsefulTyp || null == relationId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("addUseful---->" + resultInfo.getMsg());
                return resultInfo;
            }
            Integer result = bbsManageService.addUseful(userInfo, UsefulTyp, relationId, disId);
            if (result != null) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                    logger.info("addUseful--有用>>" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.USERFUL_HAVE_ALREADY);
                    logger.info("addUseful--有用>>" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            logger.info("addUseful--有用>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addUseful---web有用异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 无用
     *
     * @param UsefulTyp  有用对象：1.贴文；2.最佳回答
     * @param relationId 对象Id
     * @param disId      社区Id
     * @return result 1：成功，其他：失败
     */
    @RequestMapping(value = "/addUnUseful", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addUnUseful(HttpServletRequest request, Integer UsefulTyp, Integer relationId, Integer disId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || null == UsefulTyp || null == relationId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("addUnUseful---->" + resultInfo.getMsg());
                return resultInfo;
            }
            Integer result = bbsManageService.addUnUseful(userInfo, UsefulTyp, relationId, disId);
            if (result != null) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                    logger.info("addUnUseful--无用>>" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.UNUSERFUL_HAVE_ALREADY);
                    logger.info("addUnUseful--无用>>" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            logger.info("addUnUseful--无用>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addUnUseful---web无用异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 通过disId查询社区详细
     *
     * @param disId 社区帖子Id
     * @return ResultInfo
     */
    @RequestMapping(value = "/queryDisInfoByDisId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryDisInfoByDisId(HttpServletRequest request, Integer disId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == disId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryDisInfoByDisId---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Map<String, Object> map = bbsManageService.queryDisInfoByDisId(userInfo, disId);
            if (map != null && !map.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(map);
                logger.info("queryDisInfoByDisIdweb通过disId查询社区详细" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Succeed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
            logger.info("queryDisInfoByDisIdweb通过disId查询社区详细" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryDisInfoByDisId---web通过disId查询社区详细异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 社区加入知识仓库
     *
     * @param dis 社区对象Id
     * @return resultInfo
     */
    @RequestMapping(value = "/addBbsToKnlg", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addBbsToKnlg(HttpServletRequest request, Integer dis) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == dis) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("addBbsToKnlg---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Integer result = bbsManageService.addBbsToKnlg(userInfo, dis);
            if (result != null) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.ADD_KNLG_SUCCESS);
                    logger.info("addBbsToKnlg---web社区加入知识仓库)" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -2) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.ADD_KNLG_SUCCESS_ALREADY);
                    logger.info("addBbsToKnlg---web社区加入知识仓库)" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -3) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.ADD_KNLG_FAIL);
                    logger.info("addBbsToKnlg---web社区加入知识仓库)" + resultInfo.getMsg());
                    return resultInfo;
                }
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
                logger.info("addBbsToKnlg---web社区加入知识仓库)" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            logger.info("addBbsToKnlg---web社区加入知识仓库)" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addBbsToKnlg---web社区加入知识仓库异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 相关推荐 --- 社区
     *
     * @param disId 社区Id
     * @return resultInfo
     */
    @RequestMapping(value = "/queryBbsRecommendByDisId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryBbsRecommendByDisId(HttpServletRequest request, Integer disId, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验bookId,bookName,author,userInfo参数的合法性
            if (null == userInfo || null == disId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryBbsRecommendByDisId-参数校验不正确----->disId==" + disId + "userInfo==" + userInfo);
                return resultInfo;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //参数检验合法,调用server服务,检验返回值合法性
            List<Map<String, Object>> list = bbsManageService.queryBbsRecommendByDisId(userInfo, disId, pageSize);
            if (list != null && !(list.isEmpty())) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("queryBbsRecommendByDisId---web相关推荐 --- 社区>>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryBbsRecommendByDisId---web相关推荐 --- 社区>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryBbsRecommendByDisId---web相关推荐 --- 社区异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 积分排行榜总条数
     *
     * @return resultInfo
     */
    @RequestMapping(value = "/queryIntegralRankingCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryIntegralRankingCount(HttpServletRequest request) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryIntegralRankingCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //传入值检验合法,返回信息
            Integer result = bbsManageService.queryIntegralRankingCount(userInfo);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryIntegralRankingCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryIntegralRankingCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryIntegralRankingCount---web积分排行榜总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 查询我的关注帖子收藏总条数
     *
     * @param disType 1:  贴文/分享;2: 提问
     * @return resultInfo
     */
    @RequestMapping(value = "/queryMyselfCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryMyDiscussionCount(HttpServletRequest request, Integer disType) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryMyDiscussionCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //传入值检验合法,返回信息
            Map<String, Object> map = new HashMap<>();
            //map中帖子收藏提问赋初值0
            map.put("DiscussionCount", 0);
            map.put("CollectCount", 0);
            map.put("FollowUserCount", 0);
            //关注的帖子数目
            Integer discussionCount = bbsManageService.queryMyDiscussionCount(userInfo, disType);
            if (null != discussionCount && discussionCount > 0) {
                map.put("DiscussionCount", discussionCount);
            }
            //我的收藏数
            Integer collectCount = bbsManageService.queryCollectDiscussionCount(userInfo, disType);
            if (null != collectCount && collectCount > 0) {
                map.put("CollectCount", collectCount);
            }
            //关注的总人数
            Integer followUserCount = bbsManageService.queryFollowUserCount(userInfo);
            if (null != followUserCount && followUserCount > 0) {
                map.put("FollowUserCount", followUserCount);
            }
            resultInfo.setCode(ResponseCode.Succeed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
            resultInfo.setResult(map);
            logger.info("queryMyDiscussionCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryMyDiscussionCount---web查询我的帖子总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询我收藏的帖子总条数
     *
     * @param disType 1:  贴文/分享;2: 提问
     * @return resultInfo
     */
    @RequestMapping(value = "/queryCollectDiscussionCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryCollectDiscussionCount(HttpServletRequest request, Integer disType) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryCollectDiscussionCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //传入值检验合法,返回信息
            Integer result = bbsManageService.queryCollectDiscussionCount(userInfo, disType);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryCollectDiscussionCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryCollectDiscussionCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryCollectDiscussionCount---web查询我收藏的帖子总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询我关注的人总条数
     *
     * @return resultInfo
     */
    @RequestMapping(value = "/queryFollowUserCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryFollowUserCount(HttpServletRequest request) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryFollowUserCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //传入值检验合法,返回信息
            Integer result = bbsManageService.queryFollowUserCount(userInfo);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryFollowUserCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryFollowUserCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryFollowUserCount---web查询我关注的人总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 通过disId查询有用数和无用数
     *
     * @param disId 社区帖子Id
     * @return resultInfo
     */
    @RequestMapping(value = "/queryUsefulCountByDisId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryUsefulCountByDisId(HttpServletRequest request, Integer disId) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || null == disId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryUsefulCountByDisId---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //传入值检验合法,返回信息
            Map<String, Object> map = bbsManageService.queryUsefulCountByDisId(userInfo, disId);
            if (null != map && !map.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(map);
                logger.info("queryUsefulCountByDisId---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryUsefulCountByDisId---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryUsefulCountByDisId---web通过disId查询有用数和无用数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }

    /**
     * 社区标签搜索列表
     *
     * @param param     标签
     * @param pageIndex 当前页
     * @param pageSize  每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/showBbsByTags", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo showKnowledgeByTags(HttpServletRequest request, String param, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || !StringUtils.hasText(param)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("showBbsByTags-社区标签搜索列表----->" + resultInfo.getMsg());
                return resultInfo;
            }
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> list = bbsManageService.showBbsByTags(userInfo, param, pageIndex, pageSize);
            if (list != null && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("showBbsByTags---社区标签搜索列表>>>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值校验不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("showBbsByTags---社区标签搜索列表>>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("showBbsByTags---社区标签搜索列表!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 社区标签搜索列表总条数
     *
     * @param param 标签
     * @return resultInfo
     */
    @RequestMapping(value = "/queryBbsByTagsCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryKnowledgeByTagsCount(HttpServletRequest request, String param) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || !StringUtils.hasText(param)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryBbsByTagsCount-社区标签搜索列表总条数----->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer result = bbsManageService.queryBbsByTagsCount(userInfo, param);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryBbsByTagsCount---社区标签搜索列表总条数>>>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值校验不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryBbsByTagsCount---社区标签搜索列表总条数>>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryBbsByTagsCount---社区标签搜索列表总条数!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


}