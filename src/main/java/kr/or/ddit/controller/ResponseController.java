package kr.or.ddit.controller;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //반환값이 컬렉션 타입이면 JSON 객체 배열 타입으로 자동 변환
    //Collection List 타입 (JSON 객체 배열 타입의 데이터를 만들어서 반환)
    @ResponseBody
    @GetMapping("/returnList")
    public List<BookVO> returnList(BookVO bookVO) {
        log.info("returnList");
        List<BookVO> bookList = new ArrayList<BookVO>();

        bookVO.setBookId(3);
        BookVO vo1 = bookService.detail(bookVO);
        bookList.add(vo1);

        bookVO.setBookId(4);
        BookVO vo2 = bookService.detail(bookVO);
        bookList.add(vo2);

        log.info("bookList={}", bookList);
        return bookList;
    }

    @ResponseBody
    @PostMapping("/ex3")
    public BookVO ex3(@RequestBody BookVO bookVO) {
        log.info("bookVO post={}", bookVO);

        bookVO = bookService.detail(bookVO);

        return bookVO;
    }

    //Collection Map 타입
    @ResponseBody
    @GetMapping("/returnMap")
    public Map<String, BookVO> returnMap() {
        log.info("returnMap");

        Map<String, BookVO> map = new HashMap<String, BookVO>();
        BookVO vo1 = new BookVO();
        vo1.setBookId(3);
        vo1 = bookService.detail(vo1);
        map.put(String.valueOf(vo1.getBookId()), vo1);

        BookVO vo2 = new BookVO();
        vo2.setBookId(4);
        vo2 = bookService.detail(vo2);
        map.put(String.valueOf(vo2.getBookId()), vo2);

        return map;
    }

    //ResponseEntity<Void> 타입
    //Void : 반환할 데이터가 없음 (response 할 때 Http 헤더 정보와 내용을 가공함)
    @ResponseBody
    @GetMapping("/returnRE")
    public ResponseEntity<Void> returnRE() {
        log.info("return ResponseEntity");

        //HttpStatus.OK: 200
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //ResponseEntity<String> 타입
    //response할 때 Http 헤더 정보와 문자열 데이터를 전달하는 용도로 사용
    @ResponseBody
    @GetMapping("/returnRES")
    public ResponseEntity<String> returnRES() {
        log.info("return ResponseEntity<String>");
        String result = "";

        result = "SUCCESS";

        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    //ResponseEntity<Java Beans> 타입
    //response할 때 Http 헤더 정보와 객체 데이터를 전달하는 용도로 사용
    @ResponseBody
    @GetMapping("/returnJB")
    public ResponseEntity<BookVO> returnJB(BookVO bookVO) {
        log.info("return ResponseEntity<Java Beans>");

        bookVO.setBookId(3);
        bookVO = bookService.detail(bookVO);

        return new ResponseEntity<BookVO>(bookVO, HttpStatus.OK);
    }

    //ResponseEntity<List> 타입
    //response할 때 Http 헤더 정보와 객체 배열 데이터를 전달하는 용도로 사용
    @ResponseBody
    @GetMapping("/returnREL")
    public List<String> returnREL() {
        log.info("return ResponseEntity<List>");

        List<String> list = new ArrayList<String>();
        list.add("fiction");
        list.add("IT");
        list.add("essay");

        log.info("list={}", list);

        return list;
    }

    @ResponseBody
    @GetMapping("/returnREM")
    public ResponseEntity<Map<String, BookVO>> returnREM() {
        log.info("return ResponseEntity<Map>");

        Map<String, BookVO> map = new HashMap<String, BookVO>();

        BookVO vo1 = new BookVO();
        vo1.setBookId(3);
        vo1 = bookService.detail(vo1);
        map.put(String.valueOf(vo1.getBookId()), vo1);

        BookVO vo2 = new BookVO();
        vo2.setBookId(4);
        vo2 = bookService.detail(vo2);
        map.put(String.valueOf(vo2.getBookId()), vo2);

        return new ResponseEntity<Map<String, BookVO>>(map, HttpStatus.OK);
    }

    //ResponseEntity<byte[]> 타입
    //response할 때 Http 헤더 정보와 바이너리 파일 데이터를 전달하는 용도로 사용
    @ResponseBody
    @GetMapping("/returnREB")
    public ResponseEntity<byte[]> returnREB() throws IOException{
        log.info("home1101");

        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        try {
            HttpHeaders headers = new HttpHeaders();

            in = new FileInputStream("/Users/ChoiSeoYeon/SpringExercises/springProj/src/main/webapp/resources/images/P1234.jpg");

            headers.setContentType(MediaType.IMAGE_JPEG);

            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {
                in.close();
        }
        return entity;
    }

    //파일 다운로드에서 활용
    @ResponseBody
    @GetMapping("/returnFile")
    public ResponseEntity<byte[]> returnFile() throws IOException{
        //Stream: 파일을 읽거나 쓸 때, 네트워크 소켓을 거쳐 통신할 때 쓰이는 추상적인 개념, 데이터가 전송되는 통로
        //InputStream: 추상클래스. 데이터가 들어오는 통로의 역할에 관해 규정
        //1) 데이터를 읽어야 함 2) 남은 데이터 확인 3) 데이터 skip가능 4) close 가능 (통로 제거) 5) 특정 시점부터 다시 읽을 수 있음
        log.info("file");

        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        HttpHeaders headers = new HttpHeaders();

        try {
            in = new FileInputStream("/Users/ChoiSeoYeon/SpringExercises/springProj/src/main/webapp/resources/images/P1235.jpg");

            headers.setContentType(MediaType.IMAGE_JPEG);

            //IOUtils: byte로 관리
            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED); //HTTP 201
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {
            in.close();
        }

        return entity;
    }
}
