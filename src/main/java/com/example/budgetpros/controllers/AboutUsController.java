package com.example.budgetpros.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AboutUsController {
    @GetMapping("/about-us")
    public String returnSearchForm() {
        return "aboutUs";
    }
//
//    @PostMapping("/search")
//    public String returnSearchResults(@RequestParam String query, Model model) {
//        model.addAttribute("search", query);
//        return "search-results";
//    }

}
