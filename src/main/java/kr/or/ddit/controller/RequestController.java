package kr.or.ddit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
