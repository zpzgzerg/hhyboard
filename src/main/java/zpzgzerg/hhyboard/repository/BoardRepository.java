package zpzgzerg.hhyboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zpzgzerg.hhyboard.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
