package shop.jpashop.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String header = request.getHeader("x-requested-with");

        if ("XMLHttpRequest".equals(header)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"인증된 회원이 아닙니다");
        } else {
            response.sendRedirect("/members/login");
        }
    }
}
