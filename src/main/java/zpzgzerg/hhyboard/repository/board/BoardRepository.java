package zpzgzerg.hhyboard.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import zpzgzerg.hhyboard.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
