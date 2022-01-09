package zpzgzerg.hhyboard.dto.board;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import zpzgzerg.hhyboard.entity.RoleType;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardQueryDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime regDt;
    private LocalDateTime modDt;
    private boolean useYn;
    private int readCnt;

    private String userId;
    private String userName;
    private RoleType roleType;

    @QueryProjection
    public BoardQueryDto(Long id, String title, String content, LocalDateTime regDt, LocalDateTime modDt, boolean useYn, int readCnt, String userId, String userName, RoleType roleType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regDt = regDt;
        this.modDt = modDt;
        this.useYn = useYn;
        this.readCnt = readCnt;
        this.userId = userId;
        this.userName = userName;
        this.roleType = roleType;
    }
}
