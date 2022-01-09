package zpzgzerg.hhyboard.repository.member;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import zpzgzerg.hhyboard.dto.member.MemberQueryDto;
import zpzgzerg.hhyboard.dto.member.MemberSearchDto;
import zpzgzerg.hhyboard.dto.member.QMemberQueryDto;
import zpzgzerg.hhyboard.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static zpzgzerg.hhyboard.entity.QMember.member;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<MemberQueryDto> findMembers(MemberSearchDto memberSearchDto, Pageable pageable) {

        List<MemberQueryDto> content = queryFactory
                .select(new QMemberQueryDto(
                        member.id.as("memberId"),
                        member.roleType,
                        member.userId,
                        member.userName,
                        member.regDt,
                        member.point))
                .from(member)
                .where(
                        userNameEq(memberSearchDto.getUserName()),
                        userIdEq(memberSearchDto.getUserId())
//                        roleTypeEq(memberSearchDto.getRoleType())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Member> countQuery = queryFactory
                .select(member)
                .from(member)
                .where(
                        userNameEq(memberSearchDto.getUserName()),
                        userIdEq(memberSearchDto.getUserId())
//                        roleTypeEq(memberSearchDto.getRoleType())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);

    }

    private BooleanExpression userNameEq(String userName) {
        return hasText(userName) ? member.userName.eq(userName) : null;
    }

    private BooleanExpression userIdEq(String userId) {
        return hasText(userId) ? member.userId.eq(userId) : null;
    }

//    private BooleanExpression roleTypeEq(Enum roleType) {
//        return hasText(roleType.name()) ? member.roleType(roleType.name()) : null;
//    }
}
