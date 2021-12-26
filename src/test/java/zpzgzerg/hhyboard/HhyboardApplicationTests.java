package zpzgzerg.hhyboard;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zpzgzerg.hhyboard.entity.Member;
import zpzgzerg.hhyboard.entity.QMember;
import zpzgzerg.hhyboard.entity.RoleType;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class HhyboardApplicationTests {

    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {

        Member member = new Member(RoleType.ROLE_ADMIN, "test", "password", "테스트", 0);
        em.persist(member);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember m = QMember.member;

        Member result = query
                .selectFrom(m)
                .where(m.userId.eq("test"))
                .fetchOne();

        Assertions.assertThat(result).isEqualTo(member);
    }

}
