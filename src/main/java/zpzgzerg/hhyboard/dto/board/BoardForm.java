package zpzgzerg.hhyboard.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import zpzgzerg.hhyboard.entity.Member;
import zpzgzerg.hhyboard.dto.SaveCheck;
import zpzgzerg.hhyboard.dto.UpdateCheck;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardForm {

    @NotNull(groups = {UpdateCheck.class})
    private Long boardId;

    @NotBlank(message = "제목을 입력해주세요.", groups = {SaveCheck.class, UpdateCheck.class})
    private String title;
    @NotBlank(message = "내용을 입력해주세요.", groups = {SaveCheck.class, UpdateCheck.class})
    private String content;

    @NotNull(message = "회원정보가 없습니다.", groups = {UpdateCheck.class})
    private Member member;
}
