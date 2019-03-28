/**
 * Copyright (c) 2019,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:order
 * Package Name:com.sunego.commerce
 * File Name:TestController.java
 * Date:2019年3月26日 下午6:34:54
 * 
 */
package com.sunego.commerce;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: TestController <br/>
 * Description: TODO <br/>
 * Date: 2019年3月26日 下午6:34:54 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Controller
@RequestMapping("order")
public class TestController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("create")
    @ResponseBody
    public String create(String name) {
        return " create successful, name = " + name + " , port = " + port;
    }
}
