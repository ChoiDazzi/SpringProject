package kr.or.ddit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    //board테이블의 12번글 (/board/read/12)
    //boardNo: 경로 변수: path variable
    @GetMapping("/read/{no}")
    public String read(@PathVariable("no") int boardNo){
        log.info("no={}", boardNo);

        return "board/read";
    }

    @GetMapping("/update/{boardNo}")
    public String update(@PathVariable int boardNo) {
        return "board/update";
    }

    @GetMapping(value = "/get", params = "register")
    public String register() {
        log.info("register");
        return "board/register";
    }

    @PostMapping(value = "/post", params = "register")
    public String registerPost(){

            return "board/register";
    }
}
