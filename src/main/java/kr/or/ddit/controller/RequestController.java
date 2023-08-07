package kr.or.ddit.controller;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/req")
@Controller
public class RequestController {

    @ResponseBody
    @GetMapping("/request")
    public Map<String, String> registerByParameter(String userId, String password) {
        log.info("userId={}", userId);
        log.info("password={}", password);

        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("password", password);

        return map;
    }

    @ResponseBody
    @GetMapping("/register/{userId}")
    public String registerByPath(@PathVariable String userId) {
        log.info("userId={}", userId);

        return userId;
    }

    @GetMapping("/register01")
    public String register01() {
        log.info("register01");

        return "req/register01";
    }

    @ResponseBody
    @PostMapping("/register01")
    public String register01Post(String userId) {
        log.info("register01Post");
        log.info("userId={}", userId);
        return userId;
    }

    @ResponseBody
    @PostMapping("/register02")
    public Map<String, Object> register02Post(String userId, int coin, String password) {
        log.info("register02Post");
        log.info("userId={}", userId);
        log.info("coin={}", coin);
        log.info("password={}", password);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("coin", coin);
        map.put("password", password);

        return map;
    }

    //요청 데이터 처리 Annotation
    /*
        PathVariable: URL에서 경로 변수 값을 가져옴
        RequestParam: 요청 파라미터 값을 가져옴
        RequestBody: 요청 본문 내용(JSON)을 가져옴
        RequestHeader: 요청 헤더 값을 가져옴
        CookieValue: 쿠키 값을 가져옴
     */
    @ResponseBody
    @GetMapping("/register/{userId}/{coin}")
    public String registerByPath(@PathVariable String userId, @PathVariable int coin) {
        log.info("userId={}", userId);
        log.info("coin={}", coin);

        return "SUCCESS";
    }

    @GetMapping("/register02")
    public String register02() {
        return "req/register02";
    }

    @ResponseBody
    @PostMapping("/register0201")
    public String register0201(String userId) {
        log.info("userId={}", userId);

        return "SUCCESS";
    }

    @GetMapping("/register04")
    public String register04() {
        return "req/register04";
    }

    @ResponseBody
    @PostMapping("/register04")
    public MemberVO register0401(MemberVO memberVO, int coin) {
        log.info("userId={}", memberVO.getUserId());
        log.info("password={}", memberVO.getPassword());
        log.info("coin={}", memberVO.getCoin());

        log.info("coin={}", coin);
        return memberVO;
    }

    //Date 타입 처리
    @ResponseBody
    @GetMapping("/registerByGet01")
    public String registerByGet01(String userId, Date dateOfBirth) {
        log.info("userId={}", userId);
        log.info("dateOfBirth={}", dateOfBirth);

        return "SUCCESS";
    }

    //Date 타입 처리
    @ResponseBody
    @GetMapping("/registerByGet02")
    public String registerByGet02(MemberVO memberVO){
        log.info("userId={}", memberVO.getUserId());
        log.info("dateOfBirth={}",memberVO.getDateOfBirth() );

        return "SUCCESS";
    }
}
