package zpzgzerg.hhyboard.repository.board;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import zpzgzerg.hhyboard.dto.board.BoardQueryDto;
import zpzgzerg.hhyboard.dto.board.BoardSearchDto;
import zpzgzerg.hhyboard.dto.board.QBoardQueryDto;
import zpzgzerg.hhyboard.entity.Board;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static zpzgzerg.hhyboard.entity.QBoard.board;
import static zpzgzerg.hhyboard.entity.QMember.member;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<BoardQueryDto> findBoards(BoardSearchDto boardSearchDto, Pageable pageable) {

        List<BoardQueryDto> content = queryFactory
                .select(new QBoardQueryDto(
                        board.id.as("boardId"),
                        board.title,
                        board.content,
                        board.regDt,
                        board.modDt,
                        board.useYn,
                        board.readCnt,
                        member.userId,
                        member.userName,
                        member.roleType))
                .from(board)
                .leftJoin(board.member, member)
                .where(titleLike(boardSearchDto.getTitle()),
                        contentLike(boardSearchDto.getContents()))
                .orderBy(board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Board> countQuery = queryFactory.
                selectFrom(board)
                .where(titleLike(boardSearchDto.getTitle()),
                        contentLike(boardSearchDto.getContents()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);

    }

    private BooleanExpression titleLike(String title) {
        return hasText(title) ? board.title.like(title) : null;
    }

    private BooleanExpression contentLike(String content) {
        return hasText(content) ? board.content.like(content) : null;
    }
}
