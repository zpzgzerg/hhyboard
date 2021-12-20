package zpzgzerg.hhyboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import zpzgzerg.hhyboard.entity.Member;
import zpzgzerg.hhyboard.form.member.MemberForm;
import zpzgzerg.hhyboard.mapper.MemberMapper;
import zpzgzerg.hhyboard.service.MemberService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final Paging paging;

    /*@PostConstruct
    public void init() {
        memberService.init(passwordEncoder.encode("asdfqwer12"));
    }*/

    @GetMapping("/members")
    public String list(Model model, Pageable pageable) {

        Page<Member> members = memberService.findMembers(pageable);

        model.addAttribute("members", members.getContent());

        // 페이징 로직 처리
        model.addAttribute("paging", paging.process(members.getNumber(), members.getTotalPages(), members.getTotalElements()));

        return "member/memberList";
    }

    @GetMapping("/member/{member_id}/edit")
    public String updateMemberForm(@PathVariable Long member_id, Model model) {
        Optional<Member> member = memberService.findMember(member_id);
        model.addAttribute("member", member.get());
        return "member/updateMemberForm";
    }

    @GetMapping("/member/save")
    public String saveMemberForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "member/saveMemberForm";
    }

    @PostMapping("/member/save")
    public String saveMember(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "member/saveMemberForm";
        }

        log.info("Request Form  = {}", form);
        Member member = memberMapper.formToMember(form);
        log.info("convert Entity  = {}", member);

        memberService.saveMember(member);

        return "redirect:/members";
    }

    @GetMapping("/member/detail/{id}")
    public String detail(@PathVariable long id, Model model) {

        Optional<Member> member = memberService.findMember(id);
        model.addAttribute("member", member.get());

        return "member/memberDetail";
    }

    @GetMapping("/member/edit/{id}")
    public String editMemberForm(@PathVariable long id, Model model) {

        Optional<Member> member = memberService.findMember(id);
        log.info("select Entity  = {}", member.get());
        MemberForm memberForm = memberMapper.memberToForm(member.get());
        log.info("convert Form  = {}", memberForm);

        model.addAttribute("memberForm", memberForm);

        return "member/editMemberForm";

    }

    @PostMapping("/member/edit/{id}")
    public String editMember(@PathVariable long id, @Valid MemberForm form, BindingResult result) {

        if(result.hasErrors()) {
            return "member/editMemberForm";
        }

        memberService.editMember(id, form);

        return "redirect:/member/edit/"+id;
    }
}
