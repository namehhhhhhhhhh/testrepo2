package com.dotseven.poc.api.controller;

import com.dotseven.poc.api.model.Captcha;
import com.dotseven.poc.service.CaptchaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class CaptchaController {

    private CaptchaService captchaService;

    @Autowired
    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @GetMapping("/captcha")
    public Captcha getCaptcha(@RequestParam Integer id) {
        return captchaService.getCaptcha(id);
    }

}
