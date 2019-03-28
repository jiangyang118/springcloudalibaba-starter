/**
 * Copyright (c) 2019,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:custom
 * Package Name:com.sunego.commerce
 * File Name:TestController.java
 * Date:2019年3月28日 下午5:04:24
 * 
 */
package com.sunego.commerce;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: TestController <br/>
 * Description: TODO <br/>
 * Date: 2019年3月28日 下午5:04:24 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("test")
    @ResponseBody
    public String test( ) throws ClientProtocolException, IOException {
        List<ServiceInstance> list = discoveryClient.getInstances("order");
        ServiceInstance si = list.get(0);
        String host = si.getHost();
        int port = si.getPort();
        String url = "http://" + host + ":" + port + "/order/create?name=a";
        HttpClient hc = new DefaultHttpClient();
        HttpPost hp = new HttpPost(url);
        HttpResponse res = hc.execute(hp);
        String str = "";
        if (res.getStatusLine().getStatusCode() == 200) {
            str = EntityUtils.toString(res.getEntity());
        }
        return str;
    }
    
    @Bean 
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Autowired
    private RestTemplate restTemplate;
    
    @RequestMapping("test2")
    @ResponseBody
    public String test2( ) {
        return restTemplate.getForObject("http://order/order/create?name=bas",String.class);
    }
}
