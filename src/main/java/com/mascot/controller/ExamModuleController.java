package com.mascot.controller;

import com.interfaces.mascot.ExamManageService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 考试模块
 *
 * @author jichao
 * 2018/5/20
 */

@Controller
@RequestMapping(value = "/examModule")
public class ExamModuleController {

    @Autowired
    private ExamManageService examManageService;

    private static final Log logger = LogFactory.getLog(ExamModuleController.class);


    /**
     * 添加题目
     *
     * @param type              题目类型，1单选;2多选;3判断;4简答
     * @param excsTitle         题目内容
     * @param repCatId1         知识库一级分类
     * @param repCatId2         知识库二级分类
     * @param list              选项列表 OptionCode 选项名；OptionDesc 选项值；CrrctAns 是否正确答案 "a,哈哈哈,1",list接收
     * @param currAnswerContent 参考答案
     * @return resultInfo
     */
    @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addTopic(HttpServletRequest request, Integer type, String excsTitle, Integer repCatId1, Integer repCatId2, String list, String currAnswerContent) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,departmentId
            if (null == userInfo || null == type || !StringUtils.hasText(excsTitle) || repCatId1 == null) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addTopic传入值---->" + resultInfo.getMsg());
                return resultInfo;
            }
            List<Map<String, Object>> mapList = new ArrayList<>();

            //对传入list进行解析
            if (StringUtils.hasText(list)) {
                //对传入list进行解析
                String[] spileString = list.split("_");
                for (int i = 0; i < spileString.length; i++) {
                    Map<String, Object> map = new HashMap<>();
                    String str = spileString[i];
                    List<String> list1 = Arrays.asList(str.split(","));
                    map.put("OptionCode", list1.get(0));
                    map.put("OptionDesc", list1.get(1));
                    map.put("CrrctAns", list1.get(2));
                    mapList.add(map);
                }
            }
            //参数校验合法,调用server服务,对返回值进行校验
            Integer result = examManageService.addExercise(userInfo, type, excsTitle, repCatId1, repCatId2, mapList, currAnswerContent);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.INSERT_SUCCESS_MSG);
                logger.info("addTopic---添加题目" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("addTopic---添加题目" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addTopic---web添加题目异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询题目详情
     *
     * @param exercisesId 题目Id
     * @return resultInfo
     */
    @RequestMapping(value = "/queryExerciseDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryExerciseDetail(HttpServletRequest request, Integer exercisesId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,departmentId
            if (null == userInfo || null == exercisesId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryExerciseDetail参数不合法---->userInfo==" + userInfo + ",exercisesId==" + exercisesId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,对返回值进行校验
            Map<String, Object> map = examManageService.queryExerciseDetail(userInfo, exercisesId);
            if (map != null && !map.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(map);
                logger.info("queryExerciseDetail---web查询题目详情" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryExerciseDetail---web查询题目详情" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryExerciseDetail---web查询题目详情异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 修改题目
     *
     * @param type              题目类型，1单选;2多选;3判断;4简答
     * @param excsTitle         题目内容
     * @param repCatId1         知识库一级分类
     * @param repCatId2         知识库二级分类
     * @param list              选项列表 OptionCode 选项名；OptionDesc 选项值；CrrctAns 是否正确答案 ,"a,哈哈哈,1_b,哈哈哈哈哈,1_c,哈哈哈哈,1_d,哈很好看哈,1"
     * @param currAnswerContent 参考答案
     * @return resultInfo
     */
    @RequestMapping(value = "/updateExercise", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateExercise(HttpServletRequest request, Integer exercisesId, Integer type, String excsTitle, Integer repCatId1, Integer repCatId2, String list, String currAnswerContent) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,departmentId
            if (null == userInfo || null == type || !StringUtils.hasText(excsTitle) || repCatId1 == null || null == exercisesId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updateExercise传入值---->" + resultInfo.getMsg());
                return resultInfo;
            }
            List<Map<String, Object>> mapList = new ArrayList<>();

            //对传入list进行解析
            if (StringUtils.hasText(list)) {
                //对传入list进行解析
                String[] spileString = list.split("_");
                for (int i = 0; i < spileString.length; i++) {
                    Map<String, Object> map = new HashMap<>();
                    String str = spileString[i];
                    List<String> list1 = Arrays.asList(str.split(","));
                    map.put("OptionCode", list1.get(0));
                    map.put("OptionDesc", list1.get(1));
                    map.put("CrrctAns", list1.get(2));
                    mapList.add(map);
                }
            }

            //参数校验合法,调用server服务,对返回值进行校验
            Integer result = examManageService.updateExercise(userInfo, exercisesId, type, excsTitle, repCatId1, repCatId2, mapList, currAnswerContent);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.UPFATE_SUCCESS_MSG);
                logger.info("updateExercise---修改题目" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
            logger.info("updateExercise---修改题目" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updateExercise---web修改题目异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 删除题目
     *
     * @param exercisesId 题号
     * @return resultInfo
     */
    @RequestMapping(value = "/deleteExercise", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteExercise(HttpServletRequest request, Integer exercisesId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,departmentId
            if (null == userInfo || null == exercisesId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("deleteExercise参数不合法---->userInfo==" + userInfo + ",exercisesId==" + exercisesId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,对返回值进行校验
            Integer result = examManageService.deleteExercise(userInfo, exercisesId);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_SUCCESS_MSG);
                logger.info("deleteExercise删除题目---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,删除题目失败,返回失败信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deleteExercise删除题目---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("deleteExercise---web删除题目异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询试题管理列表
     *
     * @param exeType    题目类型 1单选2多选3判断4简答
     * @param title      标题
     * @param repCatId1  一级分类
     * @param repCatId2  二级分类
     * @param createName 创建人
     * @param start1Time 起始时间
     * @param end1Time   截止时间
     * @param pageIndex  当前页
     * @param pageSize   每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryExerciseList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryExerciseList(HttpServletRequest request, Integer exeType, String title, Integer repCatId1, Integer repCatId2, String createName,
                                        String start1Time, String end1Time, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryExerciseList---->" + resultInfo.getMsg());
                return resultInfo;
            }
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
            //参数校验合法,调用server服务查询部门信息
            List<Map<String, Object>> result = examManageService.queryExerciseList(userInfo, exeType, title, repCatId1, repCatId2, createName, startTime, endTime, pageIndex, pageSize);
            if (null != result && !result.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryExerciseList---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryExerciseList---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryExerciseList---web查询试题管理列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询试题管理列表总条数
     *
     * @param exeType    题目类型
     * @param title      标题
     * @param repCatId1  一级分类
     * @param repCatId2  二级分类
     * @param createName 创建人
     * @param start1Time 起始时间
     * @param end1Time   截止时间
     * @return resultInfo
     */
    @RequestMapping(value = "/queryExerciseListCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryExerciseListCount(HttpServletRequest request, Integer exeType, String title, Integer repCatId1, Integer repCatId2, String createName,
                                             String start1Time, String end1Time) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入值的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryExerciseListCount---->userInfo===" + userInfo);
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
            Integer result = examManageService.queryExerciseListCount(userInfo, exeType, title, repCatId1, repCatId2, createName, startTime, endTime);
            if (result != null && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryExerciseListCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryExerciseListCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryExerciseListCount---web查询试题管理列表总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 添加试卷
     *
     * @param paperTitle 题目内容
     * @param repCatId1  试卷一级分类
     * @param repCatId2  试卷二级分类 可为空
     * @param radio      单选题 可为空
     * @param multiple   多选题 可为空
     * @param judge      判断题 可为空
     * @param simpleness 简答题  可为空
     * @return resultInfo
     */
    @RequestMapping(value = "/addPaper", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addPaper(HttpServletRequest request, String paperTitle, Integer repCatId1, Integer repCatId2, String radio, String multiple, String judge, String simpleness) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            List<Map<String, Object>> list = new ArrayList<>();
            //单选题
            if (StringUtils.hasText(radio)) {
                if (topicTransfer(radio) != null || !topicTransfer(radio).isEmpty()) {
                    list.add(topicTransfer(radio));
                }
            }
            //多选题
            if (StringUtils.hasText(multiple)) {
                if (topicTransfer(multiple) != null || !topicTransfer(multiple).isEmpty()) {
                    list.add(topicTransfer(multiple));
                }
            }
            //判断题
            if (StringUtils.hasText(judge)) {
                if (topicTransfer(judge) != null || !topicTransfer(judge).isEmpty()) {
                    list.add(topicTransfer(judge));
                }
            }
            //简答题
            if (StringUtils.hasText(simpleness)) {
                if (topicTransfer(simpleness) != null || !topicTransfer(simpleness).isEmpty()) {
                    list.add(topicTransfer(simpleness));
                }
            }
            //检验参数的合法性,包括list
            if (null == userInfo || !StringUtils.hasText(paperTitle) || null == repCatId1 || list == null || list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addPaper添加试卷---->" + resultInfo.getMsg());
                return resultInfo;
            }

            //参数检验合法,调用server服务,检验返回值的合法性
            Integer result = examManageService.addPaper(userInfo, paperTitle, repCatId1, repCatId2, list);
            if (result != null) {
                if (result == -1) {
                    //传入的模块下试题列表参数为空或异常
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
                    logger.info("addPaper添加试卷--->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -2) {
                    //分值不是100!
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.ADDPAPER_COUNT_FAIL_MSG);
                    logger.info("addPaper添加试卷--->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == 1) {
                    //添加成功.
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.INSERT_SUCCESS_MSG);
                    logger.info("addPaper添加试卷--->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -3) {
                    //标题重复!
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.ADDPAPER_EXAM_REPEAT_FAIL);
                    logger.info("addPaper添加试卷--->" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("addPaper添加试卷--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addPaper---web添加试卷异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询试卷详情
     *
     * @param paperId 试卷Id
     * @return resultInfo
     */
    @RequestMapping(value = "/queryPaperDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryPaperDetail(HttpServletRequest request, Integer paperId) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == paperId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryPaperDetail---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Map<String, Object> map = examManageService.queryPaperDetail(userInfo, paperId);
            if (map != null && !(map.isEmpty())) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(map);
                logger.info("queryPaperDetail--查询试卷详情>>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryPaperDetail查询试卷详情--->>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryPaperDetail---web查询试卷详情异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }


    /**
     * 修改试卷模板
     *
     * @param paperId    试卷Id
     * @param paperTitle 试卷的标题
     * @param repCatId1  试卷一级分类
     * @param repCatId2  试卷二级分类
     * @param radio      单选题 可为空
     * @param multiple   多选题 可为空
     * @param judge      判断题 可为空
     * @param simpleness 简答题  可为空
     * @return resultInfo
     */
    @RequestMapping(value = "/updatePaper", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updatePaper(HttpServletRequest request, Integer paperId, String paperTitle, Integer repCatId1, Integer repCatId2, String radio, String multiple, String judge, String simpleness) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            List<Map<String, Object>> list = new ArrayList<>();
            //单选题
            if (StringUtils.hasText(radio)) {
                if (topicTransfer(radio) != null || !topicTransfer(radio).isEmpty()) {
                    list.add(topicTransfer(radio));
                }
            }
            //多选题
            if (StringUtils.hasText(multiple)) {
                if (topicTransfer(multiple) != null || !topicTransfer(multiple).isEmpty()) {
                    list.add(topicTransfer(multiple));
                }
            }
            //判断题
            if (StringUtils.hasText(judge)) {
                if (topicTransfer(judge) != null || !topicTransfer(judge).isEmpty()) {
                    list.add(topicTransfer(judge));
                }
            }
            //简答题
            if (StringUtils.hasText(simpleness)) {
                if (topicTransfer(simpleness) != null || !topicTransfer(simpleness).isEmpty()) {
                    list.add(topicTransfer(simpleness));
                }
            }
            //检验参数的合法性,包括list
            if (null == userInfo || !StringUtils.hasText(paperTitle) || null == repCatId1 || list == null || list.isEmpty() || null == paperId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updatePaper修改试卷模板---->" + resultInfo.getMsg());
                return resultInfo;
            }

            //参数检验合法,调用server服务,检验返回值的合法性
            Integer result = examManageService.updatePaper(userInfo, paperId, paperTitle, repCatId1, repCatId2, list);
            if (result != null) {
                if (result == -1) {
                    //传入的模块下试题列表参数为空或异常
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
                    logger.info("updatePaper修改试卷模板--->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -2) {
                    //分值不是100!
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.ADDPAPER_COUNT_FAIL_MSG);
                    logger.info("updatePaper修改试卷模板--->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == 1) {
                    //添加成功.
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.UPFATE_SUCCESS_MSG);
                    logger.info("updatePaper修改试卷模板--->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -3) {
                    //标题重复!
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.ADDPAPER_EXAM_REPEAT_FAIL);
                    logger.info("updatePaper修改试卷模板--->" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
            logger.info("updatePaper修改试卷模板--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updatePaper---web-updatePaper异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 删除试卷模板
     *
     * @param paperId 试卷号
     * @return result 1:成功
     */
    @RequestMapping(value = "/deletePaper", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deletePaper(HttpServletRequest request, Integer paperId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,departmentId
            if (null == userInfo || null == paperId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("deletePaper参数不合法---->userInfo==" + userInfo + ",paperId==" + paperId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,对返回值进行校验
            Integer result = examManageService.deletePaper(userInfo, paperId);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_SUCCESS_MSG);
                logger.info("deletePaper删除试卷模板---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,删除部门失败,返回失败信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deletePaper删除试卷模板---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("deletePaper---web删除试卷模板异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询试卷（模板）管理列表
     *
     * @param title      标题
     * @param repCatId1  一级分类
     * @param repCatId2  二级分类
     * @param start1Time 起始时间
     * @param end1Time   截止时间
     * @param pageIndex  当前页
     * @param pageSize   每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryPaperListc", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryPaperList(HttpServletRequest request, String title, Integer repCatId1, Integer repCatId2, String start1Time, String end1Time, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,departmentId
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryPaperList参数不合法---->userInfo==" + userInfo);
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
            //参数校验合法,调用server服务,对返回值进行校验
            List<Map<String, Object>> list = examManageService.queryPaperList(userInfo, title, repCatId1, repCatId2, startTime, endTime, pageIndex, pageSize);
            if (list != null && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("queryPaperList---web查询试卷（模板）管理列表" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryPaperList---web查询试卷（模板）管理列表" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryPaperList---web查询试卷（模板）管理列表!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询试卷（模板）管理列表总条数
     *
     * @param title      标题
     * @param repCatId1  一级分类
     * @param repCatId2  二级分类
     * @param start1Time 起始时间
     * @param end1Time   截止时间
     * @return resultInfo
     */
    @RequestMapping(value = "/queryPaperListCountc", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryPaperListCount(HttpServletRequest request, String title, Integer repCatId1, Integer repCatId2, String start1Time, String end1Time) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryPaperListCount---->" + resultInfo.getMsg());
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
            //传入值检验合法,返回信息
            Integer result = examManageService.queryPaperListCount(userInfo, title, repCatId1, repCatId2, startTime, endTime);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryPaperListCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryPaperListCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryPaperListCount---web查询试卷（模板）管理列表总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 发起考试
     *
     * @param start1Time    开始时间
     * @param end1Time      结束时间
     * @param time          考试时长（分钟）
     * @param examTitle     考试标题
     * @param userIdList1   参试人员
     * @param paperId       模板试卷编号
     * @param readerIdList1 阅卷人员
     * @return resultInfo
     */
    @RequestMapping(value = "/addExam", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addExam(HttpServletRequest request, String start1Time, String end1Time, Integer time, String examTitle, String userIdList1,
                              Integer paperId, String readerIdList1) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);

            //检验传入参数的合法性
            if (null == userInfo || null == time || !StringUtils.hasText(userIdList1) || !StringUtils.hasText(readerIdList1) || null == paperId || !StringUtils.hasText(examTitle) || !StringUtils.hasText(start1Time) || !StringUtils.hasText(end1Time)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addExam发起考试---->" + resultInfo.getMsg());
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
            //,分割
            List<Integer> userIdList = Arrays.asList(userIdList1.split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<Integer> readerIdList = Arrays.asList(readerIdList1.split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());


            //参数检验合法,调用server服务,检验返回值的合法性
            Integer result = examManageService.addExam(userInfo, startTime, endTime, time, examTitle, userIdList, paperId, readerIdList);
            if (result != null) {
                if (result == -1) {
                    //已存在同名部门
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.ADDPAPER_EXAM_REPEAT_FAIL);
                    logger.info("addExam--->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == 1) {
                    //部门添加成功!
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.INIT_EXAM_SUCCESS);
                    logger.info("addExam发起考试--->" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("addDepartment发起考试--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addDepartment---web发起考试异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }


    /**
     * 查询考试管理列表,考试列表
     *
     * @param title      标题
     * @param start1Time 起始时间
     * @param end1Time   截止时间
     * @param assmStatus 评估状态：1.未评估 ；2，已评估
     * @param pageIndex  当前页
     * @param pageSize   每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryExamManageList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryExamManageList(HttpServletRequest request, String title, String start1Time, String end1Time, Integer assmStatus, Integer pageIndex, Integer pageSize) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryPaperList---->" + resultInfo.getMsg());
                return resultInfo;
            }
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            Date startTime = null;
            Date endTime = null;
            if (StringUtils.hasText(start1Time)) {
                startTime = DateTransferUtil.dateTransfer(start1Time);
            }
            if (StringUtils.hasText(end1Time)) {
                endTime = DateTransferUtil.dateTransfer(end1Time);
            }
            //参数校验合法,调用server服务查询部门信息
            List<Map<String, Object>> list = examManageService.queryExamManageList(userInfo, title, startTime, endTime, assmStatus, pageIndex, pageSize);
            if (null != list && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("queryPaperList---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryPaperList---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryPaperList---web查询考试管理列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }


    /**
     * 查询考试管理列表总条数
     *
     * @param title      考试名称,标题
     * @param start1Time 起始时间,string
     * @param end1Time   截止时间,string
     * @param assmStatus 评估状态：1.未评估 ；2，已评估
     * @return resultInfo
     */
    @RequestMapping(value = "/queryExamManageListCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryExamManageListCount(HttpServletRequest request, String title, String start1Time, String end1Time, Integer assmStatus) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryPaperListCount考试列表总条数---->" + resultInfo.getMsg());
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
            //传入值检验合法,返回信息
            Integer result = examManageService.queryExamManageListCount(userInfo, title, startTime, endTime, assmStatus);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryPaperListCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryPaperListCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryPaperListCount---web查询考试管理列表总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }


    /**
     * 考试评估
     *
     * @param examId 考试编号
     * @return resultInfo
     */
    @RequestMapping(value = "/addAssessment", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addAssessment(HttpServletRequest request, Integer examId, String assmContent) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == examId || !StringUtils.hasText(assmContent)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addAssessment---传入参数不合法>" + resultInfo.getMsg());
                return resultInfo;
            }
            //对返回值进行检验
            Integer result = examManageService.addAssessment(userInfo, examId, assmContent);
            if (null != result && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.COMMENT_SUCCESS_MSG);
                logger.info("addAssessment考试评估---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            logger.info("addAssessment考试评估---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addAssessment---web考试评估异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询批卷列表
     *
     * @param examId    考试编号
     * @param pageIndex 当前页
     * @param pageSize  每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryCheckPaperList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryCheckPaperList(HttpServletRequest request, Integer examId, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || null == examId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryCheckPaperList---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //传入值检验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> InfoList = examManageService.queryCheckPaperList(userInfo, examId, pageIndex, pageSize);
            if (null != InfoList && !InfoList.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(InfoList);
                logger.info("queryCheckPaperList-查询批卷列表----->" + resultInfo.getResult());
                return resultInfo;
            }
            //查询失败,返回信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_NULL_MSG);
            logger.info("queryCheckPaperList-查询批卷列表----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryCheckPaperList---web查询批卷列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询批卷列表总条数
     *
     * @param examId 考试编号
     * @return resultInfo
     */
    @RequestMapping(value = "/queryCheckPaperListCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryCheckPaperListCount(HttpServletRequest request, Integer examId) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryCheckPaperListCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //传入值检验合法,返回信息
            Integer result = examManageService.queryCheckPaperListCount(userInfo, examId);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryCheckPaperListCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryCheckPaperListCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryCheckPaperListCount---web查询批卷列表总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 我的考试列表
     *
     * @param title      搜索参数
     * @param start1Time 起始时间
     * @param end1Time   结束时间
     * @param pageIndex  当前页
     * @param pageSize   每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/showMyPaperList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo showMyPaperList(HttpServletRequest request, String title, String start1Time, String end1Time, Integer pageIndex, Integer pageSize) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("showMyPaperList---->" + resultInfo.getMsg());
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
            //对传入的当前页和条数进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //传入值检验合法,返回信息
            List<Map<String, Object>> list = examManageService.showMyPaperList(userInfo, title, startTime, endTime, pageIndex, pageSize);
            if (null != list && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("showMyPaperList-我的考试列表----->" + resultInfo.getResult());
                return resultInfo;
            }
            //查询失败,返回信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("showMyPaperList-我的考试列表----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("showMyPaperList---web我的考试列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 我的考试列表总条数
     *
     * @param title      搜索参数
     * @param start1Time 起始时间
     * @param end1Time   结束时间
     * @return resultInfo
     */
    @RequestMapping(value = "/queryMyPaperListCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryMyPaperListCount(HttpServletRequest request, String title, String start1Time, String end1Time) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryMyPaperListCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //对传入时间进行转化
            Date startTime = null;
            Date endTime = null;
            if (StringUtils.hasText(start1Time)) {
                startTime = DateTransferUtil.dateTransfer(start1Time);
            }
            if (StringUtils.hasText(end1Time)) {
                endTime = DateTransferUtil.dateTransfer(end1Time);
            }
            //传入值检验合法,返回信息
            Integer result = examManageService.queryMyPaperListCount(userInfo, title, startTime, endTime);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryMyPaperListCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryMyPaperListCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryMyPaperListCount---web我的考试列表总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 开始考试---查询试卷实例
     *
     * @param examId 考试Id
     * @return resultInfo
     */
    @RequestMapping(value = "/showPaperInstanceDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo showPaperInstanceDetail(HttpServletRequest request, Integer examId) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == examId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("showPaperInstanceDetail-开始考试---查询试卷----->examId==" + examId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Map<String, Object> map = examManageService.showPaperInstanceDetail(userInfo, examId);
            if (null != map && !map.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(map);
                logger.info("showPaperInstanceDetail开始考试---查询试卷----->" + resultInfo.getResult());
                return resultInfo;
            }
            //借阅记录查询失败!
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("showPaperInstanceDetail-开始考试---查询试卷----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("showPaperInstanceDetail---web开始考试---查询试卷异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 开始考试 --- 提交答案
     *
     * @param examId 模板试卷编号
     * @param list1  字符串拼接答案内容
     * @return resultInfo
     */
    @RequestMapping(value = "/addExamAnswer", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addExamAnswer(HttpServletRequest request, String list1, Integer examId) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数的合法性
            if (null == examId || null == userInfo || !StringUtils.hasText(list1)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addExamAnswer---->" + resultInfo.getMsg());
                return resultInfo;
            }

            //对传入list进行解析
            List<Map<String, Object>> mapList = new ArrayList<>();
            String[] spileString = list1.split("_str_");
            for (int i = 0; i < spileString.length; i++) {
                Map<String, Object> map = new HashMap<>();
                String str = spileString[i];
                List<String> list2 = Arrays.asList(str.split("_"));
                // 题目模块（题目类型）
                map.put("ExerType", list2.get(0));
                //题目编号
                map.put("ExerId", list2.get(1));
                //作答内容，多选用英文逗号","分隔
                map.put("AnsContent", list2.get(2));
                mapList.add(map);
            }
            //
            Map<String, List<Map<String, Object>>> paramMap = new HashMap<>();
            for (Map<String, Object> map : mapList) {
                String key = (String) map.get("ExerType");
                List<Map<String, Object>> valueList = paramMap.get(key);
                if (valueList == null) {
                    valueList = new ArrayList<>();
                }
                valueList.add(map);
                paramMap.put(key, valueList);
            }
            List<Map<String, Object>> answerList = new ArrayList<>();
            if (paramMap.size() > 0) {
                for (Map.Entry<String, List<Map<String, Object>>> entry : paramMap.entrySet()) {
                    Map<String, Object> objectMap = new HashMap<>();
                    objectMap.put("ExerType", entry.getKey());
                    objectMap.put("ExerInfoList", entry.getValue());
                    //objectMap.put(entry.getKey(),entry.getValue());
                    answerList.add(objectMap);
                }
            }

            Integer result = examManageService.addExamAnswer(userInfo, examId, answerList);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.SUBMIT_SUCCESS_MSG);
                logger.info("addExamAnswer---->答案" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.SUBMIT_FAIL_MSG);
            logger.info("addExamAnswer---->答案" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addExamAnswer---web开始考试 --- 提交答案异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 阅卷---展示答案
     *
     * @param examId 考试Id
     * @param userId 考试人Id
     * @return resultInfo
     */
    @RequestMapping(value = "/updateExamAnswer", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateExamAnswer(HttpServletRequest request, Integer examId, Integer userId) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == examId || null == userId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updateExamAnswer-阅卷---展示答案----->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Map<String, Object> map = examManageService.updateExamAnswer(userInfo, examId, userId);
            if (null != map && !map.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(map);
                logger.info("updateExamAnswer阅卷---展示答案----->" + resultInfo.getResult());
                return resultInfo;
            }
            //借阅记录查询失败!
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("updateExamAnswer-阅卷---展示答案----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updateExamAnswer---web阅卷---展示答案异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }


    /**
     * 阅卷---提交
     *
     * @param examId 考试Id
     * @param userId 考试人Id
     * @return resultInfo
     */
    @RequestMapping(value = "/updateUserTotalScore", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateUserTotalScore(HttpServletRequest request, String list, Integer examId, Integer userId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验参数的合法性
            if (null == examId || null == userInfo || null == userId || !StringUtils.hasText(list)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("updateUserTotalScore---->" + resultInfo.getMsg());
                return resultInfo;
            }
            List<Map<String, Object>> scoreList = new ArrayList<>();

            //对传入list进行解析
            String[] spileString = list.split("_");
            for (int i = 0; i < spileString.length; i++) {
                Map<String, Object> map = new HashMap<>();
                String str = spileString[i];
                List<String> list1 = Arrays.asList(str.split(","));
                map.put("ExerId", list1.get(0));
                map.put("ActualScore", list1.get(1));
                scoreList.add(map);
            }
            Integer result = examManageService.updateUserTotalScore(userInfo, examId, userId, scoreList);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.SUBMIT_SUCCESS_MSG);
                logger.info("updateUserTotalScore---->阅卷" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.SUBMIT_FAIL_MSG);
            logger.info("updateUserTotalScore---->阅卷" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updateUserTotalScore---web阅卷异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }


    /**
     * 考试分析
     *
     * @param examId 考试Id
     * @return resultInfo
     */
    @RequestMapping(value = "/queryExamAnalysis", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryExamAnalysis(HttpServletRequest request, Integer examId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == examId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryExamAnalysis-考试分析----->examId==" + examId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Map<String, Object> map = examManageService.queryExamAnalysis(userInfo, examId);
            if (null != map && !map.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(map);
                logger.info("queryExamAnalysis考试分析----->" + resultInfo.getResult());
                return resultInfo;
            }
            //借阅记录查询失败!
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryExamAnalysis-考试分析----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryExamAnalysis---web考试分析异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 问答题作答情况
     *
     * @param examId     考试Id
     * @param exerciseId 题目Id
     * @param realName   模糊搜索真实姓名
     * @param pageIndex  当前页
     * @param pageSize   每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryShortAnswerAnalysis", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryShortAnswerAnalysis(HttpServletRequest request, Integer examId, Integer exerciseId, String realName, Integer pageIndex, Integer pageSize) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == examId || null == exerciseId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryShortAnswerAnalysis-问答题作答情况----->" + resultInfo.getMsg());
                return resultInfo;
            }
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> list = examManageService.queryShortAnswerAnalysis(userInfo, examId, exerciseId, realName, pageIndex, pageSize);
            if (null != list && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("queryShortAnswerAnalysis问答题作答情况----->" + resultInfo.getResult());
                return resultInfo;
            }
            //借阅记录查询失败!
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryShortAnswerAnalysis-问答题作答情况----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryShortAnswerAnalysis---web问答题作答情况异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }


    /**
     * 问答题作答情况总条数
     *
     * @param examId     考试Id
     * @param exerciseId 题目Id
     * @param realName   模糊搜索真实姓名
     * @return resultInfo
     */
    @RequestMapping(value = "/queryShortAnswerAnalysisCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryShortAnswerAnalysisCount(HttpServletRequest request, Integer examId, Integer exerciseId, String realName) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || null == examId || null == exerciseId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryShortAnswerAnalysisCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //传入值检验合法,返回信息
            Integer result = examManageService.queryShortAnswerAnalysisCount(userInfo, examId, exerciseId, realName);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryShortAnswerAnalysisCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryShortAnswerAnalysisCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryShortAnswerAnalysisCount---web总页数查询异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 考试统计 --- 柱状图所用
     *
     * @param examId 考试Id
     * @return resultInfo
     */
    @RequestMapping(value = "/queryExamBarChart", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryExamBarChart(HttpServletRequest request, Integer examId) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == examId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryExamBarChart-考试统计 --- 柱状图----->examId==" + examId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> list = examManageService.queryExamBarChart(userInfo, examId);
            if (list != null && !list.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("queryExamBarChart---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryExamBarChart考试统计 --- 柱状图---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryExamBarChart---web考试统计 --- 柱状图异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 考试统计 --- 分数详情
     *
     * @param examId    考试Id
     * @param pageIndex 当前页
     * @param pageSize  每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryScoreAnalysis", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryScoreAnalysis(HttpServletRequest request, Integer examId, Integer pageIndex, Integer pageSize) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == examId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryScoreAnalysis-考试统计 --- 分数详情----->examId==" + examId + ",userInfo==" + userInfo);
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
            List<Map<String, Object>> result = examManageService.queryScoreAnalysis(userInfo, examId, pageIndex, pageSize);
            if (result != null && !result.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryScoreAnalysis---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryScoreAnalysis考试统计 --- 分数详情--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryScoreAnalysis---web考试统计 --- 分数详情异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }


    /**
     * 考试统计 --- 分数详情总条数
     *
     * @param examId 考试Id
     * @return resultInfo
     */
    @RequestMapping(value = "/queryScoreAnalysisCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryScoreAnalysisCount(HttpServletRequest request, Integer examId) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对输入参数进行检验
            if (null == userInfo || null == examId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryScoreAnalysisCount-考试统计分数详情总条数----->examId==" + examId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,对返回值进行校验
            Integer result = examManageService.queryScoreAnalysisCount(userInfo, examId);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("queryScoreAnalysisCount考试统计分数详情总条数----->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryScoreAnalysisCount-考试统计分数详情总条数----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryScoreAnalysisCount考试统计分数详情总条数异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }


    /**
     * 删除考试
     *
     * @param examId 试卷号
     * @return resultInfo
     */
    @RequestMapping(value = "/deleteExam", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteExam(HttpServletRequest request, Integer examId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对输入参数进行检验
            if (null == userInfo || null == examId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("deleteExam-删除考试----->examId==" + examId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,对返回值进行校验
            Integer result = examManageService.deleteExam(userInfo, examId);
            if (null != result && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_SUCCESS_MSG);
                logger.info("deleteExam删除考试----->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deleteExam-删除考试----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("deleteExam删除考试!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    //添加/编辑试卷拼接参数所用
    public static Map<String, Object> topicTransfer(String string) {

        Map<String, Object> map = new HashMap<>();
        if (StringUtils.hasText(string)) {
            //map = new HashMap<>();
            String[] strs = string.split(",");
            //题目的类型
            map.put("ExerType", Integer.valueOf(strs[0]));
            //题目的分值
            map.put("ExerTypeScore", Integer.valueOf(strs[1]));
            //该类型下题号列表
            List<Map<String, Object>> ExerList = new ArrayList<>();

            //遍历String数组
            for (int i = 2; i < strs.length; i++) {
                Map<String, Object> map1 = new HashMap<>();
                map1.put("ExerId", Integer.valueOf(strs[i]));
                map1.put("Sort", i - 1);
                ExerList.add(map1);
            }
            map.put("ExerInfoList", ExerList);
        }
        return map;
    }


    //考试答案
    public static Map<String, Object> answerTransfer(String string) {

        Map<String, Object> map = new HashMap<>();
        if (StringUtils.hasText(string)) {
            //map = new HashMap<>();
            String[] strs = string.split(",");
            //题目类型
            map.put("ExerType", Integer.valueOf(strs[0]));
            //该类型下回复答案列表
            List<Map<String, Object>> ExerList = new ArrayList<>();

            //遍历String数组
            for (int i = 1; i < strs.length; i++) {
                Map<String, Object> map1 = new HashMap<>();
                map1.put("ExerId", Integer.valueOf(strs[i]));
                map1.put("AnsContent", i - 1);
                ExerList.add(map1);
            }
            map.put("ExerInfoList", ExerList);
        }
        return map;
    }

}
