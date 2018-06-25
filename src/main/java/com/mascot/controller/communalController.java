package com.mascot.controller;


import com.interfaces.file.ResponseInfo;
import com.interfaces.mascot.BbsManageService;
import com.interfaces.mascot.UserManageService;
import com.mascot.bean.ResultInfo;
import com.mascot.bean.SessionInfo;
import com.mascot.constant.UdcConstant;
import com.mascot.utils.*;
import com.thrift.common.body.UserInfo;
import com.thrift.common.define.AppType;
import com.thrift.common.define.DisType;
import com.thrift.common.define.ResponseCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 公共接口
 *
 * @author jichao
 * 2018/5/20
 */
@Controller
@RequestMapping(value = "/communal")
public class communalController {

    @Autowired
    private UserManageService userManageService;

    @Autowired
    private BbsManageService bbsManageService;

    private static final Log logger = LogFactory.getLog(communalController.class);

    /**
     * 左侧头像编辑接口
     *
     * @param fileUrl      头像url
     * @param realName     用户名
     * @param departmentId 部门id
     */
    @RequestMapping(value = "/saveHeadChange", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo saveHeadChange(HttpServletRequest request, String fileUrl, String realName, Integer departmentId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("onlineInfo");
            UserInfo userInfo = null;
            if (sessionInfo != null) {
                userInfo = SessionGetUtil.getSession(request);
            }
            //检验传入值的合法性
            if (!StringUtils.hasText(fileUrl) || !StringUtils.hasText(realName) || departmentId == null) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("saveHeadChange--编辑个人信息--->>" + resultInfo.getMsg());
                return resultInfo;
            }
            //调用server服务,检验返回值的合法性
            Integer result = userManageService.updateUserInfo1(userInfo, userInfo.getUserId(), fileUrl, realName, departmentId);
            if (result != null && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.UPFATE_SUCCESS_MSG);
                //传回前台的头像url
                resultInfo.setResult(Constant.FILE_UPLOAD_URL_PATH + fileUrl);
                logger.info("saveHeadChange----左侧头像编辑>" + resultInfo.getMsg());
                return resultInfo;
            }
            //个人信息编辑失败,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.UPFATE_FAIL_MSG);
            logger.info("saveSpecialistInfo----左侧头像编辑>" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("saveHeadChange---web左侧头像编辑异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 左侧信息获取,头像,部门名,用户名接口
     */
    @RequestMapping(value = "/initUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo initUserInfo(HttpServletRequest request) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            if (userInfo != null) {
                Integer userId = userInfo.getUserId();
                Map<String, Object> map = userManageService.getUserInfoDetailByUserId(userInfo, userId);
                if (map != null && !(map.isEmpty())) {
                    Map<String, String> map1 = new HashMap<>();
                    String departmentName = (String) map.get("DepartmentName");
                    String userName = (String) map.get("RealName");
                    String avatar = (String) map.get("Avatar");
                    String departmentId = String.valueOf(map.get("DepartmentId"));
                    map1.put("departmentName", departmentName);
                    //初始化时候显示的应该是realName，标记此处
                    map1.put("userName", userName);
                    map1.put("avatar", avatar);
                    map1.put("departmentId", departmentId);
                    List list = new ArrayList();
                    list.add(map1);
                    resultInfo.setResult(list);
                    logger.info("initUserInfo-------->>>初始化获取展示信息成功.");
                    return resultInfo;
                }
            }
            //返回失败信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("initUserInfo-------->>>初始化获取展示信息查询失败.");
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("initUserInfo---web获取用户展示信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 左侧分享提问积分值
     */
    @RequestMapping(value = "/getShareQusCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getShareQusCount(HttpServletRequest request) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("getShareQusCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //传入值检验合法,返回信息
            Map<String, Object> map = new HashMap<>();
            map.put("myPost", 0);
            map.put("myQuestion", 0);
            map.put("myIntegral", 0);
            //我的分享数
            if (null != bbsManageService.queryMyDiscussionCount(userInfo, DisType.Essay.getValue()) && bbsManageService.queryMyDiscussionCount(userInfo, DisType.Essay.getValue()) > 0) {
                map.put("myPost", bbsManageService.queryMyDiscussionCount(userInfo, DisType.Essay.getValue()));
            }
            //我的提问数
            if (null != bbsManageService.queryMyDiscussionCount(userInfo, DisType.Question.getValue()) && bbsManageService.queryMyDiscussionCount(userInfo, DisType.Question.getValue()) > 0) {
                map.put("myQuestion", bbsManageService.queryMyDiscussionCount(userInfo, DisType.Question.getValue()));
            }
            //我的积分值
            Map<String, Object> map1 = userManageService.getUserInfoDetailByUserId(userInfo, userInfo.getUserId());
            if (map1 != null && !map1.isEmpty()) {
                map.put("myIntegral", map1.get("UserScore"));
            }
            resultInfo.setCode(ResponseCode.Succeed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
            resultInfo.setResult(map);
            logger.info("getShareQusCount---->" + resultInfo.getMsg());
            return resultInfo;

        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            logger.debug("getShareQusCount---web左侧分享提问积分值异常!");
            return resultInfo;
        }
    }


    /**
     * 图片单独上传,测试
     *
     * @param file 头像上传
     * @return resultInfo
     */
    @RequestMapping(value = "/saveHeadImage", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo saveHeadImage(HttpServletRequest request, MultipartFile file) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("onlineInfo");
            //检验传入参数的合法性
            if (null == file || null == sessionInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("saveHeadImage---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法，调用server服务，检验返回值的合法性
            Map<String, Object> params = new HashMap<>();
            params.put("appType", AppType.AppType_UDC_SYN.getValue());
            params.put("tokenId", sessionInfo.getTokenId());
            ResponseInfo responseInfo = FileUploadUtils.uploadFile(Constant.FILE_UPLOAD_URL_PATH, file.getInputStream(), params, file.getOriginalFilename());
            if (responseInfo.getResponseCode() == 1) {
                //文件上传成功,返回url
                String fileUrl = responseInfo.getObject().get("fileUrl");
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.FILE_UPLOAD_SUCCESS);
                //图片预览路径
                resultInfo.setResult(Constant.FILE_SYSTEM_DOWNLOAD + fileUrl + "/mic");
                logger.info("querySpeConsultDetail图片上传" + resultInfo.getMsg());
                return resultInfo;
            } else {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.FILE_UPLOAD_FAIL);
                logger.info("querySpeConsultDetail图片上传" + resultInfo.getMsg());
                return resultInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("querySpeConsultDetail---web图片单独上传异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 富文本编辑器图片上传接口
     *
     * @return resultInfo
     */
    @RequestMapping(value = "/richTextImageSave", method = RequestMethod.POST)
    @ResponseBody
    public RichTextImageutil richTextImageSave(HttpServletRequest request) {
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("onlineInfo");
        MultipartRequest multipartRequest = (MultipartRequest) request;
        try {
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            MultipartFile file = null;
            Iterator<Map.Entry<String, MultipartFile>> entries = fileMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, MultipartFile> entry = entries.next();
                file = entry.getValue();
            }
            //参数校验合法，调用server服务，检验返回值的合法性
            Map<String, Object> params = new HashMap<>();
            params.put("appType", AppType.AppType_UDC_SYN.getValue());
            params.put("tokenId", sessionInfo.getTokenId());
            ResponseInfo responseInfo = FileUploadUtils.uploadFile(Constant.FILE_UPLOAD_URL_PATH, file.getInputStream(), params, file.getOriginalFilename());
            if (responseInfo.getResponseCode() == 1) {
                RichTextImageutil richTextImageutil = new RichTextImageutil();
                richTextImageutil.setErrno(0);
                String string = responseInfo.getObject().get("fileUrl");
                String string1 = Constant.FILE_SYSTEM_DOWNLOAD + string + "/mic";
                String[] str = {string1};
                richTextImageutil.setData(str);
                logger.info("richTextImageSave富文本编辑器图片上传成功!");
                return richTextImageutil;
            } else {
                logger.info("richTextImageSave富文本编辑器图片上传上传失败!");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("richTextImageSave---web富文本编辑器图片上传异常!");
            return null;
        }
    }

    /**
     * 附件单独上传,测试
     *
     * @param file 头像上传
     * @return resultInfo
     */
    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo saveFile(HttpServletRequest request, MultipartFile file) {

        ResultInfo resultInfo = new ResultInfo();
        try {
            SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("onlineInfo");
            //检验传入参数的合法性
            if (null == file || null == sessionInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("saveFile---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //参数校验合法，调用server服务，检验返回值的合法性
            Map<String, Object> params = new HashMap<>();
            params.put("appType", AppType.AppType_UDC_SYN.getValue());
            params.put("tokenId", sessionInfo.getTokenId());
            ResponseInfo responseInfo = FileUploadUtils.uploadFile(Constant.FILE_UPLOAD_URL_PATH, file.getInputStream(), params, file.getOriginalFilename());
            if (responseInfo.getResponseCode() == 1) {
                Map<String, Object> map = new HashMap<>();
                //上传成功,返回url
                String fileUrl1 = responseInfo.getObject().get("fileUrl");
                String fileUrl = Constant.FILE_SYSTEM_DOWNLOAD + fileUrl1;
                map.put("FileUrl", fileUrl);
                //获取上传文件的全名
                String name = file.getOriginalFilename();
                //获取上传文件的后缀名.extName
                int one = name.lastIndexOf(".");
                String extName = name.substring((one + 1), name.length());
                //调用工具类根据文件后缀名识别文件类型
                Integer fileType = IsKindOfUtil.getFileKind(extName);
                //对上传的类型进行判别
                if (fileType == null) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.FILE_NOT_SUPPORT);
                    logger.info("saveFile附件上传---->" + resultInfo.getMsg());
                    return resultInfo;
                }
                map.put("FileType", fileType);
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.FILE_UPLOAD_SUCCESS);
                //文件下载的url
                resultInfo.setResult(map);
                logger.info("saveFile附件上传" + resultInfo.getMsg());
                return resultInfo;
            } else {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.FILE_UPLOAD_FAIL);
                logger.info("saveFile附件上传" + resultInfo.getMsg());
                return resultInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("saveFile---web附件单独上传异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


}
