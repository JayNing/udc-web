package com.mascot.controller;


import com.interfaces.file.BasicService;
import com.interfaces.mascot.KnowledgeManageService;
import com.mascot.bean.ResultInfo;
import com.mascot.bean.SessionInfo;
import com.mascot.constant.UdcConstant;
import com.mascot.utils.*;
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
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 知识仓库
 *
 * @author jichao
 * 2018/5/03
 */
@Controller
@RequestMapping(value = "/knowledgeManage")
public class KnowledgeManageController {

    @Autowired
    private KnowledgeManageService knowledgeManageService;

    @Autowired
    private BasicService basicService;

    private static final Log logger = LogFactory.getLog(KnowledgeManageController.class);


    /**
     * 录入知识
     *
     * @param title      知识标题
     * @param author     知识作者 - 可为空
     * @param profile    知识简介
     * @param knlgType   知识类型：1.文章，2.非文章
     * @param artContent 文章内容 ,可为空,例:只上传附件无文章内容
     * @param tagStrings 关键字(,分割的字符串)
     * @param fileList   附件url集合
     * @param repCatId1  一级知识分类
     * @param repCatId2  二级知识分类
     * @param flowId     流程节点分类
     * @param bbsFlag    是否社区沉淀,复用接口,此处默认1,社区知识沉淀亦可用
     * @return resultInfo
     */
    @RequestMapping(value = "/addKnowledge", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addKnowledge(HttpServletRequest request, String title, String author, String profile, Integer knlgType,
                                   String artContent, String tagStrings, String[] fileList, Integer repCatId1,
                                   Integer repCatId2, Integer flowId, Integer bbsFlag) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("onlineInfo");
            UserInfo userInfo = null;
            if (sessionInfo != null) {
                userInfo = SessionGetUtil.getSession(request);
            }
            //检验传入参数的合法性
            if (null == userInfo || !StringUtils.hasText(title) || !StringUtils.hasText(profile) || null == knlgType || !StringUtils.hasText(tagStrings)
                    || null == repCatId1 || null == bbsFlag || null == flowId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addKnowledge录入知识---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //逗号替换,去空格
            String tagStrings1 = tagStrings.replaceAll("，", ",");
            String tagStrings2 = tagStrings1.replaceAll(" ", "");
            List<Map<String, Object>> list = new ArrayList<>();
            //判断知识类型
            if (!StringUtils.hasText(artContent) || !("<p><br></p>").equals(artContent)) {
                knlgType = 1;
            } else if (fileList != null && fileList.length > 0) {
                knlgType = 2;
            } else {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.KNLG_COMMENT_NULL);
                logger.info("addKnowledge录入知识---->" + resultInfo.getMsg());
                return resultInfo;
            }
            if (fileList != null && fileList.length > 0) {
                for (String str : fileList) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("FileUrl", str);
                    int one = str.lastIndexOf(".");
                    String extName = str.substring((one + 1), str.length());
                    Integer fileType = IsKindOfUtil.getFileKind(extName);
                    map.put("FileType", fileType);
                    list.add(map);
                }

            }
            repCatId2 = repCatId2 == 0 ? null : repCatId2;
            //附件上传成功,调用server服务,检验返回值信息
            Integer result = knowledgeManageService.addKnowledge(userInfo, title, author, profile, knlgType, artContent, tagStrings2, list, repCatId1, repCatId2, flowId, bbsFlag);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.INSERT_KNOWLEDGE_SUCCESS);
                logger.info("addKnowledge知识仓库------>>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.ADD_FAILINTO_DATABASE);
            logger.info("addKnowledge知识仓库------>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getDepartmentList---web新增知识异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 查询知识仓库管理列表
     *
     * @param title      知识标题
     * @param repCatId1  一级分类
     * @param repCatId2  二级分类
     * @param flowId     流程分类
     * @param start1Time 起始时间
     * @param end1Time   截止时间
     * @param pageIndex  当前页
     * @param pageSize   每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryKnowledgeInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryKnowledgeInfo(HttpServletRequest request, String title, Integer repCatId1, Integer repCatId2, Integer flowId,
                                         String start1Time, String end1Time, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryKnowledgeInfo---->" + resultInfo.getMsg());
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
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //参数校验合法,调用server服务,验证返回值的合法性
            List<Map<String, Object>> mapList = knowledgeManageService.queryKnowledgeInfo(userInfo, title, repCatId1, repCatId2, flowId, startTime, endTime, pageIndex, pageSize);
            if (null != mapList && !mapList.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(mapList);
                logger.info("queryKnowledgeInfo查询知识仓库管理列表---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryKnowledgeInfo查询知识仓库管理列表---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryKnowledgeInfo---web查询知识仓库管理列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询知识仓库管理列表总条数
     *
     * @param title      知识标题
     * @param repCatId1  一级分类
     * @param repCatId2  二级分类
     * @param flowId     流程分类
     * @param start1Time 起始时间
     * @param end1Time   截止时间
     * @return resultInfo
     */
    @RequestMapping(value = "/queryKnowledgeInfoCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryKnowledgeInfoCount(HttpServletRequest request, String title, Integer repCatId1, Integer repCatId2, Integer flowId,
                                              String start1Time, String end1Time) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryKnowledgeInfoCount----->userInfo==" + userInfo);
                return resultInfo;
            }
            Date startTime = null;
            Date endTime = null;
            if (StringUtils.hasText(start1Time)) {
                startTime = DateTransferUtil.dateTransfer(start1Time);
            }
            if (StringUtils.hasText(end1Time)) {
                endTime = DateTransferUtil.dateTransfer(end1Time);
            }
            //参数校验合法,调用server服务,校验返回值的合法性
            Integer result = knowledgeManageService.queryKnowledgeInfoCount(userInfo, title, repCatId1, repCatId2, flowId, startTime, endTime);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryKnowledgeInfoCount查询知识仓库管理列表总条数---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,查询失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryKnowledgeInfoCount查询知识仓库管理列表总条数--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryKnowledgeInfoCount---web查询知识仓库管理列表总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 智能搜索知识仓库列表
     *
     * @param param     搜索框参数 --- 不为空
     * @param pageIndex 当前页
     * @param pageSize  每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryKnowledgeInfoIntelligent", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryKnowledgeInfoIntelligent(HttpServletRequest request, String param, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || !StringUtils.hasText(param)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryKnowledgeInfoIntelligent参数不合法---->userInfo==" + resultInfo + ",param==" + param);
                return resultInfo;
            }
            //对传入的pageIndex和pageSize进行校验
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //参数校验合法,调用server服务,对返回值进行校验
            List<Map<String, Object>> mapList = knowledgeManageService.queryKnowledgeInfoIntelligent(userInfo, param, pageIndex, pageSize);
            if (null != mapList && !mapList.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(mapList);
                logger.info("queryKnowledgeInfoIntelligent智能搜索知识仓库列表---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryKnowledgeInfoIntelligent查询知识仓库管理列表---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryKnowledgeInfoIntelligent---web智能搜索知识仓库列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 智能搜索知识仓库列表总条数
     *
     * @param param 搜索框参数 --- 不为空
     * @return resultInfo
     */
    @RequestMapping(value = "/queryKnowledgeInfoIntelligentCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryKnowledgeInfoIntelligentCount(HttpServletRequest request, String param) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || !StringUtils.hasText(param)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryKnowledgeInfoIntelligentCount参数不合法---->userInfo==" + resultInfo + ",param==" + param);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer resulr = knowledgeManageService.queryKnowledgeInfoIntelligentCount(userInfo, param);
            if (null != resulr && resulr > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(resulr);
                logger.info("queryKnowledgeInfoIntelligentCount查询知识仓库管理列表总条数---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,查询失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryKnowledgeInfoIntelligentCount查询知识仓库管理列表总条数--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryKnowledgeInfoIntelligentCount---web智能搜索知识仓库列表总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 最近浏览知识列表
     *
     * @param num 最大展示条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryKnowledgeInfoRecently", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryKnowledgeInfoRecently(HttpServletRequest request, Integer num) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == num) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryKnowledgeInfoRecently参数不合法---->userInfo==" + resultInfo + ",num==" + num);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> mapList = knowledgeManageService.queryKnowledgeInfoRecently(userInfo, num);
            if (null != mapList && !mapList.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(mapList);
                logger.info("queryKnowledgeInfoRecently最近浏览知识列表---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回失败信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryKnowledgeInfoRecently最近浏览知识列表---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryKnowledgeInfoRecently---web最近浏览知识列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 公司热点知识列表
     *
     * @param num 最大展示条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryKnowledgeInfoHot", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryKnowledgeInfoHot(HttpServletRequest request, Integer num) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == num) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryKnowledgeInfoHot参数不合法---->userInfo==" + resultInfo + ",num==" + num);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> mapList = knowledgeManageService.queryKnowledgeInfoHot(userInfo, num);
            if (null != mapList && !mapList.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(mapList);
                logger.info("queryKnowledgeInfoHot公司热点知识列表---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryKnowledgeInfoHot公司热点知识列表---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryKnowledgeInfoHot---web公司热点知识列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 热门标签
     *
     * @param num 最大展示数量
     * @return resultInfo
     */
    @RequestMapping(value = "/updateTagInfoCounts", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateTagInfoCounts(HttpServletRequest request, Integer num) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == num) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updateTagInfoCounts参数不合法---->userInfo==" + resultInfo + ",num==" + num);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            List<TagInfo> tagInfoList = knowledgeManageService.updateTagInfoCounts(userInfo, num);
            if (null != tagInfoList && !tagInfoList.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(tagInfoList);
                logger.info("updateTagInfoCounts热门标签---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("updateTagInfoCounts热门标签---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updateTagInfoCounts---web热门标签异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 查看知识详情
     *
     * @param kId       知识编号
     * @param viewOrUpd 标志：1，修改时需要点开的详情界面（不计浏览量）；2，浏览时点开的详情界面（计浏览量）
     * @return resultInfo
     */
    @RequestMapping(value = "/queryKnowledgeInfoDetails", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryKnowledgeInfoDetails(HttpServletRequest request, Integer kId, Integer viewOrUpd) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == kId || null == viewOrUpd) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryKnowledgeInfoDetails参数不合法---->userInfo==" + resultInfo + ",kId==" + kId + ",viewOrUpd==" + viewOrUpd);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值map的合法性
            Map<String, Object> map = knowledgeManageService.showKnowledgeInfoDetails(userInfo, kId, viewOrUpd);
            if (null != map && !map.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                //结果中获取文件的url
                // String  FileUrl =  (String) knowledgeManageService.queryKnowledgeInfoDetails(userInfo, kId, viewOrUpd).get("FileUrl");
                resultInfo.setResult(knowledgeManageService.showKnowledgeInfoDetails(userInfo, kId, 1));
                logger.info("queryKnowledgeInfoDetails--->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryKnowledgeInfoDetails--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryKnowledgeInfoDetails---web热门标签异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 修改知识
     *
     * @param kId        知识编号
     * @param title      知识标题
     * @param author     知识作者 - 可为空
     * @param profile    知识简介
     * @param knlgType   知识类型：1.文章，2.非文章
     * @param artContent 文章内容
     * @param tagStrings 标签名(字符串),逗号分隔
     * @param repCatId1  一级知识分类
     * @param repCatId2  二级知识分类
     * @param flowId     流程分类
     * @return 1:成功；其他：失败 ,resultInfo
     */
    @RequestMapping(value = "/updateKnowledge", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateKnowledge(HttpServletRequest request, Integer kId, String title, String author, String profile,
                                      Integer knlgType, String artContent, String tagStrings, Integer repCatId1,
                                      Integer repCatId2, Integer flowId, String[] fileList) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == kId || !StringUtils.hasText(title) || !StringUtils.hasText(profile) || null == knlgType || !StringUtils.hasText(tagStrings)
                    || null == repCatId1 || null == flowId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updateKnowledge修改知识---->" + resultInfo.getMsg());
                return resultInfo;
            }

            //逗号替换,去空格
            String tagStrings1 = tagStrings.replaceAll("，", ",");
            String tagStrings2 = tagStrings1.replaceAll(" ", "");

            //判断知识类型
            if (StringUtils.hasText(artContent)) {
                knlgType = 1;
            } else if (fileList != null && fileList.length > 0) {
                knlgType = 2;
            } else {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.KNLG_COMMENT_NULL);
                logger.info("updateKnowledge编辑知识---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //拼接参数
            List<Map<String, Object>> list = new ArrayList<>();
            for (String str : fileList) {
                Map<String, Object> map = new HashMap<>();
                map.put("FileUrl", str);
                int one = str.lastIndexOf(".");
                String extName = str.substring((one + 1), str.length());
                Integer fileType = IsKindOfUtil.getFileKind(extName);
                map.put("FileType", fileType);
                list.add(map);
            }
            repCatId2 = repCatId2 == 0 ? null : repCatId2;
            //附件上传成功,调用server服务,检验返回值信息
            Integer result = knowledgeManageService.updateKnowledge(userInfo, kId, title, author, profile, knlgType, artContent, tagStrings2, repCatId1, repCatId2, flowId, list);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.UPDATE_KNOWLEDGE_SUCCESS);
                logger.info("updateKnowledge修改知识------>>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.UPDATE_KNOWLEDGE_FAIL);
            logger.info("updateKnowledge修改知识------>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updateKnowledge---web修改知识异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 删除知识
     *
     * @param kId         知识编号
     * @param knlgType    知识类型
     * @param artOrFileId 关联Id
     * @return 1:成功；其他：失败
     */
    @RequestMapping(value = "/deleteKnowledge", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteKnowledge(HttpServletRequest request, Integer kId, Integer knlgType, Integer artOrFileId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == userInfo || null == kId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryKnowledgeInfoDetails参数不合法---->userInfo==" + resultInfo + ",kId==" + kId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer result = knowledgeManageService.deleteKnowledge(userInfo, kId);
            if (null != result && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_SUCCESS_MSG);
                logger.info("deleteKnowledge删除知识---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,删除失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deleteKnowledge删除知识---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("deleteKnowledge---web删除知识异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 根据标签名/关键字查询标签信息
     *
     * @param tagName 标签名/关键字
     * @return resultInfo
     */
    @RequestMapping(value = "/queryTagInfoByTagName", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryTagInfoByTagName(HttpServletRequest request, String tagName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || !StringUtils.hasText(tagName)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryTagInfoByTagName---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            TagInfo tagInfo = knowledgeManageService.queryTagInfoByTagName(userInfo, tagName);
            if (tagInfo != null) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(tagInfo);
                logger.info("queryTagInfoByTagName---web根据标签名/关键字查询标签信息" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryTagInfoByTagName---web根据标签名/关键字查询标签信息" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryTagInfoByTagName---web根据标签名/关键字查询标签信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 智能推荐知识列表
     *
     * @param num 最大展示条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryKnowledgeInfoIntel", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryKnowledgeInfoIntelligently(HttpServletRequest request, Integer num) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == num) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryKnowledgeInfoIntel-智能推荐知识列表num----->" + num + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> list = knowledgeManageService.queryKnowledgeInfoIntelligently(userInfo, num);
            if (list != null && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("queryKnowledgeInfoIntel---web智能推荐知识列表>>>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值校验不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryKnowledgeInfoIntel---web智能推荐知识列表>>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryKnowledgeInfoIntel---web智能推荐知识列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 相关知识推荐
     * 规则：相同知识库分类下，浏览数最多的知识
     *
     * @param kId 知识Id
     * @param num 最大展示条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryRelatedKnowledge", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryRelatedKnowledge(HttpServletRequest request, Integer kId, Integer num) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == num || null == kId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryRelatedKnowledge-相关知识推荐----->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> list = knowledgeManageService.queryRelatedKnowledge(userInfo, kId, num);
            if (list != null && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("queryRelatedKnowledge---相关知识推荐>>>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值校验不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryRelatedKnowledge---相关知识推荐>>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryRelatedKnowledge---相关知识推荐!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 标签搜索列表
     *
     * @param param     标签
     * @param pageIndex 当前页
     * @param pageSize  每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/showKnowledgeByTags", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo showKnowledgeByTags(HttpServletRequest request, String param, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || !StringUtils.hasText(param)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("showKnowledgeByTags-标签搜索列表----->" + resultInfo.getMsg());
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
            List<Map<String, Object>> list = knowledgeManageService.showKnowledgeByTags(userInfo, param, pageIndex, pageSize);
            if (list != null && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("showKnowledgeByTags---标签搜索列表>>>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值校验不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("showKnowledgeByTags---标签搜索列表>>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("showKnowledgeByTags---标签搜索列表!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 标签搜索列表总条数
     *
     * @param param 标签
     * @return resultInfo
     */
    @RequestMapping(value = "/queryKnowledgeByTagsCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryKnowledgeByTagsCount(HttpServletRequest request, String param) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || !StringUtils.hasText(param)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryKnowledgeByTagsCount-标签搜索列表总条数----->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer result = knowledgeManageService.queryKnowledgeByTagsCount(userInfo, param);
            if (result != null && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryKnowledgeByTagsCount---标签搜索列表总条数>>>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值校验不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryKnowledgeByTagsCount---标签搜索列表总条数>>>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryKnowledgeByTagsCount---标签搜索列表总条数!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


}
