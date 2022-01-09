package zpzgzerg.hhyboard.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardSearchDto {
    private String title;
    private String contents;
    private String userName;
}
