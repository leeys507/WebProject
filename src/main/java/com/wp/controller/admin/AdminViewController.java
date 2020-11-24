package com.wp.controller.admin;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminViewController {

    @GetMapping("/yu/admin/index")
    public String openAdminIndexView(Model model) {
        return "admin/adminIndex";
    }
}
