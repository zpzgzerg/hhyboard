package zpzgzerg.hhyboard.dto.mapper;

import org.mapstruct.Mapper;
import zpzgzerg.hhyboard.entity.Board;
import zpzgzerg.hhyboard.dto.board.BoardForm;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    Board formToBoard(BoardForm form);
    BoardForm boardToForm(Board board);

}
