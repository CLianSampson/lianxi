package com.cl.lianxi;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Date;

public class UrlConfig {


//    public static String HOST = "https://121.46.247.72:443";

    public static String HOST = "https://kyfw.12306.cn";


    //初始化  cookie
    public static String initCookie = HOST + "/otn/resources/login.html";

    //public static String initCookie = "https://kyfw.12306.cn/otn/login/conf";

//    public static String initCookie = "https://kyfw.12306.cn/otn/login/init";



    //验证码图片的 base64 生成方式
//    public static String captchaImage64 = HOST + "/passport/captcha/captcha-image64?login_site=E&module=login&rand=sjrand&" +  new Date().getTime() + "&callback=jQuery19105195399483506546_1557822683965&_=1557822683966";
    public static String captchaImage64 = HOST + "/passport/captcha/captcha-image64?login_site=E&module=login&rand=sjrand&" +  new Date().getTime() + "&callback=jQuery19105195399483506546_" + new Date().getTime() + "&_=" + new Date().getTime();


    //验证验证码  参数 $input$ 用正则替换
//    public static String captchaCheck = HOST + "/passport/captcha/captcha-check?callback=jQuery19106336282621182714_1557911193566&answer=$input$&rand=sjrand&login_site=E&_=1557911193570";
    public static String captchaCheck = HOST + "/passport/captcha/captcha-check?callback=jQuery19106336282621182714_" + new Date().getTime() + "&answer=$input$&rand=sjrand&login_site=E&_=" + new Date().getTime();



    //登陆
    public static String webLogin = HOST + "/passport/web/login";


//    public static String webLogin ="http://127.0.0.1:8080/app/getForm";




}
