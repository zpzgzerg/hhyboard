package zpzgzerg.hhyboard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDto {

    private String loginId;
    private String password;
    private boolean error;
    private String errorMessage;

}
