package com.mascot.controller;

import com.interfaces.mascot.RepositoryCategoryService;
import com.mascot.bean.ResultInfo;
import com.mascot.constant.UdcConstant;
import com.mascot.utils.SessionGetUtil;
import com.thrift.common.body.RepositoryCategory;
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
 * 分类管理-知识仓库文章类型
 *
 * @author jichao
 * 2018/5/04
 */
@Controller
@RequestMapping("/repositoryClassify")
public class RepositoryClassifyController {

    @Autowired
    private RepositoryCategoryService repositoryCategoryService;

    private static final Log logger = LogFactory.getLog(RepositoryClassifyController.class);

    /**
     * 获取知识分类目录列表
     *
     * @param repCatParentId 知识仓库分类的父类id
     * @return resultInfo
     */
    @RequestMapping(value = "/getRepositoryCategoryList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getRepositoryCategoryList(HttpServletRequest request, Integer repCatParentId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo || null == repCatParentId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getRepositoryCategoryList---->userInfo==" + userInfo + ",repCatParentId==" + repCatParentId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            List<RepositoryCategory> list = repositoryCategoryService.getRepositoryCategoryList(userInfo, repCatParentId);
            if (null != list && !list.isEmpty()) {
                List list1 = new ArrayList();
                for (RepositoryCategory repositoryCategory : list) {
                    String str = repositoryCategoryService.getNextRepositoryCategoryByParentId(userInfo, repositoryCategory.getRepCatId());
                    if (StringUtils.hasText(str)) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("RepositoryCategory", repositoryCategory);
                        map.put("IsHaveChild", 1);
                        list1.add(map);
                    } else {
                        Map<String, Object> map = new HashMap<>();
                        map.put("RepositoryCategory", repositoryCategory);
                        map.put("IsHaveChild", 2);
                        list1.add(map);
                    }
                }
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list1);
                logger.info("getRepositoryCategoryList---->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getRepositoryCategoryList---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getRepositoryCategoryList---web获取知识分类目录异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 新增分类目录
     *
     * @param repCatParentId 知识仓库分类的父类id
     * @param repCatName     目录名称
     * @return result -1:分类存在,resultInfo
     */
    @RequestMapping(value = "/addRepositoryCategory1", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addRepositoryCategory1(HttpServletRequest request, Integer repCatParentId, String repCatName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对传入参数进行检验
            if (null == userInfo || !StringUtils.hasText(repCatName)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addRepositoryCategory1------>repCatName==" + repCatName + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Integer result = repositoryCategoryService.addRepositoryCategory1(userInfo, repCatParentId, repCatName);
            if (null != result && result == 1) {
                //增加成功,返回增加成功信息.
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.INSERT_SUCCESS_MSG);
                logger.info("addRepositoryCategory1--->" + resultInfo.getMsg());
                return resultInfo;
            } else if (result == -1) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.CLASSIFY_EXIST_MSG);
                logger.info("addRepositoryCategory1--->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,返回失败信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("addRepositoryCategory1--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addRepositoryCategory1---web新增目录分类异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 编辑修改知识分类
     *
     * @param repCatId   分类编号
     * @param repCatName 分类名称
     * @return resultInfo
     */
    @RequestMapping(value = "/updateRepositoryCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateRepositoryCategory(HttpServletRequest request, Integer repCatId, String repCatName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对传入参数进行检验
            if (null == userInfo || null == repCatId || !StringUtils.hasText(repCatName)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updateRepositoryCategory参数异常--->repCatId==" + repCatId + ",userInfo==" + userInfo + ",repCatName==" + repCatName);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Integer result = repositoryCategoryService.updateRepositoryCategory(userInfo, repCatId, repCatName);
            if (null != result && result == 1) {
                //编辑成功,返回编辑成功信息.
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.UPFATE_SUCCESS_MSG);
                logger.info("updateRepositoryCategory----->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,返回编辑失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
            logger.info("updateRepositoryCategory----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updateRepositoryCategory--->web编辑修改知识异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 删除知识分类
     *
     * @param repCatId 分类编号
     * @return resultInfo
     */
    @RequestMapping(value = "/deleteRepositoryCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteRepositoryCategory(HttpServletRequest request, Integer repCatId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对传入的参数进行检验
            if (null == userInfo || null == repCatId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("deleteRepositoryCategory----->repCatId==" + repCatId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数校验合法,调用server服务,验证返回值的合法性
            Integer result = repositoryCategoryService.deleteRepositoryCategory(userInfo, repCatId);
            if (null != result && result == 1) {
                //删除成功,返回删除成功信息.
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_SUCCESS_MSG);
                logger.info("deleteRepositoryCategory----->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回值不合法,返回失败信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deleteRepositoryCategory----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("deleteRepositoryCategory---web删除知识分类异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 获取知识分类详情
     *
     * @param repCatId 分类编号
     * @return resultInfo
     */
    @RequestMapping(value = "/getRepositoryCategoryDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getRepositoryCategoryDetail(HttpServletRequest request, Integer repCatId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == repCatId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getRepositoryCategoryDetail参数异常----->repCatId==" + repCatId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server层服务,检验返回值的合法性
            RepositoryCategory repositoryCategory = repositoryCategoryService.getRepositoryCategoryDetail(userInfo, repCatId);
            if (repositoryCategory != null) {
                List<RepositoryCategory> result = new ArrayList<>();
                result.add(repositoryCategory);
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("getRepositoryCategoryDetail获取知识分类详情--->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            //返回值检验不合法,返回查询失败信息.
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getRepositoryCategoryDetail获取知识分类详情--->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getRepositoryCategoryDetail---web获取知识分类详情异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 根据上级目录获取所有下级目录
     *
     * @param repCatId 一级分类编号
     * @return resultInfo
     */
    @RequestMapping(value = "/getRepository", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getExCategoryListByParentId(HttpServletRequest request, Integer repCatId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (userInfo == null || repCatId == null) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getRepository---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            String result = repositoryCategoryService.getNextRepositoryCategoryByParentId(userInfo, repCatId);
            if (StringUtils.hasText(result)) {
                //传回二级分类Id字符串
                String[] list = result.split(",");
                List<Map<String, Object>> list1 = new ArrayList<>();
                List<Integer> list2 = new ArrayList<>();
                for (String str : list) {
                    list2.add(Integer.valueOf(str));
                }
                for (Integer inte : list2) {
                    RepositoryCategory repositoryCategory = repositoryCategoryService.getRepositoryCategoryDetail(userInfo, inte);
                    if (repositoryCategory != null) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("repCatId", inte);
                        map.put("repCatName", repositoryCategory.getRepCatName());
                        list1.add(map);
                    }
                }
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list1);
                logger.info("getRepository===>" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getRepository===>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getRepository---web根据上级目录获取所有下级目录异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


}