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
import zpzgzerg.hhyboard.controller.Paging;
import zpzgzerg.hhyboard.dto.member.MemberQueryDto;
import zpzgzerg.hhyboard.dto.member.MemberSearchDto;
import zpzgzerg.hhyboard.entity.Member;
import zpzgzerg.hhyboard.form.SaveCheck;
import zpzgzerg.hhyboard.form.UpdateCheck;
import zpzgzerg.hhyboard.form.member.MemberForm;
import zpzgzerg.hhyboard.mapper.MemberMapper;
import zpzgzerg.hhyboard.service.member.MemberService;

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

//    @GetMapping("/members")
//    public String list(Model model, Pageable pageable) {
//
//        Page<Member> members = memberService.findMembers(pageable);
//
//        model.addAttribute("members", members.getContent());
//        model.addAttribute("search", new MemberSearchDto());
//
//        // 페이징 로직 처리
//        model.addAttribute("paging", paging.process(members.getNumber(), members.getTotalPages(), members.getTotalElements()));
//
//        return "member/memberList";
//    }

    @RequestMapping("/members")
    public String search(@ModelAttribute("search") MemberSearchDto searchDto, Model model, Pageable pageable, BindingResult result) {

        if (result.hasErrors()) {
            log.error("search vaild error : {}", result.getFieldError());
            return "member/memberList";
        }

        log.error("searchDto : {}", searchDto);

        Page<MemberQueryDto> members = memberService.findMembersSearch(searchDto, pageable);

        model.addAttribute("members", members.getContent());
        model.addAttribute("search", searchDto);

        // 페이징 로직 처리
        model.addAttribute("paging", paging.process(members.getNumber(), members.getTotalPages(), members.getTotalElements()));

        return "member/memberList";
    }

    @GetMapping("/members/{id}/detail")
    public String detail(@PathVariable long id, Model model) {

        Optional<Member> member = memberService.findMember(id);
        model.addAttribute("member", member.get());

        return "member/memberDetail";
    }

    @GetMapping("/members/save")
    public String saveMemberForm(Model model) {
        model.addAttribute("form", new MemberForm());
        return "member/saveMemberForm";
    }

    @PostMapping("/members/save")
    public String saveMember(@Validated(SaveCheck.class) @ModelAttribute("form") MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            log.error("Valid error : {}", result.getFieldError());
            return "member/saveMemberForm";
        }

        log.info("Request Form  = {}", form);
        Member member = memberMapper.formToMember(form);
        log.info("convert Entity  = {}", member);

        memberService.saveMember(member);

        return "redirect:/members";
    }

    @GetMapping("/members/{id}/edit")
    public String editMemberForm(@PathVariable long id, Model model) {

        Optional<Member> member = memberService.findMember(id);
        log.info("select Entity  = {}", member.get());
        MemberForm memberForm = memberMapper.memberToForm(member.get());
        log.info("convert Form  = {}", memberForm);

        model.addAttribute("form", memberForm);

        return "member/editMemberForm";

    }

    @PostMapping("/members/{id}/edit")
    public String editMember(@PathVariable long id, @Validated(UpdateCheck.class) @ModelAttribute("form") MemberForm form, BindingResult result) {

        if(result.hasErrors()) {
            log.error("Valid error : {}", result.getFieldError());
            return "member/editMemberForm";
        }

        memberService.editMember(form);

        return "redirect:/members/"+form.getMemberId()+"/detail";
    }
}
