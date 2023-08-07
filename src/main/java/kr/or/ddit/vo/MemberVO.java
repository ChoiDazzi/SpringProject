package kr.or.ddit.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MemberVO {
    private String userId;
    private String password;
    private int coin;
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
}
