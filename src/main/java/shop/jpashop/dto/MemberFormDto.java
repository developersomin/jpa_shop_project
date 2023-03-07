package shop.jpashop.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberFormDto {

    @NotBlank(message = "이름을 입력하시오")
    private String name;

    @NotEmpty(message = "비밀번호 입력하시오")
    @Length(min=8,max=16,message = "비밀번호는 8~16 글자롤 입력해주세요")
    private String password;

    @NotEmpty(message = "이메일을 입력하시오")
    private String email;

    @NotEmpty(message = "주소를 입력하시오")
    private String address;
}
