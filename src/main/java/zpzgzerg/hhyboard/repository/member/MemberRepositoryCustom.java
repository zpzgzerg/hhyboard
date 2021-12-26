package zpzgzerg.hhyboard.repository.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zpzgzerg.hhyboard.dto.member.MemberQueryDto;
import zpzgzerg.hhyboard.dto.member.MemberSearchDto;

public interface MemberRepositoryCustom {
    Page<MemberQueryDto> search(MemberSearchDto memberSearchDto, Pageable pageable);
}
