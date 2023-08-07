package kr.or.ddit.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MemberVO {
    private String userId;
    private String password;
    private int coin;
    private Date dateOfBirth;
}
