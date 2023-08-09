package kr.or.ddit.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

//자바빈 클래스
@Data
public class AddressVO {
    @NotBlank(message = "필수항목입니다.")
    private String zonecode;
    @NotBlank(message = "필수항목입니다.")
    private String address;
    private String buildingName;
}