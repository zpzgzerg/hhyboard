package zpzgzerg.hhyboard.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import zpzgzerg.hhyboard.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Member findByUserId(String userId);
}
