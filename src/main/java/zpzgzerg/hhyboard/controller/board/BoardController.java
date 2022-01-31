package zpzgzerg.hhyboard.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zpzgzerg.hhyboard.service.comm.Paging;
import zpzgzerg.hhyboard.dto.board.BoardQueryDto;
import zpzgzerg.hhyboard.dto.board.BoardSearchDto;
import zpzgzerg.hhyboard.entity.Board;
import zpzgzerg.hhyboard.dto.SaveCheck;
import zpzgzerg.hhyboard.dto.board.BoardForm;
import zpzgzerg.hhyboard.dto.mapper.BoardMapper;
import zpzgzerg.hhyboard.security.config.CustomUser;
import zpzgzerg.hhyboard.service.board.BoardService;
import zpzgzerg.hhyboard.service.member.MemberService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;
    private final MemberService memberService;
    private final Paging paging;

    /**
     * 게시판 목록
     */
    @RequestMapping("/boards")
    public String list(@Validated @ModelAttribute("search") BoardSearchDto boardSearchDto, Model model, Pageable pageable, BindingResult result) {

        if (result.hasErrors()) {
            log.error("Search Valid Error : {}", result.getFieldError());
            return "board/boardList";
        }

        Page<BoardQueryDto> boards = boardService.findBoards(boardSearchDto, pageable);

        model.addAttribute("boards", boards.getContent());
        model.addAttribute("search", boardSearchDto);

        // 페이징 로직처리
        model.addAttribute("paging", paging.process(boards.getNumber(), pageable.getPageSize(), boards.getTotalPages(), boards.getTotalElements()));

        return "board/boardList";
    }

    /**
     * 게시판 등록 폼
     */
    @GetMapping("/boards/save")
    public String saveBoardForm(Model model) {
        model.addAttribute("form", new BoardForm());
        return "board/saveBoardForm";
    }

    /**
     * 게시판 등록 처리
     */
    @PostMapping("/boards/save")
    public String saveBoard(@Validated(SaveCheck.class) @ModelAttribute("form") BoardForm form, BindingResult result, @AuthenticationPrincipal CustomUser customUser) {

        if (result.hasErrors()) {
            log.error("Save Valid Error : {}", result.getFieldError());
            return "board/saveBoardForm";
        }

        form.setMember(customUser.getMember());

        log.info("Request Form : {}", form);
        Board board = boardMapper.formToBoard(form);
        log.info("convert Board : {}", board);

        boardService.saveBoard(board);

        return "redirect:/boards";
    }
}
