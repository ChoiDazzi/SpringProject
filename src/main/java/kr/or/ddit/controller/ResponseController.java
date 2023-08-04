package kr.or.ddit.controller;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/resp")
public class ResponseController {
    private final BookService bookService;

    public ResponseController(BookService bookService) {
        this.bookService = bookService;
    }

    //void : 호출하는 URL과 동일한 뷰 이름을 나타내기 위해 사용
    @GetMapping("/goHome")
    public void home() {
        log.info("home");
    }

    @GetMapping("/goHomeTwo")
    public void homeTwo() {
        log.info("homeTwo");
    }
    //URL경로
    @GetMapping("/str")
    public String str() {
        log.info("str");

        //파일 경로
        return "resp/str";
    }

    @GetMapping("/strTwo")
    public String strTwo() {
        log.info("strTwo");

        return "resp/strTwo";
    }

    @GetMapping("/strRedirect")
    public String strRedirect() {
        log.info("strRedirect");

        return "redirect:/resp/strTwo";
    }

    @GetMapping("/goHomeThree")
    public String goHomeThree() {
        log.info("goHomeThree");

        return "/resp/goHomeThree"; //tiles 적용이 안됨 (절대경로라서)
    }

    @GetMapping("/homethree")
    public String homethree() {
        log.info("homethree");

        return "resp/homeThree";
    }

    //자바빈즈 클래스 타입 (VO => JSON)
    //@ResponseBody를 지정하지 않으면 HTTP404 오류 발생
    @ResponseBody //pom.xml => dependencies => Library (jackson-databind) - 없으면 HTTP406 오류 발생 -
    @GetMapping("/javaBeans")
    public AttachVO javaBeans() {
        log.info("javaBeans");

        AttachVO attachVO = new AttachVO();
        attachVO.setSeq(1);
        attachVO.setBookId("ISBN1234");
        attachVO.setFilename("강아지.jpg");

        return attachVO;
    }

    @ResponseBody //VO => JSON
    @GetMapping(value = "/ex1")
    public BookVO ex1(@RequestBody BookVO bookVO) {
        log.info("bookVO={}", bookVO);

        bookVO = bookService.detail(bookVO);
        log.info("bookVO={}", bookVO);

        return bookVO;
    }

    @ResponseBody
    @PostMapping("/ex2")
    public BookVO ex2(@RequestBody BookVO bookVO) {
        log.info("bookVO post={}", bookVO);

        bookVO = bookService.detail(bookVO);

        return bookVO;
    }

    @ResponseBody
    @PostMapping("/ex3")
    public BookVO ex3(@RequestBody BookVO bookVO) {
        log.info("bookVO post={}", bookVO);

        bookVO = bookService.detail(bookVO);

        return bookVO;
    }
}
