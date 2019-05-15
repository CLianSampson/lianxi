package com.cl.lianxi.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cl.lianxi.UrlConfig;
import okhttp3.Request;
import okhttp3.Response;
import sun.jvm.hotspot.memory.HeapBlock;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaptchImage64 extends AbstractQuery {

    /**
     * 将12306返回的 jsonp 转化成json
     * @param baseStr
     * @return
     */
    public String getJsonFromJsonp(String baseStr){
        Pattern p = Pattern.compile("\\((.*?)\\)");//正则表达式，取(和)之间的字符串，不包括(和)
        Matcher m = p.matcher(baseStr);
        String jsonStr = "";
        while(m.find()) {
            jsonStr = jsonStr + m.group(1);
        }

        return jsonStr;
    }

    /**
     * 初始化cookie
     */
    public void getCookie(){
        System.out.println("初始化cookie....");

        String url = UrlConfig.initCookie;

        Request request = initHttpGet(url);
        httpGet(request);
    }


    /**
     * 获取验证码图片
     * @return
     */
    public String getCaptchImage(){
        System.out.println("获取验证码图片....");

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



        String jsonStr = getJsonFromJsonp(baseStr);


        JSONObject jsonObject = JSON.parseObject(jsonStr);


        String imageStr = (String) jsonObject.get("image");

//        System.out.println(imageStr);

        base64ToPic(imageStr,"/data/anmoyi");

        //输入验证码坐标
        System.out.println("请输入验证码坐标...");
        String input = scan();
        String[] select = input.split(",");

        String answer = getCaptchaLocation(select);

        return answer;
    }


    /**
     * 验证验证码
     * @param answer  验证码坐标
     */
    public void captchCheck(String answer){

        System.out.println("验证验证码....");

        //验证验证码
        String url = UrlConfig.captchaCheck;

        url = url.replaceAll("\\$input\\$",answer);

        System.out.println("验证验证码 url is : ");
        System.out.println(url);

        Request request = initHttpGet(url);
        Response response = httpGet(request);

        String responsestr = null;
        try {
            responsestr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("验证验证码返回: ");
        System.out.println(responsestr);


        String jsonStr = getJsonFromJsonp(responsestr);


        JSONObject jsonObject = JSON.parseObject(jsonStr);
        System.out.println("jsonObject is :");
        System.out.println(jsonObject);

        if (jsonObject.get("result_code").equals("5")){
            System.out.println("验证码校验错误，需要重新请求");
            System.out.println(jsonObject.get("result_message"));

            //重新获取
            doHttp();
        }

    }



    public void login(String answer){
        System.out.println("登陆....");


        String url = UrlConfig.webLogin;


//        JSONObject params = new JSONObject();
//        params.put("username","cl448169133");
//        params.put("password","Cl448169133");
//        params.put("appid","otn");
//        params.put("answer",answer);


        //此处 answer 顺序不一样
        Request request = initHttpPost(url,answer);


        System.out.println("登陆强求头 : ");
        System.out.println(request.headers());
        System.out.println("登陆 body : ");
        System.out.println(request.body().toString());

        Response response = httpPost(request);


        String responsestr = null;
        try {
            responsestr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("登陆返回: ");
        System.out.println(responsestr);


        String jsonStr = getJsonFromJsonp(responsestr);


        JSONObject jsonObject = JSON.parseObject(jsonStr);
        System.out.println("jsonObject is :");
        System.out.println(jsonObject);

        if (jsonObject.get("result_code").equals("5")){
            System.out.println("登陆错误，需要重新请求");
            System.out.println(jsonObject.get("result_message"));

            //重新获取
//            doHttp();
        }
    }


    public void doHttp(){

        //初始化cookie
        getCookie();

        //获取验证码图片
        String answer = getCaptchImage();


        captchCheck(answer);

        login(answer);

    }

    public static void main(String[] args) {
        CaptchImage64 captchImage64 = new CaptchImage64();

        captchImage64.doHttp();


//        String input = captchImage64.scan();
//        String[] select = input.split(",");
//
//        String answer = captchImage64.getCaptchaLocation(select);


    }


    public String scan(){

        String input = "";

        //创建Scanner对象
        //System.in表示标准化输出，也就是键盘输出
        Scanner sc = new Scanner(System.in);


        //利用hasNextXXX()判断是否还有下一输入项
        while (sc.hasNext()) {
            //利用nextXXX()方法输出内容
            String str = sc.next();
//            System.out.println(str);

            input = input + str;
            break;
        }

        System.out.println("input is : ");
        System.out.println(input);

        return input;
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



    public String getCaptchaLocation(String[] select){
        int offsetsX = 0;
        int offsetsY = 0;

        int count = select.length;

        List<Integer> xArry = new ArrayList<>(count);
        List<Integer> yArry = new ArrayList<>(count);



        for (int i = 0; i < count; i++) {
            switch (Integer.parseInt(select[i])){
                case 1 :
                    offsetsY = 34;
                    offsetsX = 37;
                    break;
                case 2 :
                    offsetsY = 38;
                    offsetsX = 105;
                    break;
                case 3 :
                    offsetsY = 53;
                    offsetsX = 181;
                    break;
                case 4 :
                    offsetsY = 44;
                    offsetsX = 257;
                    break;
                case 5 :
                    offsetsY = 109;
                    offsetsX = 40;
                    break;
                case 6 :
                    offsetsY = 109;
                    offsetsX = 115;
                    break;
                case 7 :
                    offsetsY = 113;
                    offsetsX = 189;
                    break;
                case 8 :
                    offsetsY = 117;
                    offsetsX = 261;
                    break;
            }

            xArry.add(new Integer(offsetsX));
            yArry.add(new Integer(offsetsY));
        }

        String answer = "";
        for (int i = 0; i < count; i++) {
            answer = answer + xArry.get(i) + ",";
        }

        for (int i = 0; i < count; i++) {
            if (i == count-1){
                answer = answer + yArry.get(i);
            }else {
                answer = answer + yArry.get(i) + ",";
            }
        }

        System.out.println("answer is :");
        System.out.println(answer);

        return answer;

    }


}
