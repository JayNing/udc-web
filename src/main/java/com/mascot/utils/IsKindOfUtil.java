package com.mascot.utils;


import com.thrift.common.define.MascotFileType;

import java.util.HashSet;
import java.util.Set;

/**
 * 文件类型识别工具类
 * Created by j on 2018/7/29.
 */
public class IsKindOfUtil {

    public static Integer getFileKind(String extName){

        //1.视频
        Set<String> set=new HashSet();
        set.add("AVI");
        set.add("wma");
        set.add("rmvb");
        set.add("rm");
        set.add("flash");
        set.add("mp4");
        set.add("mid");
        set.add("3GP");
       //2.PPT
        Set<String> set1=new HashSet();
        set1.add("ppt");
        set1.add("pptx");
        set1.add("dps");
        set1.add("dpt");
        set1.add("wpp");
        //3.PDF
        Set<String> set2=new HashSet();
        set2.add("pdf");
        //4.文档
        Set<String> set3=new HashSet();
        set3.add("doc");
        set3.add("docx");
        set3.add("xls");
        set3.add("xlsx");
        set3.add("wps");
        set3.add("wpt");
        set3.add("ett");
        set3.add("et");
        set3.add("exl");
        set3.add("txt");
        if (set.contains(extName)){
            return MascotFileType.Video.getValue();
        }else if (set1.contains(extName)){
            return MascotFileType.Ppt.getValue();
        }else if (set2.contains(extName)){
            return MascotFileType.Pdf.getValue();
        }else if (set3.contains(extName)){
            return MascotFileType.Document.getValue();
        }else{
            return null;
        }

    }

    //测试用,待删
    public static void main(String[] args) {
        Integer result = getFileKind("exl");
        System.out.println(result);
    }

}
