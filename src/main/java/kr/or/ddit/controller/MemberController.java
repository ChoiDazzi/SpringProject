package kr.or.ddit.controller;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/member")
@Controller
public class MemberController {
    //Model에 폼 객체를 추가하지 않으면 오류 발생
    @GetMapping("/registerForm01")
    public String registerForm01(Model model) {
        model.addAttribute("memberVO", new MemberVO());
        return "member/registerForm01";
    }

    @GetMapping("/registerForm02")
    public String registerForm02(Model model) {
        model.addAttribute("memberVO", new MemberVO());
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
    public String registerForm05(MemberVO memberVO) {
        memberVO.setUserId("dazzi");
        memberVO.setUserName("seoyeon");

        return "member/registerForm05";
    }

    //@ModelAttribute 로 폼 객체의 속성명을 직접 지정할 수 있다.
    @GetMapping("/registerForm06")
    public String registerForm06(@ModelAttribute("user") MemberVO memberVO) {
        memberVO.setUserId("dazzi");
        memberVO.setUserName("seoyeon");
        memberVO.setCoin(500);

        return "member/registerForm06";
    }
}
