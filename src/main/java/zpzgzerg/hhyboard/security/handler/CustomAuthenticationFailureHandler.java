package zpzgzerg.hhyboard.security.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = "아이디 혹은 비밀번호가 틀립니다.";

        if(exception instanceof BadCredentialsException) {
            errorMessage = "아이디 혹은 비밀번호가 틀립니다.";
        }

        setDefaultFailureUrl("/login");
        setUseForward(true);

        request.setAttribute("error", true);
        request.setAttribute("errorMessage", errorMessage);

        super.onAuthenticationFailure(request, response, exception);
    }
}
