package com.xin.http;

import com.google.common.collect.Lists;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.List;

/**
 * @author Three
 * @since 18/6/14下午8:24
 */
public class ApacheClient {
    public static void main(String[] args) throws Exception {

        HttpPost                 post   = new HttpPost("http://www.baidu.com");
        List<BasicNameValuePair> params = Lists.newArrayList();
        params.add(new BasicNameValuePair("api_key", "KwGoc7IkT3eSFnmFs6fwcQxI84tRKrXT"));//param
        post.addHeader("Connection", "keep-alive");//header
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = HttpClients.createDefault().execute(post);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
