package com.interfaces.mascot;

import com.thrift.common.body.UserInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 知识管理接口
 *
 * @author zhangmengyu
 * 2018/5/16
 */
public interface TrainManageService extends BasicService {

    /**
     * 发起培训
     *
     * @param userInfo     调用接口用户信息
     * @param trainType    培训类型，1线上2线下
     * @param trainFlag    培训类型，1内训2外训
     * @param title        训练标题
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param trainSite    培训地点
     * @param lecturerName 讲师姓名
     * @param lecHeadUrl   讲师头像
     * @param lecProfile   讲师简介
     * @param category1    考试培训分类一级目录
     * @param category2    考试培训分类二级目录
     * @param trainContent 培训内容
     * @param fileList     文件列表 参数1：FileUrl，参数2：FileType
     * @param userIdList   参与人列表
     * @return 1：成功，其他失败
     */
    Integer addTrain(UserInfo userInfo, Integer trainType, Integer trainFlag, String title, Date startTime, Date endTime,
                     String trainSite, String lecturerName, String lecHeadUrl, String lecProfile, Integer category1, Integer category2,
                     String trainContent, List<Map<String, Object>> fileList, List<Integer> userIdList);

    /**
     * 培训审核消息详情
     *
     * @param userInfo  调用接口用户信息
     * @param messageId 消息Id
     * @return 1：成功，其他失败
     */
    Map<String,Object> queryTrainCheckMessageDetail(UserInfo userInfo, Integer messageId);

    /**
     * 培训审核
     *
     * @param userInfo 调用接口用户信息
     * @param trainId  培训Id
     * @param optionId 操作Id 2：通过；4驳回
     * @return 1：成功，其他失败
     */
    Integer updateTrainStatus(UserInfo userInfo, Integer trainId, Integer optionId);


}
