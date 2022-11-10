package com.vote.system.controller;

import cn.hutool.core.lang.Dict;
import com.vote.system.service.WeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Enrace
 */
@RestController
public class WeBaseController {
    @Autowired
    WeBaseService service;

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Dict haveAccount(@RequestParam("account") String account) {
        return service.haveAccount(account);
    }

    @RequestMapping(value="/myself",method = RequestMethod.POST)
    public Dict getAllproject(@RequestParam("account") String account) {
        return service.getAllproject(account);
    }
}
