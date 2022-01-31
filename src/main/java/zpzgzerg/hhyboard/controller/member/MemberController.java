package zpzgzerg.hhyboard.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zpzgzerg.hhyboard.service.comm.Paging;
import zpzgzerg.hhyboard.dto.member.MemberQueryDto;
import zpzgzerg.hhyboard.dto.member.MemberSearchDto;
import zpzgzerg.hhyboard.entity.Member;
import zpzgzerg.hhyboard.dto.SaveCheck;
import zpzgzerg.hhyboard.dto.UpdateCheck;
import zpzgzerg.hhyboard.dto.member.MemberForm;
import zpzgzerg.hhyboard.dto.mapper.MemberMapper;
import zpzgzerg.hhyboard.service.member.MemberService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final Paging paging;

    /**
     * 회원 목록
     */
    @RequestMapping("/members")
    public String list(@Validated @ModelAttribute("search") MemberSearchDto memberSearchDto, Model model, Pageable pageable, BindingResult result) {

        if (result.hasErrors()) {
            log.error("Search Valid Error : {}", result.getFieldError());
            return "member/memberList";
        }

        Page<MemberQueryDto> members = memberService.findMembers(memberSearchDto, pageable);

        model.addAttribute("members", members.getContent());
        model.addAttribute("search", memberSearchDto);

        // 페이징 로직 처리
        model.addAttribute("paging", paging.process(members.getNumber(), pageable.getPageSize(), members.getTotalPages(), members.getTotalElements()));

        return "member/memberList";
    }

    /**
     * 회원 상세
     */
    @GetMapping("/members/{id}/detail")
    public String detail(@PathVariable long id, Model model) {

        Optional<Member> member = memberService.findMember(id);
        model.addAttribute("member", member.get());

        return "member/memberDetail";
    }

    /**
     * 회원 등록 폼
     */
    @GetMapping("/members/save")
    public String saveMemberForm(Model model) {
        model.addAttribute("form", new MemberForm());
        return "member/saveMemberForm";
    }

    /**
     * 회원 등록 처리
     */
    @PostMapping("/members/save")
    public String saveMember(@Validated(SaveCheck.class) @ModelAttribute("form") MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            log.error("Save Valid Error : {}", result.getFieldError());
            return "member/saveMemberForm";
        }

        log.info("Request Form  = {}", form);
        Member member = memberMapper.formToMember(form);
        log.info("convert Member  = {}", member);

        memberService.saveMember(member);

        return "redirect:/members";
    }

    /**
     * 회원 수정 폼
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/members/{id}/edit")
    public String editMemberForm(@PathVariable long id, Model model) {

        Optional<Member> member = memberService.findMember(id);
        log.info("select Entity  = {}", member.get());
        MemberForm memberForm = memberMapper.memberToForm(member.get());
        log.info("convert Form  = {}", memberForm);

        model.addAttribute("form", memberForm);

        return "member/editMemberForm";

    }

    /**
     * 회원 수정 처리
     */
    @PostMapping("/members/{id}/edit")
    public String editMember(@PathVariable long id, @Validated(UpdateCheck.class) @ModelAttribute("form") MemberForm form, BindingResult result) {

        if(result.hasErrors()) {
            log.error("Edit Valid Error : {}", result.getFieldError());
            return "member/editMemberForm";
        }

        memberService.editMember(form);

        return "redirect:/members/"+form.getMemberId()+"/detail";
    }
}
