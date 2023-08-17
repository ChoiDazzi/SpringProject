package kr.or.ddit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class SecurityController {

    @GetMapping("/freeboard/list")
    public String freeboardList() {
        return "freeboard/list";
    }

    @GetMapping("/freeboard/register")
    public String freeboardRegister() {
        return "freeboard/register";
    }

    @GetMapping("/notice/list")
    public String noticeList() {
        return "notice/list";
    }

    @GetMapping("/notice/register")
    public String noticeRegister() {
        return "notice/register";
    }

    @GetMapping("/security/accessError")
    public String accessError(Authentication authentication, Model model) {
        //authentication: 로그인이 시도된 정보를 담고 있음
        log.info("access Denied Id: {}", authentication.getName());
        model.addAttribute("message", "Access Denied");

        return "security/accessError";
    }


}
