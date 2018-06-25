package com.mascot.utils;

import com.interfaces.file.ResponseInfo;
import com.thrift.common.define.ResponseCode;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 文件上传工具类
 * Created by Jerry on 2016/7/29.
 */
public class FileUploadUtils {


    // 换行符
    private static final String newLine = "\r\n";
    private static final String boundaryPrefix = "--";
    // 定义数据分隔线
    private static final String BOUNDARY = "========7d4a6d158c9";


    /**
     * 上传文件
     * @param urlPath 上传文件 URL
     * @param fileStream 文件数据流
     * @param params 参数
     */
    public static ResponseInfo uploadFile(String urlPath, final InputStream fileStream, Map<String, Object> params, String fileName) throws IOException {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setResponseCode(ResponseCode.Failed.getValue());
        if (urlPath == null || fileStream == null || fileName == null){
            return responseInfo;
        }

        URL url = new URL(urlPath);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setRequestProperty("Connection","Keep-Alive");
        urlConnection.setRequestProperty("Charset","UTF-8");
        urlConnection.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);
        OutputStream outputStream = urlConnection.getOutputStream();

        StringBuilder sb = new StringBuilder();
        sb.append(boundaryPrefix);
        sb.append(BOUNDARY);
        sb.append(newLine);
        sb.append("Content-Length:\"33\"");
        sb.append(newLine);
        // 文件参数,photo参数名可以随意修改
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + URLEncoder.encode(fileName, "UTF-8")
                + "\"" + newLine);
        sb.append("Content-Type:application/octet-stream");
        // 参数头设置完以后需要两个换行，然后才是参数内容
        sb.append(newLine);
        sb.append(newLine);
        // 将参数头的数据写入到输出流中
        outputStream.write(sb.toString().getBytes());
        byte[] bytes = new byte[1024] ;
        int len;
        while ((len = fileStream.read(bytes)) != -1){
            outputStream.write(bytes, 0, len);
        }
        fileStream.close();

        // 参数
        StringBuilder param = new StringBuilder();
        param.append(newLine);

        if (params != null && params.size() > 0){
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                param.append(boundaryPrefix).append(BOUNDARY).append(newLine);
                param.append("Content-Disposition: form-data; name=\"").append(entry.getKey()).append("\"");
                param.append(newLine).append(newLine);
                param.append(entry.getValue());
                param.append(newLine);
            }
        }
        // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
        param.append(boundaryPrefix).append(BOUNDARY).append(boundaryPrefix).append(newLine);

        outputStream.write(param.toString().getBytes());
        outputStream.flush();
        outputStream.close();

        int responseCode = urlConnection.getResponseCode();

        if (responseCode == 200){
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder result = new StringBuilder();
            byte[] rByte = new byte[1024];
            while (inputStream.read(rByte) != -1){
                result.append(new String(rByte, "UTF-8"));
            }
            JSONObject object = JSONObject.fromObject(result.toString());
            responseInfo = (ResponseInfo) JSONObject.toBean(object, ResponseInfo.class);
//            if (responseInfo.getObject() != null){
//                JSONObject object_1 = JSONObject.fromObject(responseInfo.getObject());
//                Map<String, String> map = (FileInfo) JSONObject.toBean(object_1, FileInfo.class);
//                responseInfo.setObject(fileBean);
//            }
        }
        return responseInfo ;
    }

}
