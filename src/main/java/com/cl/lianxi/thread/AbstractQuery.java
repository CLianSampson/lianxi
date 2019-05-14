package com.cl.lianxi.thread;


import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractQuery  {

    private OkHttpClient okHttpClient = new OkHttpClient();


    protected Request initHttpGet(String url){
//        Headers headers = new Headers.Builder()
//                .add("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
//                .add("Accept-Encoding","gzip, deflate, br")
//                .add("Accept-Language","zh-CN,zh;q=0.9")
//                .add("Cache-Control","no-cache")
//                .add("Connection","keep-alive")
//                .add("Cookie","JSESSIONID=75216C474F7D1CB3919DE433AF6475B8; _jc_save_wfdc_flag=dc; _jc_save_detail=true; ten_js_key=Z8g1e2V1n5yeJCmK0IjJZdRuP6wvh%2B59; BIGipServerotn=535298314.64545.0000; RAIL_EXPIRATION=1558075597073; RAIL_DEVICEID=Fh5jsjnnyA8fIAQ-8kBs_IojAh4WqPvUpmnW_k28y3sAqS9UZpOQWQKogBnG8W3L58VgIRV4ts6EPOKbKeRU9wwpdDerh3g_trXmP2ZEK50ZwTTKscwZZsmdlMZjvSJnAGBhQ2FThhmcUMf1kuoFBwkmUnifm83Z; BIGipServerpassport=1005060362.50215.0000; route=c5c62a339e7744272a54643b3be5bf64; _jc_save_fromStation=%u5317%u4EAC%2CBJP; _jc_save_toStation=%u4E0A%u6D77%2CSHH; _jc_save_fromDate=2019-05-14; _jc_save_toDate=2019-05-14")
//                .add("Cookie","_jc_save_wfdc_flag=dc; _jc_save_detail=true; ten_js_key=Z8g1e2V1n5yeJCmK0IjJZdRuP6wvh%2B59; BIGipServerotn=535298314.64545.0000; RAIL_EXPIRATION=1558075597073; RAIL_DEVICEID=Fh5jsjnnyA8fIAQ-8kBs_IojAh4WqPvUpmnW_k28y3sAqS9UZpOQWQKogBnG8W3L58VgIRV4ts6EPOKbKeRU9wwpdDerh3g_trXmP2ZEK50ZwTTKscwZZsmdlMZjvSJnAGBhQ2FThhmcUMf1kuoFBwkmUnifm83Z; BIGipServerpassport=1005060362.50215.0000; route=c5c62a339e7744272a54643b3be5bf64; _jc_save_fromStation=%u5317%u4EAC%2CBJP; _jc_save_toStation=%u4E0A%u6D77%2CSHH; _jc_save_fromDate=2019-05-14; _jc_save_toDate=2019-05-14")
//
//                .add("Host","kyfw.12306.cn")
//                .add("If-Modified-Since","0")
//                .add("Pragma","no-cache")
//                .add("Referer","Referer: https://kyfw.12306.cn/otn/leftTicket/init?linktypeid=dc&fs=%E5%8C%97%E4%BA%AC,BJP&ts=%E4%B8%8A%E6%B5%B7,SHH&date=2019-05-14&flag=N,N,Y")
//                .add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36")
//                .add("X-Requested-With","XMLHttpRequest").build();



        final Request request = new Request.Builder()
                .get()//默认就是GET请求，可以不写
//                .headers(null)
                .url(url)
                .build();

        System.out.println("request header is : " + request.headers());

        return request;
    }



    protected Request initHttpPost(String url, JSONObject jsonObject){
        Headers headers = new Headers.Builder()
                .add("Accept","*/*")
                .add("Accept-Encoding","gzip, deflate, br")
                .add("Accept-Language","zh-CN,zh;q=0.9")
                .add("Cache-Control","no-cache")
                .add("Connection","keep-alive")
                .add("Cookie","JSESSIONID=75216C474F7D1CB3919DE433AF6475B8; _jc_save_wfdc_flag=dc; _jc_save_detail=true; ten_js_key=Z8g1e2V1n5yeJCmK0IjJZdRuP6wvh%2B59; BIGipServerotn=535298314.64545.0000; RAIL_EXPIRATION=1558075597073; RAIL_DEVICEID=Fh5jsjnnyA8fIAQ-8kBs_IojAh4WqPvUpmnW_k28y3sAqS9UZpOQWQKogBnG8W3L58VgIRV4ts6EPOKbKeRU9wwpdDerh3g_trXmP2ZEK50ZwTTKscwZZsmdlMZjvSJnAGBhQ2FThhmcUMf1kuoFBwkmUnifm83Z; BIGipServerpassport=1005060362.50215.0000; route=c5c62a339e7744272a54643b3be5bf64; _jc_save_fromStation=%u5317%u4EAC%2CBJP; _jc_save_toStation=%u4E0A%u6D77%2CSHH; _jc_save_fromDate=2019-05-14; _jc_save_toDate=2019-05-14")
                .add("Host","kyfw.12306.cn")
                .add("If-Modified-Since","0")
                .add("Pragma","no-cache")
                .add("Referer","Referer: https://kyfw.12306.cn/otn/leftTicket/init?linktypeid=dc&fs=%E5%8C%97%E4%BA%AC,BJP&ts=%E4%B8%8A%E6%B5%B7,SHH&date=2019-05-14&flag=N,N,Y")
                .add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36")
                .add("X-Requested-With","XMLHttpRequest").build();




        MediaType mediaType = MediaType.parse("application/json");


        RequestBody body = RequestBody.create(mediaType, jsonObject.toJSONString());

        final Request request = new Request
                .Builder()
                .post(body)//Post请求的参数传递
                .url(url)
                .build();




        System.out.println("request header is : " + request.headers());



        return request;
    }


    protected Response httpGet(Request request){
        return httpImpl(request);
    }




    protected Response httpPost(Request request){
        return httpImpl(request);
    }



    protected Response httpImpl(Request request){
        Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();

            System.out.println("response header: "  + response.headers().toString());

//            String jsonstr = new String(Base64.decode(response.body().getBytes(), Base64.URL_SAFE));


            //此处只能调用一次
            String baseStr = response.body().string();


//            System.out.println("onResponse: " + base);


            Pattern p = Pattern.compile("\\((.*?)\\)");//正则表达式，取=和|之间的字符串，不包括=和|
//        基础数据成功率：(.*?)%
            Matcher m = p.matcher(baseStr);
            while(m.find()) {
                System.out.println(m.group(1));//m.group(1)不包括这两个字符

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }


























    public void  test(){
        String url = "http://wwww.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("onResponse: " + response.body().string());
            }
        });
    }


    public void synchronizedRequest(){


        String url = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2019-05-14&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=SHH&purpose_codes=ADULT";
        Headers headers = new Headers.Builder()
                .add("Accept","*/*")
                .add("Accept-Encoding","gzip, deflate, br")
                .add("Accept-Language","zh-CN,zh;q=0.9")
                .add("Cache-Control","no-cache")
                .add("Connection","keep-alive")
                .add("Cookie","JSESSIONID=75216C474F7D1CB3919DE433AF6475B8; _jc_save_wfdc_flag=dc; _jc_save_detail=true; ten_js_key=Z8g1e2V1n5yeJCmK0IjJZdRuP6wvh%2B59; BIGipServerotn=535298314.64545.0000; RAIL_EXPIRATION=1558075597073; RAIL_DEVICEID=Fh5jsjnnyA8fIAQ-8kBs_IojAh4WqPvUpmnW_k28y3sAqS9UZpOQWQKogBnG8W3L58VgIRV4ts6EPOKbKeRU9wwpdDerh3g_trXmP2ZEK50ZwTTKscwZZsmdlMZjvSJnAGBhQ2FThhmcUMf1kuoFBwkmUnifm83Z; BIGipServerpassport=1005060362.50215.0000; route=c5c62a339e7744272a54643b3be5bf64; _jc_save_fromStation=%u5317%u4EAC%2CBJP; _jc_save_toStation=%u4E0A%u6D77%2CSHH; _jc_save_fromDate=2019-05-14; _jc_save_toDate=2019-05-14")
                .add("Host","kyfw.12306.cn")
                .add("If-Modified-Since","0")
                .add("Pragma","no-cache")
                .add("Referer","Referer: https://kyfw.12306.cn/otn/leftTicket/init?linktypeid=dc&fs=%E5%8C%97%E4%BA%AC,BJP&ts=%E4%B8%8A%E6%B5%B7,SHH&date=2019-05-14&flag=N,N,Y")
                .add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36")
                .add("X-Requested-With","XMLHttpRequest").build();



        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .headers(headers)
                .build();


        System.out.println("request header is : " + request.headers());


        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();

            System.out.println("response header: "  + response.headers().toString());

            System.out.println("onResponse: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void post(){

        String url = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2019-05-14&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=SHH&purpose_codes=ADULT";


        MediaType mediaType = MediaType.parse("application/json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key","hello world");

        RequestBody body = RequestBody.create(mediaType, jsonObject.toJSONString());

        Request request = new Request
                .Builder()
                .post(body)//Post请求的参数传递
                .url(url)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String result = response.body().string();
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        AbstractQuery abstractQuery = new  AbstractQuery();
        abstractQuery.synchronizedRequest();
    }


}
