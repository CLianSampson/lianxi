package com.cl.lianxi.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cl.lianxi.UrlConfig;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login12306 extends AbstractQuery {


    public void doHttp(){

        String url = UrlConfig.captchaImage64;

        Request request = initHttpGet(url);
        Response response = httpGet(request);





    }

}
