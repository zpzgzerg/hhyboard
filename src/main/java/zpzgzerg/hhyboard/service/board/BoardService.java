package zpzgzerg.hhyboard.service.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zpzgzerg.hhyboard.dto.board.BoardQueryDto;
import zpzgzerg.hhyboard.dto.board.BoardSearchDto;
import zpzgzerg.hhyboard.entity.Board;
import zpzgzerg.hhyboard.repository.board.BoardRepository;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시판 목록
     */
    public Page<BoardQueryDto> findBoards(BoardSearchDto boardSearchDto, Pageable pageable) {
        return boardRepository.findBoards(boardSearchDto, pageable);
    }

    /**
     * 게시판 등록
     */
    @Transactional
    public Long saveBoard(Board board) {
        boardRepository.save(board);
        return board.getId();
    }
}


