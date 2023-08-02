package kr.or.ddit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    //void: return "board/register"; //forwarding
    @GetMapping("/register")
    public void registerForm() {
        log.info("registerForm");
    }

    @GetMapping("/modify")
    public void modyfyForm() {
        log.info("modifyForm");
    }

    @GetMapping("/read")
    public void read(){
        log.info("read");
    }
}
