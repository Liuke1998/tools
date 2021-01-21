package com.example.tools.httpService;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HttpService {

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig requestConfig;


    /**
     * 执行get请求，200返回响应内容，其他状态码返回null
     *
     * @return
     */
    public String doGet(String url) throws Exception {
        //创建httpClient对象
        CloseableHttpResponse response = null;
        HttpGet httpGet = new HttpGet(url.trim());
        //设置请求参数
        httpGet.setConfig(requestConfig);
        try {
            //执行请求
            response = httpClient.execute(httpGet);

            //判断返回状态码是否为200
            String data = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                return data;
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     * 执行带有参数的get请求
     *
     * @return
     */
    public String doGet(String url, Map<String, String> paramMap) throws Exception {
        URIBuilder builder = new URIBuilder(url.trim());
        for (Map.Entry<String, String> s : paramMap.entrySet()) {
            builder.addParameter(s.getKey(), s.getValue());
        }
        return doGet(builder.build().toString());
    }


    /**
     * 执行post请求
     *
     * @return
     */
    public HttpResult doPost(String url, Map<String, String> paramMap, Map<String, String> headers) throws Exception {
        HttpPost httpPost = new HttpPost(url.trim());
        //设置header
        if (headers != null && headers.size() > 1) {
            for (Map.Entry<String, String> s : headers.entrySet()) {
                httpPost.addHeader(s.getKey(), s.getValue());
            }
        }
        //设置请求参数
        if (paramMap != null) {
            List<NameValuePair> parameters = new ArrayList<>();
            for (Map.Entry<String, String> s : paramMap.entrySet()) {
                parameters.add(new BasicNameValuePair(s.getKey(), s.getValue()));
            }
            //构建一个form表单式的实体
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, StandardCharsets.UTF_8);
            //将请求实体放入到httpPost中
            httpPost.setEntity(formEntity);
        }
        //创建httpClient对象
        CloseableHttpResponse response = null;
        try {
            //执行请求
            response = httpClient.execute(httpPost);
            String data = EntityUtils.toString(response.getEntity());
            return new HttpResult(response.getStatusLine().getStatusCode(), data);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 执行post请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public HttpResult doPost(String url) throws Exception {
        return doPost(url, null, null);
    }


    /**
     * 提交json数据
     *
     * @return
     */
    public HttpResult doPostJson(String url, String json, Map<String, String> header) throws Exception {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url.trim());
        httpPost.setConfig(this.requestConfig);

        if (json != null) {
            // 构造一个请求实体
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(stringEntity);
        }
        // 添加头信息
        if (null != header && !header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                if (StringUtils.isNotBlank(entry.getKey())) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
        }
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = this.httpClient.execute(httpPost);
            return new HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 提交文本数据
     *
     * @return
     */
    public HttpResult doPostText(String url, String text, Map<String, String> header) throws Exception {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url.trim());
        httpPost.setConfig(this.requestConfig);

        if (text != null) {
            // 构造一个请求实体
            StringEntity stringEntity = new StringEntity(text, ContentType.create(MediaType.TEXT_PLAIN_VALUE, StandardCharsets.UTF_8));
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(stringEntity);
        }
        CloseableHttpResponse response = null;

        // 添加头信息
        if (null != header && !header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                if (StringUtils.isNotBlank(entry.getKey())) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
        }

        try {
            // 执行请求
            response = this.httpClient.execute(httpPost);
            return new HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 提交XML数据
     *
     * @return
     */
    public HttpResult doPostXML(String url, String text) throws Exception {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url.trim());
        httpPost.setConfig(this.requestConfig);

        if (text != null) {
            // 构造一个请求实体
            StringEntity stringEntity = new StringEntity(text, ContentType.create(MediaType.APPLICATION_XML_VALUE, StandardCharsets.UTF_8));
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(stringEntity);
        }
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = this.httpClient.execute(httpPost);
            return new HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }
}
