package zpzgzerg.hhyboard.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zpzgzerg.hhyboard.dto.member.MemberQueryDto;
import zpzgzerg.hhyboard.dto.member.MemberSearchDto;
import zpzgzerg.hhyboard.entity.Member;
import zpzgzerg.hhyboard.entity.RoleType;
import zpzgzerg.hhyboard.form.member.MemberForm;
import zpzgzerg.hhyboard.repository.member.MemberRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void init(String password) {
        for (int i = 1; i <= 2; i++) {
            memberRepository.save(new Member(RoleType.ROLE_ADMIN, "userId" + i, password, "userName" + i, 0));
        }
    }

    /**
     * 회원 목록 조회
     * @return
     */
    public Page<Member> findMembers(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    /**
     * 회원 목록 검색 (임시)
     * @param memberSearchDto
     * @param pageable
     * @return
     */
    public Page<MemberQueryDto> findMembersSearch(MemberSearchDto memberSearchDto, Pageable pageable) {
        return memberRepository.search(memberSearchDto, pageable);
    }

    /**
     * 회원 단건 조회
     * @param id
     * @return
     */
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    /**
     * 회원 가입
     * @param member
     * @return
     */
    @Transactional
    public Long saveMember(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 회원 수정
     * @param form
     */
    @Transactional
    public void editMember(MemberForm form) {
        Member member = memberRepository.findById(form.getMemberId()).get();
        member.updateMember(form);
    }
}