package com.mascot.utils;


/**
 * 常量类
 * Created by liu on 2017/8/9.
 */
public interface Constant {

    // 文件服务器服务信息
    String FILE_SYSTEM_HEAD = "http://10.128.5.150:16801/filesystem";
    String FILE_SYSTEM_DOWNLOAD = FILE_SYSTEM_HEAD + "/download";
    String FILE_HEAD_TAG = "<FILE_SYSTEM_HEAD/>";
    String FILE_UPLOAD_URL_PATH = FILE_SYSTEM_HEAD + "/upload/common";
    //测试用,待删
    String URL_PATH = "http://10.128.5.52:7070/filesystem/upload/common";
    // 线程数
    Integer secureServiceThreadCount = 5;

    String FILE_SYSTEM_URL = "";

}
