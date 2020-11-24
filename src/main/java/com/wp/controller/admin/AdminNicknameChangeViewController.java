package com.wp.controller.admin;

import com.wp.domain.report.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminNicknameChangeViewController {
    @GetMapping("/yu/admin/nicknameChange")
    public String openNicknameChangeView(Model model, Pageable pageable) {
        return "admin/adminNickNameChangeView";
    }
}
