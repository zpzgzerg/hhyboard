package zpzgzerg.hhyboard.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "roleType", "userId", "password", "userName", "regDt", "point"})
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    private String userId;
    private String password;
    private String userName;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDt;
    private int point;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Point> points = new ArrayList<>();

    public Member(RoleType roleType, String userId, String password, String userName, int point) {
        this.roleType = roleType;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.point = point;
    }

    private void changePassword(String password) {
        this.password = password;
    }
}
