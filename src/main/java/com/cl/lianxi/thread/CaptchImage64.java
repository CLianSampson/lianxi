package com.cl.lianxi.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cl.lianxi.UrlConfig;
import okhttp3.Request;
import okhttp3.Response;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaptchImage64 extends AbstractQuery {


    public void doHttp(){

        String url = UrlConfig.captchaImage64;

        Request request = initHttpGet(url);
        Response response = httpGet(request);



        //此处只能调用一次
        String baseStr = null;
        try {
            baseStr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Pattern p = Pattern.compile("\\((.*?)\\)");//正则表达式，取(和)之间的字符串，不包括(和)
        Matcher m = p.matcher(baseStr);
        String jsonStr = "";
        while(m.find()) {
//            System.out.println(m.group(1));//m.group(1)不包括这两个字符

            jsonStr = jsonStr + m.group(1);
        }


//        System.out.println(jsonStr);

        JSONObject jsonObject = JSON.parseObject(jsonStr);


        String imageStr = (String) jsonObject.get("image");

//        System.out.println("imageStr is : ");
//        System.out.println(imageStr);


//        String imageBase64Str = "data:image/jpg;base64," + imageStr;

        String imageBase64Str =  imageStr;


        System.out.println("\n");
        System.out.println("\n");

        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");

        System.out.println(imageBase64Str);



//        base64StringToImage(imageBase64Str,"/data/anmoyi/1.jpg","jpg");

        base64ToPic(imageBase64Str,"/data/anmoyi");

    }

    public static void main(String[] args) {
        CaptchImage64 captchImage64 = new CaptchImage64();

        captchImage64.doHttp();
    }




    /**
       * 字符串转图片
       * @param base64String
       */
    public static boolean base64StringToImage(String base64String,String toImagePath,String imageType) {
        try {



//            //文件不存在
//            File existsFilePath = new File(toImagePath);
//            if (!existsFilePath.exists()) {
//                existsFilePath.mkdir();
//            }

            BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64String);

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            RenderedImage bi1 = ImageIO.read(bais);
            File w2 = new File(toImagePath);// 可以是jpg,png,gif格式
            if(!w2.exists()){
                w2.createNewFile();
                System.out.println("no exist=====");
            }
            System.out.println("pass...........");
            return ImageIO.write(bi1, imageType, w2);// 不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(base64Code);
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
