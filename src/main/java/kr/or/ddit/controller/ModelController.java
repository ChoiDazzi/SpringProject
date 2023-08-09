package kr.or.ddit.controller;

import kr.or.ddit.vo.AddressVO;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@RequestMapping("/model")
@Controller
public class ModelController {
    //Model 객체: 컨트롤러에서 생성된 데이터를 담아서 view에 전달

    @GetMapping("/home")
    public String home(Model model) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        String today = formatter.format(date);
        log.info("today={}", today);

        model.addAttribute("today", today);
        //model객체는 redirect에서는 안됨

        return "model/home";
    }

    @GetMapping("/read01")
    public String read01(Model model) {
        model.addAttribute("userId", "dazzi");
        model.addAttribute("password", "java");
        model.addAttribute("email", "ddit@test.com");

        return "model/read01";
    }

    @GetMapping("/read02")
    public String read02(Model model) {
        MemberVO memberVO = new MemberVO();

        memberVO.setUserId("dazzi");
        memberVO.setPassword("java");
        memberVO.setCoin(100);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DAY_OF_MONTH, 10);

        memberVO.setDateOfBirth(calendar.getTime());

        //model.addAttribute("memberVO", memberVO);
        model.addAttribute(memberVO);
        return "model/read02";
    }

    //Model객체에 자바빈즈 클래스 객체를 특정한 이름을 지정해 전달 가능
    @GetMapping("/read03")
    public String read03(Model model) {
        MemberVO memberVO = new MemberVO();

        memberVO.setUserId("dazzi");
        memberVO.setPassword("java");
        memberVO.setCoin(100);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DAY_OF_MONTH, 10);

        memberVO.setDateOfBirth(calendar.getTime());

        //model.addAttribute("memberVO", memberVO);
        model.addAttribute("member", memberVO);
        return "model/read03";
    }

    @GetMapping("/read04")
    public String read04(Model model) {
        String[] carArray = {"벤츠", "포니"};

        //리스트
        List<String> carList = new ArrayList<>();
        carList.add("페라리");
        carList.add("그렌져");

        model.addAttribute("carArray", carArray);
        model.addAttribute("carList", carList);

        return "model/read04";
    }

    @GetMapping("/read05")
    public String read05(Model model) {
        MemberVO memberVO = new MemberVO();

        AddressVO addressVO = new AddressVO();
        addressVO.setZonecode("12345");
        addressVO.setAddress("대전 중구");
        addressVO.setBuildingName("문화동 123");

        memberVO.setAddressVO(addressVO); // 1:1

        List<CardVO> cardVOList = new ArrayList<>();
        CardVO cardVO1 = new CardVO();
        cardVO1.setNo("11111");
        cardVO1.setValidMonth("202103");
        cardVOList.add(cardVO1);

        CardVO cardVO2 = new CardVO();
        cardVO2.setNo("22222");
        cardVO2.setValidMonth("202104");
        cardVOList.add(cardVO2);

        memberVO.setCardVOList(cardVOList); //1:N

        model.addAttribute("memberVO", memberVO);

        return "model/read05";
    }

    //@ModelAttribute: 전달받은 매개변수를 강제로 Model에 담아서 전달할 때 사용
    @GetMapping("/registerForm")
    public String registerForm() {

        return "model/registerForm";
    }

    @PostMapping("/register01")
    public String register01(String userId, String password) {
        log.info("userId={}", userId);
        log.info("password={}", password);

        return "model/result";
    }

    @PostMapping("/register02")
    public String register02(@ModelAttribute("userId") String userId, @ModelAttribute("password") String password) {
        log.info("userId={}", userId);
        log.info("password={}", password);

        return "model/result";
    }

    //자바빈즈 규칙(멤버변수, Constructor, getter/setter) 에 맞는 객체는
    //매개변수로 선언하면 기본적으로 forwarding시 데이터를 전달함
    @PostMapping("/register04")
    public String register04(MemberVO memberVO) {
        log.info("memberVO={}", memberVO);

        return "model/result";
    }
}
