package com.vote.system.controller;

import cn.hutool.core.lang.Dict;
import com.vote.system.service.WeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Enrace
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class WeBaseController {
    @Autowired
    WeBaseService service;

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Dict haveAccount(@RequestBody Map<String,String> account) {
        return service.haveAccount(account.get("account"));
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public Dict signAccount(@RequestBody Map<String,String> account){
        return service.signAccount(account.get("account"));
    }

    @RequestMapping(value="/myself",method = RequestMethod.POST)
    public Dict getAllproject(@RequestParam("account") String account) {
        return service.getAllproject(account);
    }
}
