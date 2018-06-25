package com.mascot.constant;

/*
* 常量类
* */
public class UdcConstant {

    //图书入库
    public static final String SAVE_BOOKINFO_FAIL_MSG = "图书存入失败!";
    public static final String SAVE_BOOKINFO_SUCCESS_MSG = "图书存入成功!";
    //删除图书
    public static final String DELETE_BOOKINFO_SUCCESS_MSG = "图书删除成功!";
    //图书借阅
    public static final String QUERY_BORROWRE_SUCCESS_MSG = "借阅记录查询成功!";
    public static final String QUERY_BORROWRE_FAIL_MSG = "暂无对应借阅记录!";
    public static final String BOOK_GIVEBACK_ALREADY= "图书已归还!";
    public static final String BORROW_FAIL_MSG = "借阅失败!";
    //ResultInfo
    public static final String USERINF_NULL_MSG = "用户信息不能为空!";
    public static final String BORROWINF_INSUCCESS_MSG = "借阅成功!";
    public static final String BOOK_GIVEBACK_SUCCESS = "图书归还成功!";
    //账号管理
    public static final String ACCOUNT_EXIST_MSG = "该用户已经存在!";
    public static final String ACCOUNT_INSERT_FAIL_MSG = "新增用户失败!";
    public static final String DELETE_ACCOUNT_SUCCESS_MSG = "删除用户成功!";
    public static final String QUERY_USERINFO_SUCCESS_MSG = "账户信息查询成功!";
    public static final String QUERY_USERINFO_FAIL_MSG = "账户信息查询失败!";
    public static final String QUERY_USERINFO_ALL_SUCCESS = "查询用户详细信息成功!";
    public static final String QUERY_USERINFO_ALL_FAIL = "查询用户详细信息失败!";
    public static final String UPDATE_USERINFO_SUCCESS_MSG = "编辑更改用户信息成功!";
    public static final String UPDATE_USERINFO_FAIL_MSG = "编辑更改用户信息失败!";
    public static final String ROLE_EXIST_MSG = "该角色已存在!";
    public static final String MODULE_FAIL_MSG1 = "请先输入上级模块的权限码!";
    public static final String MODULE_FAIL_MSG2 = "上级分类为空!";
    public static final String MODULE_FAIL_MSG3 = "模块码名称重复!";
    public static final String MODULE_FAIL_MSG4 = "权限码重复!";
    //权限管理
    public static final String NOT_HAVE_PARENT_NAME = "暂无父模块!";
    //公共
    public static final String DELETE_SUCCESS_MSG = "删除成功!";
    public static final String DELETE_FAIL_MSG = "删除失败!";
    public static final String PARAM_ERROR_MSG = "参数不合法!";
    public static final String OPERATE_SUCCESS_MSG = "操作成功!";
    public static final String OPERATE_FAIL_MSG = "操作异常!";
    public static final String BOOKCD_ALREADY_EXIST_MSG = "该编号已存在!";
    public static final String QUERY_SUCCESS_MSG = "查询成功!";
    public static final String QUERY_FAIL_MSG = "暂无相关的搜索内容!";
    public static final String QUERY_FAIL_NULL_MSG = "暂无相关的搜索内容!";
    public static final String INSERT_SUCCESS_MSG = "添加成功!";
    public static final String INSERT_FAIL_MSG = "添加失败!";
    public static final String UPFATE_FAIL_MSG = "修改失败!";
    public static final String UPFATE_SUCCESS_MSG = "修改成功!";
    public static final String GIVEBACK_FAIL_MSG = "归还失败!";
    public static final String NILL_ACCOUNT_MSG  ="暂无用户信息!";
    //分类模块
    public static final String CLASSIFY_EXIST_MSG = "该分类已存在!";
    //知识仓库
    public static final String INSERT_KNOWLEDGE_SUCCESS = "知识新增成功!";
    public static final String FILEINFO_ADD_FAIL = "附件上传失败!";
    public static final String ADD_FAILINTO_DATABASE = "附件上传成功,知识添加失败!";
    public static final String FILE_UPLOAD_SUCCESS = "文件上传成功!";
    public static final String FILE_UPLOAD_FAIL = "文件上传失败!";
    public static final String FILE_NOT_SUPPORT = "暂不支持该类型文件!";
    public static final String UPDATE_KNOWLEDGE_SUCCESS = "知识修改成功!";
    public static final String UPDATE_KNOWLEDGE_FAIL = "知识修改成功!";
    public static final String KNLG_COMMENT_NULL = "知识内容不能为空!";
    //专家点赞
    public static final String SPE_LIKE_FLAG_ALREADY = "已经点过赞喽!";
    public static final String SPE_LIKE_FLAG_NOT = "还未点赞哦!";
    //平台用户登录
    public static final String USER_LOGIN_FAIL = "用户登录失败!";
    public static final String USER_LOGIN_SUCCESS = "登陆成功!";
    public static final String USER_LOGIN_FAIL_MSG= "密码错误!";
    public static final String ACCOUNT_LOGIN_FAIL= "账号或密码错误!";
    public static final String ACCOUNT_NOT_EXIST= "账号不存在!";
    public static final String ACCOUNT_NOT_AUTH= "账号无授权!";
    //bbs社区模块
    public static final String ISTOP_SUCCESS_MSG = "置顶成功!";
    public static final String ISTOP_REFOSE_MSG = "置顶已取消!";
    public static final String ISTOP_FAIL_MSG = "置顶失败!";
    public static final String QUESTION_ADD_SUCCESS = "问题已提出!";
    public static final String ESSAY_ADD_SUCCESS = "贴文添加成功!";
    public static final String ADD_FOLLOW_SUCCESS_MSG = "关注成功!";
    public static final String ADD_FOLLOW_FAIL_MSG = "关注失败!";
    public static final String COMMENT_ADD_SUCCESS = "评论成功!";
    public static final String COMMENT_ADD_FAIL = "评论失败!";
    public static final String ANSWER_SUCCESS_MSG = "回答成功!";
    public static final String BESTANSWER_ALREADY_EXIST = "最佳答案已存在!";
    public static final String ADD_KNLG_SUCCESS = "加入知识仓库成功!";
    public static final String ADD_KNLG_SUCCESS_ALREADY = "已加入知识仓库!";
    public static final String ADD_KNLG_FAIL = "该提问没有最佳答案，无法加入知识仓库!";
    public static final String TAG_NOT_RIGHT = "标签格式不正确!";
    public static final String COLLECT_SUCCESS_MSG = "收藏成功!";
    public static final String CANCEL_FOLLOW_SUCCESS = "关注已取消!";
    public static final String CANCEL_COLLECT_SUCCESS = "收藏已取消!";
    public static final String ADD_LIKE_SUCCESS = "点赞成功!";
    public static final String RECALL_LIKE_SUCCESS = "取消成功!";
    public static final String NOTICE_ISSUE_SUCCESS = "公告发布成功!";
    public static final String NOTICE_ISSUE_FAIL = "公告发布失败!";
    public static final String USERFUL_HAVE_ALREADY = "已点无用，请先取消!";
    public static final String UNUSERFUL_HAVE_ALREADY = "已点有用，请先取消!";
    //考试培训
    public static final String ADDPAPER_EXAM_REPEAT_FAIL = "标题重复!";
    public static final String ADDPAPER_COUNT_FAIL_MSG = "总分值不是100!";
    public static final String INIT_EXAM_SUCCESS = "发起考试成功!";
    public static final String COMMENT_SUCCESS_MSG = "评估成功!";
    public static final String SUBMIT_SUCCESS_MSG = "提交成功!";
    public static final String SUBMIT_FAIL_MSG = "提交失败!";
}