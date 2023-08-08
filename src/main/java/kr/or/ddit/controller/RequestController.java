package kr.or.ddit.controller;

import kr.or.ddit.util.FileUploadUtils;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    public String registerByGet01(String userId, Date dateOfBirth) { //2023/08/07 같은 형태
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

    @ResponseBody
    @PostMapping("/register0402")
    public String register0402(String userId, int coin, String gender
            , @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth
            , String nationality, String[] cars, ArrayList<String> homeList
            , String[] hobbys, String developer
            , boolean foreigner, String introduction
            , MemberVO memberVO) {
        log.info("userId : {}", userId);
        log.info("coin : {}", coin);
        //dataOfBirth : Thu Jul 04 00:00:00 KST 1996
        log.info("dateOfBirth : {}", dateOfBirth);
        log.info("gender : {}", gender);
        log.info("nationality : {}", nationality);
        //보유자동차 String[] cars
        for(String car : cars) {
            log.info("car : {}", car);
        }
        //집 ArrayList<String> homeList : List매개변수는 안 됨
        homeList = memberVO.getHomeList();
        if(homeList!=null) {
            for(int i=0; i<homeList.size(); i++) {
                log.info("home : {}", homeList.get(i));
            }
        }

        for(String hobby : hobbys) {
            log.info("hobby : {}", hobby);
        }
        //개발자 여부
        log.info("developer : {}", developer);
        //외국인 여부
        log.info("foreigner : {}", foreigner);

        log.info("memberVO : {}", memberVO);

        return "SUCCESS";
    }

    //파일 업로드 폼 방식 요청 처리
    //파일 업로드 폼 파일 요소값을 스프링 MVC가 지원하는 MultipartFile 매개변수로 처리함
    @GetMapping("/registerFile01")
    public String registerFile01() {
        return "req/registerFile01";
    }

    @GetMapping("/registerFile02")
    public String registerFile02() {
        return "req/registerFile02";
    }

    @ResponseBody
    @PostMapping("/registerFile01Post")
    public String registerFile01Post(MultipartFile picture) {
        log.info("원본 파일명={}", picture.getOriginalFilename());
        log.info("파일 크기={}", picture.getSize());
        log.info("파일 MIME 타입={}", picture.getContentType());

        return "SUCCESS";
    }

    @ResponseBody
    @PostMapping("registerFile02Post")
    public String registerFile02Post(String userId, String password, MultipartFile picture) {
        log.info("userId={}", userId);
        log.info("password={}", password);

        log.info("원본 파일명={}", picture.getOriginalFilename());
        log.info("파일 크기={}", picture.getSize());
        log.info("파일 MIME 타입={}", picture.getContentType());

        return "SUCCESS";
    }

    @GetMapping("/registerFile03")
    public String registerFile03() {
        return "req/registerFile03";
    }

    @ResponseBody
    @PostMapping("/registerFile03Post")
    public String registerFile03Post(MemberVO memberVO) {
        log.info("userId={}", memberVO.getUserId());
        log.info("password={}", memberVO.getPassword());

        MultipartFile picture = memberVO.getPicture();
        log.info("원본 파일명={}", picture.getOriginalFilename());
        log.info("파일 크기={}", picture.getSize());
        log.info("파일 MIME 타입={}", picture.getContentType());

        return "SUCCESS";
    }

    @GetMapping("/registerFile04")
    public String registerFile04() {
        return "req/registerFile04";
    }

    @ResponseBody
    @PostMapping("/registerFile04Post")
    public String registerFile04Post(MemberVO memberVO) {
        log.info("userId={}", memberVO.getUserId());
        log.info("password={}", memberVO.getPassword());

        MultipartFile[] pictures = memberVO.getPictures();

        String result = FileUploadUtils.multiUpload(pictures);

        log.info("result={}" , result);
        return "SUCCESS";
    }
}
