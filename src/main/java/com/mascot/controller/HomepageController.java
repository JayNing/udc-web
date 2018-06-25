package com.mascot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页
 *
 * @author jichao
 * 2018/4/27
 */
@Controller
@RequestMapping(value = "/homepage")
public class HomepageController {

    //登陆后跳转首页homepage
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String loginSkip() {
        return "homepage/homepage.ftl";
    }

}
