package zpzgzerg.hhyboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zpzgzerg.hhyboard.entity.Member;
import zpzgzerg.hhyboard.entity.RoleType;
import zpzgzerg.hhyboard.form.member.MemberForm;
import zpzgzerg.hhyboard.mapper.MemberMapper;
import zpzgzerg.hhyboard.repository.MemberRepository;

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
     * @param id
     * @param form
     */
    @Transactional
    public void editMember(Long id, MemberForm form) {
        Member member = memberRepository.findById(id).get();
        member.updateMember(form);
    }
}
