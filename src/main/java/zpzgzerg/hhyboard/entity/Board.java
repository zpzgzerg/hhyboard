package zpzgzerg.hhyboard.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "title", "content", "regDt", "modDt", "useYn", "readCnt"})
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String content;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDt;
    @LastModifiedDate
    private LocalDateTime modDt;
    private boolean useYn;
    private int readCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    public Board(String title, String content, boolean useYn, int readCnt, Member member) {
        this.title = title;
        this.content = content;
        this.useYn = useYn;
        this.readCnt = readCnt;
        this.member = member;
    }
}
