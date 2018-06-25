package com.mascot.controller;


import com.interfaces.mascot.TrainManageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 培训模块
 *
 * @author jichao
 * 2018/6/7
 */

@Controller
@RequestMapping(value = "/trainModule")
public class TrainModuleController {


   // private TrainManageService trainManageService;

    private static final Log logger = LogFactory.getLog(TrainModuleController.class);


    /**
     * 发起培训
     *
     * @param trainType    培训类型，1线上2线下
     * @param trainFlag    培训类型，1内训2外训
     * @param title        训练标题
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param trainSite    培训地点
     * @param lecturerName 讲师姓名
     * @param lecHeadUrl   讲师头像url
     * @param lecProfile   讲师简介
     * @param category1    考试培训分类一级目录id
     * @param category2    考试培训分类二级目录id
     * @param trainContent 培训内容
     * @param fileList     文件列表 参数1：FileUrl，参数2：FileType
     * @param userIdList   参与人列表
     * @return resultInfo
     */


}
