package com.mascot.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* 日期转换
* */
public class DateTransferUtil {


    //结束时间
    public static Date dateTransfer(String time ){
        //转换string-to-日期格式为yyyy-MM-dd的时间格式
        String time2 = time + " 23:59:59";
        //2018-06-04 13:36:46
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = null;
        try {
            time1 = sf.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time1;
    }


    //开始时间
    public static Date dateTransfer2(String time ){
        //转换string-to-日期格式为yyyy-MM-dd的时间格式第二种
        String time2 = time + " 00:00:00";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = null;
        try {
            time1 = sf.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time1;
    }

    public static List<Integer> pageCheck(Integer pageIndex, Integer pageSize){
        List<Integer> list = new ArrayList<>();
        if (null == pageIndex) {
            pageIndex = 1;
        }
        list.add(pageIndex);
        if (null == pageSize) {
            pageSize = 10;
        }
        list.add(pageSize);
        return list;
    }


}
