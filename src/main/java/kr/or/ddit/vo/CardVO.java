package kr.or.ddit.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class CardVO {
    @NotBlank(message = "값을 입력해주세요.")
    private String no;

    //Date type이면 @Future가능 @SimpleDateType 가능
    @Pattern(regexp = "^[0-9]{6}$", message = "연도 4자리와 월 2자리로 입력해주세요.")
    private String validMonth;
}
