package com.practice.pyeonhan_sesang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiaryController {

    @GetMapping("/diary")
    public String diaryController() {
        return "diary";
    }
}
