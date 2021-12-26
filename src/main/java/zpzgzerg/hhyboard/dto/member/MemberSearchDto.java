package zpzgzerg.hhyboard.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberSearchDto {
    private String roleType;
    private String userId;
    private String userName;
}
