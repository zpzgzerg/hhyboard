package zpzgzerg.hhyboard.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zpzgzerg.hhyboard.dto.board.BoardQueryDto;
import zpzgzerg.hhyboard.dto.board.BoardSearchDto;

public interface BoardRepositoryCustom {
    Page<BoardQueryDto> findBoards(BoardSearchDto boardSearchDto, Pageable pageable);
}
