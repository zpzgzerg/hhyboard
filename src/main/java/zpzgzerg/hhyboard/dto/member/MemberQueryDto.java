package zpzgzerg.hhyboard.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberQueryDto {

    private Long memberId;
    private String memberType;
    private String userId;
    private String userName;
    private String point;
    private String regDt;
}
