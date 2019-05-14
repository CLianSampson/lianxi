package com.cl.lianxi.thread;

import com.cl.lianxi.UrlConfig;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaptchImage64 extends AbstractQuery {


    public void doHttp(){

        String url = UrlConfig.captchaImage64;

        Request request = initHttpGet(url);
        Response response = httpGet(request);




//        String filetext = "//@张小名: 25分//@李小花: 43分//@王力: 100分";


//        String base = null;
//        try {
//            base = new String(response.body().string());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Pattern p = Pattern.compile("\\@(.*?)\\:");//正则表达式，取=和|之间的字符串，不包括=和|

//        Pattern p = Pattern.compile("((.*?))");//正则表达式，取=和|之间的字符串，不包括=和|
////        基础数据成功率：(.*?)%
//        Matcher m = p.matcher(base);
//        while(m.find()) {
//            System.out.println(m.group(0));//m.group(1)不包括这两个字符
//
//        }


    }

    public static void main(String[] args) {
        CaptchImage64 captchImage64 = new CaptchImage64();

        captchImage64.doHttp();
    }




    /**
     * 根据base64编码生成图片，保存在Path中
     *
     * @param base64Code
     * @param filePath
     */
    public static boolean base64ToPic(String base64Code, String filePath) {
        if (base64Code == null) {
            return false;
        }
        //文件不存在
        File existsFilePath = new File(filePath);
        if (!existsFilePath.exists()) {
            existsFilePath.mkdir();
        }
//        String handledBase64Code = "";
//        if (StringUtils.contains(base64Code, "base64,")) {
//            handledBase64Code = StringUtils.substringAfter(base64Code, "base64,");
//        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer("base64");
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            String path = filePath + "/" + UUID.randomUUID() + ".jpg";
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }





}
