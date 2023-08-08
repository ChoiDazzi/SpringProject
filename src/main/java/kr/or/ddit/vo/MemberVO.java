package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

// 자바빈 클래스
@Data
public class MemberVO {
    private String userId;
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