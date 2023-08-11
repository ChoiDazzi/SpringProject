package kr.or.ddit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
}
