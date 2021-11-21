package zpzgzerg.hhyboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zpzgzerg.hhyboard.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
