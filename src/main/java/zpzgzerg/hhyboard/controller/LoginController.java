package zpzgzerg.hhyboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest req) {

        model.addAttribute("error", req.getAttribute("error"));
        model.addAttribute("errorMessage", req.getAttribute("errorMessage"));

        log.info("getRequestURI = {}", req.getRequestURI());

        return "login/login";
    }

    @GetMapping("/denied")
    public String denied() {
        return "login/denied";
    }
}
