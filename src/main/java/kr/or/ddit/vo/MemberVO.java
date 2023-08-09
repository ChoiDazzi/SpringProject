package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

// 자바빈 클래스
@Data
public class MemberVO {
    //입력값 검증 규칙을 지정
    @NotBlank(message = "아이디는 필수입니다.")
    private String userId;
    @NotBlank(message = "이름은 필수입니다.")
    @Size(max=6)
    private String userName;
    private String password;
    private int coin;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    private String gender;
    private String nationality;
    private String[] cars;
    private ArrayList<String> homeList;
    private String[] hobbys;
    private String developer;
    private boolean foreigner;
    // 주소
    // 중첩된 자바빈
    // MemberVO : AddressVO = 1 : 1
    private AddressVO addressVO;
    // 보유 카드들
    private List<CardVO> cardVOList;
    private String introduction;
    private MultipartFile picture;
    //다중 파일 업로드
    private MultipartFile[] pictures;
}