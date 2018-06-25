package com.mascot.controller;

import com.interfaces.mascot.EssayTypeService;
import com.mascot.bean.ResultInfo;
import com.mascot.constant.UdcConstant;
import com.mascot.utils.SessionGetUtil;
import com.thrift.common.body.EssayType;
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
 * 社区贴文分类管理接口
 * <p>
 * Created by jichao on 2018/05/11
 */
@Controller
@RequestMapping(value = "/essayType")
public class EssayTypeController {

    @Autowired
    private EssayTypeService essayTypeService;

    private static final Log logger = LogFactory.getLog(EssayTypeController.class);


    /**
     * 获取贴文分类目录列表
     *
     * @param parentId 父类id --- 可为空
     * @return resultInfo
     */
    @RequestMapping(value = "/getEssayTypeList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getEssayTypeList(HttpServletRequest request, Integer parentId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getEssayTypeList--->传入参数不合法,userInfo==" + userInfo);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            List<EssayType> result = essayTypeService.getEssayTypeList(userInfo, parentId);
            if (null != result && !(result.isEmpty())) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("getEssayTypeList---获取贴文分类目录列表>" + resultInfo.getMsg());
                return resultInfo;
            }
            //获取列表信息失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_NULL_MSG);
            logger.info("getEssayTypeList---获取贴文分类目录列表>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getEssayTypeList---web获取贴文分类目录列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 新增分类目录
     *
     * @param parentId      父类id --- 可为空
     * @param essayTypeName 目录名称
     * @return resultInfo
     */
    @RequestMapping(value = "/addEssayType", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addEssayType(HttpServletRequest request, Integer parentId, String essayTypeName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对传入参数进行检验
            if (null == userInfo || !StringUtils.hasText(essayTypeName)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("addEssayType--->传入参数不合法,userInfo==" + userInfo + ",essayTypeName==" + essayTypeName);
                return resultInfo;
            }
            //参数校验合法,调用server服务,验证返回值的合法性
            Integer result = essayTypeService.addEssayType(userInfo, parentId, essayTypeName);
            if (result != null) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.INSERT_SUCCESS_MSG);
                    logger.info("addEssayType----新增分类目录>" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -1) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.CLASSIFY_EXIST_MSG);
                    logger.info("addEssayType----新增分类目录>" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            //新增失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("addEssayType----新增分类目录>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("addEssayType---web新增分类目录异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 获取贴文分类详情
     *
     * @param essayTypeId 分类编号
     * @return resultInfo
     */
    @RequestMapping(value = "/getEssayTypeDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getEssayTypeDetail(HttpServletRequest request, Integer essayTypeId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验bookId,bookName,author,userInfo参数的合法性
            if (null == userInfo || null == essayTypeId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getEssayTypeDetail-参数校验不正确----->essayTypeId==" + essayTypeId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数校验合法,调用server服务,校验返回值的合法性
            EssayType essayType = essayTypeService.getEssayTypeDetail(userInfo, essayTypeId);
            if (essayType != null) {
                List<EssayType> list = new ArrayList<>();
                list.add(essayType);
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(list);
                logger.info("getEssayTypeDetail---web获取贴文分类详情" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getEssayTypeDetail---web获取贴文分类详情" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getEssayTypeDetail---web获取贴文分类详情异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 编辑修改贴文分类
     *
     * @param essayTypeId   分类编号
     * @param essayTypeName 分类名称
     * @return resultInfo
     */
    @RequestMapping(value = "/updateEssayType", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateEssayType(HttpServletRequest request, Integer essayTypeId, String essayTypeName) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数参数的合法性
            if (null == userInfo || null == essayTypeId || !StringUtils.hasText(essayTypeName)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("updateEssayType-参数校验不正确--->userInfo==" + userInfo + ",essayTypeId==" + essayTypeId + ",essayTypeName==" + essayTypeName);
                return resultInfo;
            }
            //参数校验合法,调用server服务,校验返回值的合法性
            Integer result = essayTypeService.updateEssayType(userInfo, essayTypeId, essayTypeName);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.UPFATE_SUCCESS_MSG);
                logger.info("updateEssayType---web编辑修改贴文分类详情->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
            logger.info("updateEssayType---web编辑修改贴文分类详情->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("updateEssayType---web编辑修改贴文分类异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 删除贴文分类
     *
     * @param essayTypeId 分类编号
     * @return resultInfo
     */
    @RequestMapping(value = "/deleteEssayType", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteEssayType(HttpServletRequest request, Integer essayTypeId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数参数的合法性
            if (null == userInfo || null == essayTypeId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getQuestionTypeDetail-参数校验不正确--->userInfo==" + userInfo + ",essayTypeId==" + essayTypeId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,校验返回值的合法性
            Integer result = essayTypeService.deleteEssayType(userInfo, essayTypeId);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_SUCCESS_MSG);
                logger.info("updateEssayType---web删除贴文分类->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("updateEssayType---web删除贴文分类->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getQuestionTypeDetail---web删除贴文分类!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 根据上级目录获取所有下级目录
     *
     * @param parentId 一级分类编号
     * @return resultInfo
     */
    @RequestMapping(value = "/getEssayTypeListByParentId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getEssayTypeListByParentId(HttpServletRequest request, Integer parentId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验传入参数参数的合法性
            if (null == userInfo || null == parentId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getEssayTypeListByParentId-参数校验不正确--->userInfo==" + userInfo + ",parentId==" + parentId);
                return resultInfo;
            }
            //参数校验合法,调用server服务,校验返回值的合法性
            List<EssayType> result = essayTypeService.getEssayTypeListByParentId(userInfo, parentId);
            if (null != result && !result.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("getEssayTypeListByParentId---web根据上级目录获取所有下级目录-->" + resultInfo.getMsg());
                return resultInfo;
            }
            //获取目录列表失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getEssayTypeListByParentId---web根据上级目录获取所有下级目录-->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getEssayTypeListByParentId---web根据上级目录获取所有下级目录异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


}