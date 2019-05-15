package com.cl.lianxi;

public class UrlConfig {

    //初始化  cookie
    public static String initCookie = "https://kyfw.12306.cn/otn/resources/login.html";


    //验证码图片的 base64 生成方式
    public static String captchaImage64 = "https://kyfw.12306.cn/passport/captcha/captcha-image64?login_site=E&module=login&rand=sjrand&1557822864180&callback=jQuery19105195399483506546_1557822683965&_=1557822683966";


    //验证验证码  参数 $input$ 用正则替换
    public static String captchaCheck ="https://kyfw.12306.cn/passport/captcha/captcha-check?callback=jQuery19106336282621182714_1557911193566&answer=$input$&rand=sjrand&login_site=E&_=1557911193570";



    //登陆
    public static String webLogin ="https://kyfw.12306.cn/passport/web/login";


}
