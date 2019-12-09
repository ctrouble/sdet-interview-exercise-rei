package com.rei.interview.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {

    public UiController() {
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "application OK");
        return "index";
    }
}
