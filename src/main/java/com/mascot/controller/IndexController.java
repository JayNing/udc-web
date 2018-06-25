package com.mascot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 跳转页面
 */

@Controller
public class IndexController {

    @RequestMapping(value = "/loginSkip", method = RequestMethod.GET)
    public String loginSkip() {
        return "member/memberCenter.ftl";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String demo5Front() {
        return "account/login.ftl";
    }

    //专家模块,需传id到编辑页面
    @RequestMapping(value = "/specEdit", method = RequestMethod.GET)
    public String specEdit() {
        return "specialist/specManagerAdd.ftl";
    }

    @RequestMapping(value = "/specAdd", method = RequestMethod.GET)
    public String specAdd() {
        return "specialist/specManagerAdd.ftl";
    }

    //线上跳线下
    @RequestMapping(value = "/beLine", method = RequestMethod.GET)
    public String beLine() {
        return "exam/trainMangBelline.ftl";
    }

    //跳转知识仓库文章类型页面
    @RequestMapping(value = "/knlgArticle", method = RequestMethod.GET)
    public String knlgArticle() {
        return "classify/knlwarehouseClassify.ftl";
    }

    //跳转部门分类页面
    @RequestMapping(value = "/departClassify", method = RequestMethod.GET)
    public String departClassify() {
        return "classify/departmentClassify.ftl";
    }

    //跳转考试分类页面
    @RequestMapping(value = "/examClassify", method = RequestMethod.GET)
    public String examClassify() {
        return "classify/examTrainClassify.ftl";
    }

    //跳转考试分类页面
    @RequestMapping(value = "/knlgNode", method = RequestMethod.GET)
    public String knlgNode() {
        return "classify/knlhouseNode.ftl";
    }

    //跳转社区文章分类页面
    @RequestMapping(value = "/communityArit", method = RequestMethod.GET)
    public String communityArit() {
        return "classify/commArticleClassity.ftl";
    }

    //线下跳线上
    @RequestMapping(value = "/onLine", method = RequestMethod.GET)
    public String onLine() {
        return "exam/trainMangOnline.ftl";
    }

    //点击知识库管理平台img跳转回主页
    @RequestMapping(value = "/backIndex", method = RequestMethod.GET)
    public String backIndex() {
        return "member/memberCenter.ftl";
    }



    //智能搜索结果页面
    @RequestMapping(value = "/intelligentSearchResult", method = RequestMethod.GET)
    public String intelligentSearchResult() {
        return "knlwarehouse/intelligentSearchResult.ftl";
    }

    //分类检索页面
    @RequestMapping(value = "/knseClassify", method = RequestMethod.GET)
    public String knseClassifyText() {
        return "knlwarehouse/knseClassify.ftl";
    }


    //通知列表
    @RequestMapping(value = "/informList", method = RequestMethod.GET)
    public String informList() {
        return "member/informList.ftl";
    }

    //收藏列表
    @RequestMapping(value = "/collectList", method = RequestMethod.GET)
    public String collectList() {
        return "member/collectlist.ftl";
    }

    //关注列表
    @RequestMapping(value = "/followList", method = RequestMethod.GET)
    public String followList() {
        return "member/followList.ftl";
    }

    //知识仓库编辑
    @RequestMapping(value = "/knlgEdit", method = RequestMethod.GET)
    public String knlgEdit() {
        return "knlwarehouse/kClMagInputKnl.ftl";
    }


    //知识仓库详情页
    @RequestMapping(value = "/searchDetail", method = RequestMethod.GET)
    public String searchDetail() {
        return "knlwarehouse/knseClassifyPPT.ftl";
    }

    //考试管理考试分析页面
    @RequestMapping(value = "/detailPage", method = RequestMethod.GET)
    public String detailPage() {
        return "exam/paperManagerDetails.ftl";
    }

    //考试管理考试统计页面
    @RequestMapping(value = "/examCount", method = RequestMethod.GET)
    public String examCount() {
        return "exam/examMangCount.ftl";
    }

    //考试管理批卷页面
    @RequestMapping(value = "/examRevise", method = RequestMethod.GET)
    public String examRevise() {
        return "exam/examMangRevise.ftl";
    }

    //考试管理我的考试页面
    @RequestMapping(value = "/ownexam", method = RequestMethod.GET)
    public String ownexam() {
        return "exam/ownExam.ftl";
    }

    //个人中心-我的考试-开始考试
    @RequestMapping(value = "/beginExam", method = RequestMethod.GET)
    public String beginExam() {
        return "exam/beginExam.ftl";
    }

    //个人中心-我的考试-考试详情
    @RequestMapping(value = "/checkDetail", method = RequestMethod.GET)
    public String checkDetail() {
        return "exam/checkDetail.ftl";
    }

    //帖子列表
    @RequestMapping(value = "/postList", method = RequestMethod.GET)
    public String postList() {
        return "member/postList.ftl";
    }

    //用户退出登录
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        Enumeration em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        return "account/login.ftl";
    }


    //录入知识
    @RequestMapping(value = "/inputKnlg", method = RequestMethod.GET)
    public String inputKnlg() {
        return "knlwarehouse/kClMagInputKnl.ftl";
    }


    //左侧个人主页模板跳转
    @RequestMapping(value = "/persoalCenter/{id}", method = RequestMethod.GET)
    public String persoalCenter(@PathVariable("id") String id) {
        switch (id) {
            //通知列表
            case "2":
                return "member/informList.ftl";
            //通知审核
            case "3":
                return "member/informCheck.ftl";
            case "4":
                return "book/bookManager.ftl";
            //默认,会员中心
            default:
                return "member/memberCenter.ftl";
        }
    }

    //头部个人主页模板跳转
    @RequestMapping(value = "/topSkip/{id}", method = RequestMethod.GET)
    public String topSkip(@PathVariable("id") String id) {
        switch (id) {
            //知识仓库
            case "2":
                return "knlwarehouse/knlCfHomePage.ftl";
            //考试培训
            case "3":
                return "exam/initiateExam.ftl";
            //社区
            case "4":
                return "community/community.ftl";
            //外部专家
            case "5":
                return "specialist/externalSpecialist.ftl";
            //以上都不是,默认跳转
            default:
                return "resourceManager/orderHomepage.ftl";
        }
    }


    //左侧知识仓库,专家模板跳转
    @RequestMapping(value = "/knlgBank/{id}", method = RequestMethod.GET)
    public String knlgBank(@PathVariable("id") String id) {
        switch (id) {
            //知识仓库管理页面
            case "1":
                return "knlwarehouse/knseClfManager.ftl";
            //知识仓库管理页面
            case "4":
                return "specialist/specialistQuestion.ftl";
            //专家列表
            case "3":
                return "specialist/specManager.ftl";
            default:
                return "knlwarehouse/kClMagInputKnl.ftl";
        }
    }

    //左侧分类管理模板跳转
    @RequestMapping(value = "/classifyManager/{id}", method = RequestMethod.GET)
    public String classifyManager(@PathVariable("id") String id) {
        switch (id) {
            //知识仓库文章分类
            case "1":
                return "classify/knlwarehouseClassify.ftl";
            //社区文章分类
            case "2":
                return "classify/commArticleClassity.ftl";
            //部门分类
            case "3":
                return "classify/departmentClassify.ftl";
            //知识库流程节点分类
            case "5":
                return "classify/knlhouseNode.ftl";
            //else,默认考试分类
            default:
                return "classify/examTrainClassify.ftl";
        }
    }


    //左侧考试培训模板跳转
    @RequestMapping(value = "/examTrain/{id}", method = RequestMethod.GET)
    public String examTrain(@PathVariable("id") String id) {
        switch (id) {
            //发起考试
            case "1":
                return "exam/initiateExam.ftl";
            //题库管理
            case "2":
                return "exam/extrQusBankManager.ftl";
            //考卷列表
            case "3":
                return "exam/paperManager.ftl";
            //添加考卷
            case "4":
                return "exam/addPaper.ftl";
            //考试列表
            case "5":
                return "exam/examManager.ftl";
            //试卷详情
            case "6":
                return "exam/paperManagerDetails.ftl";
            //考试统计
            case "7":
                return "exam/examMangCount.ftl";
            //批卷
            case "8":
                return "exam/examMangRevise.ftl";
            //培训列表
            case "9":
                return "exam/trainManager.ftl";
            //发起培训-线上
            case "10":
                return "exam/trainMangOnline.ftl";
            //else,默认发起考试-线下
            default:
                return "exam/trainMangBelline.ftl";
        }
    }


    //左侧账号管理模块
    @RequestMapping(value = "/accountManager/{id}", method = RequestMethod.GET)
    public String accountManager(@PathVariable("id") String id) {
        switch (id) {
            //员工账号
            case "1":
                return "account/employeeAccount.ftl";
            //模块管理
            case "3":
                return "account/moduleManager.ftl";
            //权限设置
            default:
                return "account/permissionSetting.ftl";
        }
    }

    //左侧目录栏图书管理
    @RequestMapping(value = "/bookList", method = RequestMethod.GET)
    public String bookList() {
        return "book/bookManager.ftl";
    }

    //资源管理模块图书管理
    @RequestMapping(value = "/bookBorrow", method = RequestMethod.GET)
    public String bookBorrow() {
        return "book/bookBorrow.ftl";
    }

    //社区阅读全文跳转帖子详情
    @RequestMapping(value = "/communifyDisDetail", method = RequestMethod.GET)
    public String communifyDisDetail() {
        return "community/communityPostDel.ftl";
    }

    //社区阅读全文跳转帖子详情
    @RequestMapping(value = "/communifyQusDetail", method = RequestMethod.GET)
    public String communifyQusDetail() {
        return "community/communityEssayDel.ftl";
    }

    //左侧目录栏社区管理
    @RequestMapping(value = "/communifyInfo", method = RequestMethod.GET)
    public String communifyInfo() {
        return "community/communityManager.ftl";
    }

    //社区热点
    @RequestMapping(value = "/commHotSearch", method = RequestMethod.GET)
    public String commHotSearch() {
        return "community/community.ftl";
    }

    //社区模块提问分享
    @RequestMapping(value = "/askShare/{id}", method = RequestMethod.GET)
    public String askShare(@PathVariable("id") String id) {
        switch (id) {
            //提问
            case "1":
                return "community/communityQuestion.ftl";
            //分享
            case "2":
                return "community/communityShare.ftl";

            //默认分享页
            default:
                return "community/communityShare.ftl";
        }
    }

}
