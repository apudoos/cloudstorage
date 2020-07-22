package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/result")
public class ResultController {

    @GetMapping
    public String getHome(@RequestParam Map<String,String> allParams, Model model) {

        if (allParams.containsKey("success")) {
            model.addAttribute("message", "SUCCESS");
        } else if (allParams.containsKey("deleteSuccess")) {
            model.addAttribute("message", "SUCCESS");
        } else if (allParams.containsKey("failure")) {
            model.addAttribute("message", "FAILURE");
            model.addAttribute("error", allParams.get("failure"));
        }

        return "result";

    }


}
