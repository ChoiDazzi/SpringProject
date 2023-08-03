package kr.or.ddit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //Params 매핑: 요청 파라미터를 매핑 조건으로 지정하는 경우 params 속성을 사용 (jsp의 name값 - button 또는 a 태그 - 이 넘어온다)
    @PostMapping(value="/post", params="register")
    public String register(String bookId) {
        log.info("register");
        return "board/register";
    }

    @PostMapping(value="/post", params="update")
    public String updatePost(String bookId){
            log.info("update");
            return "board/register";
    }

    @PostMapping(value="/post", params="delete")
    public String deletePost(String bookId){
            log.info("delete");
            return "board/register";
    }

    @PostMapping(value="/post", params="list")
    public String listPost(String bookId){
            log.info("list");
            return "board/register";
    }

    //board/get?remove
    @GetMapping(value="/get", params="remove")
    public String removeGet() {
        log.info("removeGet");
        return "board/register";
    }

    @PostMapping(value="/post", params="remove")
    public String removePost() {
        log.info("removePost");
        return "board/register";
    }

    @GetMapping(value="/get", params="read")
    public String readPost() {
        log.info("read");
        return "board/register";
    }

    @PostMapping(value="/{boardNo}")
    public ResponseEntity<String> modifyPost(@PathVariable("boardNo") int boardNo) {
        ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

        return entity;
    }

}
