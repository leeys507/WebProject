package com.wp.controller.admin;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminViewController {

    @GetMapping("/yu/adminLogin")
    public String openAdminLoginView(Model model) {
        return "admin/adminLogin";
    }

    @GetMapping("/yu/adminIndex")
    public String openAdminIndexView(Model model) {
        return "admin/adminIndex";
    }
}
