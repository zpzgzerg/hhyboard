package zpzgzerg.hhyboard.form.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import zpzgzerg.hhyboard.entity.RoleType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class MemberForm {

    @NotNull(message = "권한을 선택해주세요.")
    private RoleType roleType;

    @NotBlank(message = "이름을 입력해주세요.")
    private String userName;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Length(min = 8, max= 20, message = "아이디는 {2}글자 ~ {1}글자 사이의 길이만 허용합니다.")
    private String userId;

    @NotBlank(message = "패스워드를 입력해주세요.")
    @Length(min = 8, max = 20, message = "패스워드는 {2}글자 ~ {1}글자 사이의 길이만 허용합니다.")
    private String password;

    @NotNull
    private int point;
}
