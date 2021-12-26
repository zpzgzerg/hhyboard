package zpzgzerg.hhyboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import zpzgzerg.hhyboard.dto.LoginDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    @GetMapping(value = {"/login", "/loginError"})
    public String login(@ModelAttribute("login") LoginDto loginDto, Model model, HttpServletRequest req) {

        log.info("getRequestURI = {}", req.getRequestURI());
        log.info("loginError = {}", loginDto.isError());
        log.info("loginErrorMessage = {}", loginDto.getErrorMessage());

        return "login/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/login";
    }

    @GetMapping("/denied")
    public String denied(@ModelAttribute("login") LoginDto loginDto, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principal = (String) authentication.getPrincipal();
        loginDto.setLoginId(principal);

        return "login/denied";
    }
}
