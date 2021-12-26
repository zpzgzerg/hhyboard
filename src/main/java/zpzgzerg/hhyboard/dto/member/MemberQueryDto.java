package zpzgzerg.hhyboard.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import zpzgzerg.hhyboard.entity.RoleType;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MemberQueryDto {

    private Long id;
    private RoleType roleType;
    private String userId;
    private String userName;
    private LocalDateTime regDt;
    private int point;

    @QueryProjection
    public MemberQueryDto(Long id, RoleType roleType, String userId, String userName, LocalDateTime regDt, int point) {
        this.id = id;
        this.roleType = roleType;
        this.userId = userId;
        this.userName = userName;
        this.regDt = regDt;
        this.point = point;
    }
}
