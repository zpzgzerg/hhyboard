package zpzgzerg.hhyboard.form.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import zpzgzerg.hhyboard.entity.RoleType;
import zpzgzerg.hhyboard.form.SaveCheck;
import zpzgzerg.hhyboard.form.UpdateCheck;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class MemberForm {

    @NotNull(groups = {UpdateCheck.class})
    private long memberId;

    @NotNull(message = "권한을 선택해주세요.", groups = {SaveCheck.class, UpdateCheck.class})
    private RoleType roleType;

    @NotBlank(message = "이름을 입력해주세요.", groups = {SaveCheck.class, UpdateCheck.class})
    private String userName;

    @NotBlank(message = "아이디를 입력해주세요.", groups = {SaveCheck.class})
    @Length(min = 8, max= 20, message = "아이디는 {2}글자 ~ {1}글자 사이의 길이만 허용합니다.", groups = {SaveCheck.class})
    private String userId;

    @NotBlank(message = "패스워드를 입력해주세요.", groups = {SaveCheck.class})
    @Length(min = 8, max = 20, message = "패스워드는 {2}글자 ~ {1}글자 사이의 길이만 허용합니다.", groups = {SaveCheck.class})
    private String password;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    private int point;
}
