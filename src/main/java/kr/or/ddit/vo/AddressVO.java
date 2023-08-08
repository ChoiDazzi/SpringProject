package kr.or.ddit.vo;

import lombok.Data;

@Data
public class AddressVO {
    private String zipcode;   //우편번호
    private String addr1;   //주소
    private String addr2;   //상세주소
}