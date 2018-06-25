package com.mascot.controller;

import com.interfaces.mascot.BookManageService;
import com.mascot.bean.ResultInfo;
import com.mascot.constant.UdcConstant;
import com.mascot.utils.DateTransferUtil;
import com.mascot.utils.SessionGetUtil;
import com.thrift.common.body.BookInfo;
import com.thrift.common.body.UserInfo;
import com.thrift.common.define.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 图书管理
 *
 * @author jichao
 * 2018/4/15
 */
@Controller
@RequestMapping(value = "/bookManager")
public class BookManagerController {

    @Autowired
    private BookManageService bookManageService;

    private static final Log logger = LogFactory.getLog(BookManagerController.class);

    /**
     * 查询图书列表
     *
     * @param param      搜索框参数
     * @param start1Time 起始时间
     * @param end1Time   截止时间
     * @param pageIndex  当前页
     * @param pageSize   每页条数
     * @return resultInfo
     */
    @RequestMapping(value = "/queryBookInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryBookInfo(HttpServletRequest request, String param, String start1Time, String end1Time, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("queryBookInfo---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //对传入时间进行转换
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
            //传入值检验合法,返回信息
            List<Map<String, Object>> bookInfoList = bookManageService.queryBookInfo(userInfo, param, startTime, endTime, pageIndex, pageSize);
            if (null != bookInfoList && !bookInfoList.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(bookInfoList);
                logger.info("queryBookInfo-查询图书列表查询正确的返回值resultInfo----->" + resultInfo.getResult());
                return resultInfo;
            }
            //查询失败,返回信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryBookInfo-查询图书列表失败resultInfo----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryBookInfo---web查询图书列表异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }

    }

    /**
     * 新增图书
     *
     * @param bookId   图书编号 --- 必输项
     * @param bookName 书名 --- 必输项
     * @param author   作者 --- 必输项
     * @return result -2：图书编码已存在 ,resultInfo
     */
    @RequestMapping(value = "/saveBookInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo saveBookInfo(HttpServletRequest request, String bookId, String bookName, String author) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验bookId,bookName,author,userInfo参数的合法性
            if (null == userInfo || !StringUtils.hasText(bookId) || !StringUtils.hasText(bookName) || !StringUtils.hasText(author)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("saveBookInfo-参数校验不正确----->bookId==" + bookId + ",bookName==" + bookName + ",author==" + author + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值合法性
            Integer result = bookManageService.saveBookInfo(userInfo, bookId, bookName, author);
            if (result != null) {
                if (result == -2) {
                    resultInfo.setCode(ResponseCode.Failed.getValue());
                    resultInfo.setMsg(UdcConstant.BOOKCD_ALREADY_EXIST_MSG);
                    logger.info("saveBookInfo-图书编码已存在----->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == 1) {
                    //图书保存成功!
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.SAVE_BOOKINFO_SUCCESS_MSG);
                    logger.info("saveBookInfo图书保存----->" + resultInfo.getMsg());
                    return resultInfo;
                }
                //图书存入失败.
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.SAVE_BOOKINFO_FAIL_MSG);
                logger.info("saveBookInfo-图书存入失败----->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.INSERT_FAIL_MSG);
            logger.info("saveBookInfo图书保存----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("saveBookInfo图书保存---web图书入库异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 根据图书编号删除图书信息
     *
     * @param pkId 图书主键 --- 必输项
     * @return resultInfo
     */
    @RequestMapping(value = "deleteBookInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteBookInfo(HttpServletRequest request, Integer pkId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对输入参数进行检验
            if (null == userInfo || null == pkId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("deleteBookInfo-图书编码为空bookId----->" + pkId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,对返回值进行校验
            Integer result = bookManageService.deleteBookInfoByPkId(userInfo, pkId);
            if (null != result && result == 1) {
                //图书删除成功,返回信息.
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.DELETE_BOOKINFO_SUCCESS_MSG);
                logger.info("deleteBookInfo图书删除----->" + resultInfo.getMsg());
                return resultInfo;
            }
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.DELETE_FAIL_MSG);
            logger.info("deleteBookInfo-图书删除失败----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("deleteBookInfo图书删除---web图书删除异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 根据BookId查询该书借阅记录
     *
     * @param pkId      图书主键 --- 必输项
     * @param pageIndex 当前页
     * @param pageSize  每页条数
     * @return ResultInfo
     */
    @RequestMapping(value = "/queryBorrowRecord", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryBorrowRecord(HttpServletRequest request, Integer pkId, Integer pageIndex, Integer pageSize) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == pkId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryBorrowRecord-图书编码为空bookId----->" + pkId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //对传入的页面和页码进行验证
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            List<Map<String, Object>> borrowlist = bookManageService.queryBorrowRecordByPkId(userInfo, pkId, pageIndex, pageSize);
            if (null != borrowlist && !borrowlist.isEmpty()) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(borrowlist);
                logger.info("queryBorrowRecord图书借阅记录----->" + resultInfo.getResult());
                return resultInfo;
            }
            //借阅记录查询失败!
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_BORROWRE_FAIL_MSG);
            logger.info("queryBorrowRecord-图书的借阅记录查询失败----->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryBorrowRecord---web图书借阅记录查询异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 新增借阅记录
     *
     * @param bkPkId borrowDays 图书借阅的相关参数 --- 不为空
     * @return resultInfo
     */
    @RequestMapping(value = "/saveBorrowInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo saveBorrowInfo(HttpServletRequest request, Integer bkPkId, Integer borrowDays, String remark) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == bkPkId || null == borrowDays || borrowDays <= 0) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("saveBorrowInfo---传入参数不合法>bookId==" + bkPkId + ",borrowDays==" + borrowDays + ",userInfo" + userInfo);
                return resultInfo;
            }
            Integer userId = userInfo.getUserId();
            //获取当前时间,并加上borrowDays
            Date d = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String currdate = format.format(d);
            //currdate当前时间,也就是borrowTime
            Calendar ca = Calendar.getInstance();
            ca.add(Calendar.DATE, borrowDays);// borrowDays为增加的天数，可以改变的
            d = ca.getTime();
            //enddate是givebackTime,加上了borrowDays
            String enddate = format.format(d);
            //转换日期格式为date类型
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date givebackTime = null;
            try {
                givebackTime = sf.parse(enddate);
            } catch (ParseException e) {
                logger.debug("saveBorrowInfo---->转换日期格式为yyyy-MM-dd的ParseException异常.");
                e.printStackTrace();
            }
            //对返回值进行检验
            Integer result = bookManageService.saveBorrowInfo(userInfo, userId, bkPkId, borrowDays, givebackTime, remark);
            if (null != result && result == 1) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.BORROWINF_INSUCCESS_MSG);
                logger.info("saveBorrowInfo借阅表插入成功---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //借阅记录插入失败
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.BORROW_FAIL_MSG);
            logger.info("saveBorrowInfo借阅表插入失败---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("saveBorrowInfo---web图书借阅保存异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }

    /**
     * 查询图书列表总条数
     *
     * @param param      搜索框参数
     * @param start1Time 起始时间
     * @param end1Time   截止时间
     * @return count
     */
    @RequestMapping(value = "/getBookListCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getBookListCount(HttpServletRequest request, String param, String start1Time, String end1Time) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //检验userInfo的合法性
            if (null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.USERINF_NULL_MSG);
                logger.info("getBookListCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //对传入时间进行转换
            Date startTime = null;
            Date endTime = null;
            if (StringUtils.hasText(start1Time)) {
                startTime = DateTransferUtil.dateTransfer2(start1Time);
            }
            if (StringUtils.hasText(end1Time)) {
                endTime = DateTransferUtil.dateTransfer(end1Time);
            }
            //传入值检验合法,返回信息
            Integer result = bookManageService.getBookListCount(userInfo, param, startTime, endTime);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("getBookListCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //查询失败,返回失败信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getBookListCount---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getBookListCount---web总页数查询异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 查询该书借阅记录总条数
     *
     * @param pkId 图书编号 --- 必输项
     * @return list
     */
    @RequestMapping(value = "/getBorrowRecordCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getBorrowRecordCount(HttpServletRequest request, Integer pkId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //对userInfo进行检验
            if (null == userInfo || null == pkId) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getBorrowRecordCount-图书编码为空bookId----->" + pkId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //参数检验合法,调用server服务,检验返回值的合法性
            Integer result = bookManageService.getBorrowRecordCount(userInfo, pkId);
            if (null != result && result > 0) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(result);
                logger.info("getBorrowRecordCount---->" + resultInfo.getMsg());
                return resultInfo;
            }
            //返回失败信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("getBorrowRecordCount查询借阅记录总条数---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getBorrowRecordCount---web借阅记录总页数查询异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 归还图书
     *
     * @param userId 借阅用户Id --- 不为空
     * @param bkPkId 图书主键 --- 不为空
     * @return result
     */
    @RequestMapping(value = "/getGiveBackBook", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getGiveBackBook(HttpServletRequest request, Integer userId, Integer bkPkId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //验证传入值的合法性
            if (null == userId || null == bkPkId || null == userInfo) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("getGiveBackBook-归还图书----->userId==" + userId + ",bkPkId==" + bkPkId + ",userInfo==" + userInfo);
                return resultInfo;
            }
            //传入值合法,调用server服务,验证返回值的合法性
            Integer result = bookManageService.updateGiveBackBook(userInfo, bkPkId, userId);
            if (null != result) {
                if (result == 1) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.BOOK_GIVEBACK_SUCCESS);
                    logger.info("getGiveBackBook归还图书---->" + resultInfo.getMsg());
                    return resultInfo;
                } else if (result == -2) {
                    resultInfo.setCode(ResponseCode.Succeed.getValue());
                    resultInfo.setMsg(UdcConstant.BOOK_GIVEBACK_ALREADY);
                    logger.info("getGiveBackBook归还图书---->" + resultInfo.getMsg());
                    return resultInfo;
                }
            }
            //返回值不合法,返回失败信息.
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.GIVEBACK_FAIL_MSG);
            logger.info("getGiveBackBook归还图书---->" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("getGiveBackBook---web归还图书异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


    /**
     * 根据BookNo查询图书信息
     *
     * @param bookNo 图书编号 --- 必输项
     * @return resultInfo
     */
    @RequestMapping(value = "/queryBookInfoById", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo queryBookInfoById(HttpServletRequest request, String bookNo) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = SessionGetUtil.getSession(request);
            //验证传入值的合法性
            if (null == userInfo || !StringUtils.hasText(bookNo)) {
                resultInfo.setCode(ResponseCode.Failed.getValue());
                resultInfo.setMsg(UdcConstant.PARAM_ERROR_MSG);
                logger.info("queryBookInfoById-根据BookNo查询图书信息----->userInfo==" + userInfo + ",bookNo==" + bookNo);
                return resultInfo;
            }
            //参数校验合法,调用server服务,检验返回值的合法性
            BookInfo bookInfo = bookManageService.queryBookInfoById(userInfo, bookNo);
            if (bookInfo != null) {
                resultInfo.setCode(ResponseCode.Succeed.getValue());
                resultInfo.setMsg(UdcConstant.QUERY_SUCCESS_MSG);
                resultInfo.setResult(bookInfo);
                logger.info("queryBookInfoById---web根据BookNo查询图书信息" + resultInfo.getMsg());
                return resultInfo;
            }
            //检验值不合法,返回失败信息
            resultInfo.setCode(ResponseCode.Failed.getValue());
            resultInfo.setMsg(UdcConstant.QUERY_FAIL_MSG);
            logger.info("queryBookInfoById---web根据BookNo查询图书信息" + resultInfo.getMsg());
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(ResponseCode.Failed.getValue());
            logger.debug("queryBookInfoById---web根据BookNo查询图书信息异常!");
            resultInfo.setMsg(UdcConstant.OPERATE_FAIL_MSG);
            return resultInfo;
        }
    }


}