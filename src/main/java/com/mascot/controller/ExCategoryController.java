package com.mascot.controller;

import com.interfaces.mascot.ExCategoryService;
import com.mascot.bean.ResultInfo;
import com.mascot.constant.UdcConstant;
import com.mascot.utils.SessionGetUtil;
import com.thrift.common.body.ExCategory;
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
 * 考试培训分类管理
 *
 * @author jichao
 * 2018/5/22
 */
@Controller
@RequestMapping(value = "/exCategory")
public class ExCategoryController {

    @Autowired
    private ExCategoryService exCategoryService;

    private static final Log logger = LogFactory.getLog(ExCategoryController.class);

    /**
     * 获取考试培训分类目录列表
     *
     * @param parentId 考试培训分类的父类id
     * @return resultInfo
     */
    @RequestMapping(value = "/getExCategoryList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getExCategoryList(HttpServletRequest request, Integer parentId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || null == parentId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getExCategoryList参数异常---->userInfo==" + userInfo + ",parentId==" + parentId);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            List<ExCategory> exCategoryList = exCategoryService.getExCategoryList(userInfo, parentId);
            if (null != exCategoryList && !exCategoryList.isEmpty()) {
                //返回值检验合法,返回查询结果
                List list1 = new ArrayList();
                for (ExCategory exCategory : exCategoryList) {
                    String str = exCategoryService.getNextExCategoryByParentId(userInfo, exCategory.getCategoryId());
                    if (StringUtils.hasText(str)) {
                        //1,代表有子模块
                        Map<String, Object> map = new HashMap<>();
                        map.put("ExCategory", exCategory);
                        map.put("IsHaveChild", 1);
                        list1.add(map);
                    } else {
                        //2,暂无子模块
                        Map<String, Object> map = new HashMap<>();
                        map.put("ExCategory", exCategory);
                        map.put("IsHaveChild", 2);
                        list1.add(map);
                    }
                }
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list1);
                logger.info("getExCategoryList--->>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值校验失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getExCategoryList--->>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getExCategoryList---web获取考试培训分类目录列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 新增分类目录
     *
     * @param parentId     考试培训分类的父类id
     * @param categoryName 目录名称
     * @return resultInfo
     */
    @RequestMapping(value = "/addExCategory1", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addExCategory1(HttpServletRequest request, Integer parentId, String categoryName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || !StringUtils.hasText(categoryName)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addExCategory1---->userInfo==" + userInfo + ",categoryName==" + categoryName);
                return resultInfo;
            }
            Integer result = exCategoryService.addExCategory1(userInfo, parentId, categoryName);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.INSERT_SUCCESS_MSG);
                logger.info("addExCategory1新增分类目录---->" + resultInfo.getMsg());
                return resultInfo;
            } else if (result == -1) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.CLASSIFY_EXIST_MSG);
                logger.info("addExCategory1新增分类目录---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,,添加失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("addExCategory1新增分类目录---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addExCategory1---web新增分类目录异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 编辑修改考试培训分类
     *
     * @param categoryId   分类编号
     * @param categoryName 分类名称
     * @return resultInfo
     */
    @RequestMapping(value = "/updateExCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateExCategory(HttpServletRequest request, Integer categoryId, String categoryName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (userInfo == null || categoryId == null || !StringUtils.hasText(categoryName)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updateExCategory---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer result = exCategoryService.updateExCategory(userInfo, categoryId, categoryName);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.UPFATE_SUCCESS_MSG);
                logger.info("updateExCategory编辑修改考试培训分类=====>" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
            logger.info("updateExCategory编辑修改考试培训分类=====>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.info("updateExCategory---web编辑修改考试培训分类异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 删除考试培训分类
     *
     * @param categoryId 分类编号
     * @return resultInfo
     */
    @RequestMapping(value = "/deleteExCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteExCategory(HttpServletRequest request, Integer categoryId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (userInfo == null || categoryId == null) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("deleteExCategory---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            Integer result = exCategoryService.deleteExCategory(userInfo, categoryId);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_SUCCESS_MSG);
                logger.info("deleteExCategory删除考试培训分类---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值校验不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deleteExCategory删除考试培训分类---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("deleteExCategory---web删除考试培训分类异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 根据上级目录获取所有下级目录
     *
     * @param categoryId 一级分类编号
     * @return resultInfo
     */
    @RequestMapping(value = "/getExCategoryListByParentId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getExCategoryListByParentId(HttpServletRequest request, Integer categoryId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (userInfo == null || categoryId == null) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getExCategoryListByParentId---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            String result = exCategoryService.getNextExCategoryByParentId(userInfo, categoryId);
            if (StringUtils.hasText(result)) {
                //传回二级分类Id字符串
                String[] list = result.split(",");
                List<Map<String, Object>> list1 = new ArrayList<>();
                List<Integer> list2 = new ArrayList<>();
                for (String str : list) {
                    list2.add(Integer.valueOf(str));
                }
                for (Integer inte : list2) {
                    ExCategory exCategory = exCategoryService.getExCategoryDetail(userInfo, inte);
                    if (exCategory != null) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("categoryId", inte);
                        map.put("categoryName", exCategory.getCategoryName());
                        list1.add(map);
                    }
                }
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list1);
                logger.info("getExCategoryListByParentId===>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getExCategoryListByParentId===>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getExCategoryListByParentId---web根据上级目录获取所有下级目录异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 获取考试培训分类详情
     *
     * @param categoryId 分类编号
     * @return resultInfo
     */
    @RequestMapping(value = "/getExCategoryDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getExCategoryDetail(HttpServletRequest request, Integer categoryId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo,departmentId
            if (null == userInfo || null == categoryId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getExCategoryDetail参数不合法---->userInfo==" + userInfo + ",categoryId==" + categoryId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,对返回值进行校验
            ExCategory exCategory = exCategoryService.getExCategoryDetail(userInfo, categoryId);
            if (exCategory != null) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(exCategory);
                logger.info("getExCategoryDetail===>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getExCategoryDetail===>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getExCategoryDetail---web删除部门异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


}