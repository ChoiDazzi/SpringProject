package kr.or.ddit.controller;

import kr.or.ddit.vo.AddressVO;
import kr.or.ddit.vo.MemberVO_backup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/member")
@Controller
public class MemberController {
    //Model에 폼 객체를 추가하지 않으면 오류 발생
    @GetMapping("/registerForm01")
    public String registerForm01(Model model) {
        model.addAttribute("memberVO", new MemberVO_backup());
        return "member/registerForm01";
    }

    @GetMapping("/registerForm02")
    public String registerForm02(Model model) {
        model.addAttribute("memberVO", new MemberVO_backup());
        return "member/registerForm02";
    }

    /*
        컨트롤러 메서드의 매개변수로 자바빈즈 객체가 전달이 되면
        forwarding 시 뷰(registerForm.jsp)로 memberVO를 전달함
        컨트롤러는 자바빈즈 규칙에 맞는 객체를 뷰로 전달함
        폼 객체의 속성명은 직접 지정하지 않으면 폼 객체의 클래스명의 맨 처음 문자를
        소문자로 변환하여 처리함
     */
    @GetMapping("/registerForm05")
    public String registerForm05(MemberVO_backup memberVO) {
        memberVO.setUserId("dazzi");
        memberVO.setUserName("seoyeon");

        return "member/registerForm05";
    }

    //@ModelAttribute 로 폼 객체의 속성명을 직접 지정할 수 있다.
    @GetMapping("/registerForm06")
    public String registerForm06(@ModelAttribute("user") MemberVO_backup memberVO) {
        memberVO.setUserId("dazzi");
        memberVO.setUserName("seoyeon");
        memberVO.setCoin(500);

        return "member/registerForm06";
    }

    @GetMapping("/registerForm07")
    public String registerForm07(@ModelAttribute("addressVO") AddressVO addressVO) {
        addressVO.setZonecode("12345");
        addressVO.setAddress("대전 중구 문화동");
        addressVO.setBuildingName("대덕인재개발원");

        return "member/registerForm07";
    }

    @GetMapping("/registerForm08")
    public String registerForm08(@ModelAttribute("memberVO") MemberVO_backup memberVO, Model model) {
        memberVO.setUserId("dazzi");
        memberVO.setUserName("seoyeon");
        memberVO.setPassword("java"); //view에 반영 안 됨
        memberVO.setIntroduction("hello\nmy name is seoyeon");
        String[] hobbys = {"reading", "camping"};
        memberVO.setHobbys(hobbys);

        Map<String, String> hobbymap = new HashMap<>();
        hobbymap.put("reading", "reading");
        hobbymap.put("swimming", "swimming");
        hobbymap.put("camping", "camping");

        model.addAttribute("hobbyMap", hobbymap);

        memberVO.setGender("Female");
        Map<String, String> genderMap = new HashMap<>();
        genderMap.put("Male", "Male");
        genderMap.put("Female", "Female");
        genderMap.put("Other", "Other");
        model.addAttribute("genderMap", genderMap);

        Map<String, String> nationalityMap = new HashMap<String, String>();
        nationalityMap.put("Korea", "한국");
        nationalityMap.put("Germany", "독일");
        nationalityMap.put("Australia", "호주");
        model.addAttribute("nationalityMap", nationalityMap);

        String[] cars = {"audi", "qm5"};
        memberVO.setCars(cars);
        Map<String, String> carsMap = new HashMap<>();
        carsMap.put("audi", "audi");
        carsMap.put("grandure", "grandure");
        carsMap.put("qm5", "qm5");
        model.addAttribute("carsMap", carsMap);

        return "member/registerForm08";
    }

    //입력값 검증을 할 도메인 클래스에 @Validated를 지정함
    @PostMapping("/registerForm08Post")
    public String registerForm08Post(@Validated @ModelAttribute("memberVO") MemberVO_backup memberVO,
                                     BindingResult result, Model model) {
        log.info("memberVO={}", memberVO);
        if (result.hasErrors()) {//유효성 검증 실패
            List<ObjectError> allErrors = result.getAllErrors(); //모든
            List<ObjectError> globalErrors = result.getGlobalErrors(); //객체
            List<FieldError> fieldErrors = result.getFieldErrors(); //멤버변수

            log.info("allError.size() : {}", allErrors.size());
            log.info("globalErrors.size() : {}", globalErrors.size());
            log.info("fieldErrors.size() : {}", fieldErrors.size());


            for (int i = 0; i < allErrors.size(); i++) {
                ObjectError objectError = allErrors.get(i);
                log.info("allError : {}", objectError);
            }

            for (int i = 0; i < globalErrors.size(); i++) {
                ObjectError objectError = globalErrors.get(i);
                log.info("globalErrors : {}", objectError);
            }

            for (int i = 0; i < fieldErrors.size(); i++) {
                FieldError fieldError = fieldErrors.get(i);
                log.info("fieldErrors : {}", fieldError);
                log.info("fieldError.getDefaultMessage : {}", fieldError.getDefaultMessage());
            }

            Map<String, String> nationalityMap = new HashMap<>();
            nationalityMap.put("Korea", "한국");
            nationalityMap.put("Germany", "독일");
            nationalityMap.put("Australia", "호주");
            model.addAttribute("nationalityMap", nationalityMap);

            String[] cars = {"audi", "qm5"};
            memberVO.setCars(cars);
            Map<String, String> carsMap = new HashMap<>();
            carsMap.put("audi", "audi");
            carsMap.put("grandure", "grandure");
            carsMap.put("qm5", "qm5");
            model.addAttribute("carsMap", carsMap);

    return "member/registerForm08";
            }
            return "member/result"; //유효성 검사 통과
        }

        @GetMapping("/loginMember")
        public String loginMember() {
            return "member/loginMember";
        }
    }
