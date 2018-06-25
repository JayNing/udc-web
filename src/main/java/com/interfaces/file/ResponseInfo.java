package com.interfaces.file;


import java.util.Map;

/**
 * 返回信息
 * Created by liujinren on 2017/8/1.
 */
public class ResponseInfo {

    private Integer responseCode;
    private String responseMessage;
    private Map<String, String> object;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Map<String, String> getObject() {
        return object;
    }

    public void setObject(Map<String, String> object) {
        this.object = object;
    }
}
