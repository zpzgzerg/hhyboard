package zpzgzerg.hhyboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zpzgzerg.hhyboard.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserId(String userId);
}
