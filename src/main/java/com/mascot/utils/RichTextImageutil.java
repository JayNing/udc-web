package com.mascot.utils;

/*
* 富文本编辑器上传工具类*/

public class RichTextImageutil {

    private Integer errno;
    private String[] data;


    public RichTextImageutil() {
    }

    public RichTextImageutil(Integer errno, String[] data) {
        this.errno = errno;
        this.data = data;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }


}
