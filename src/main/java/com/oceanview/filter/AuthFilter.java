package com.oceanview.filter;

import com.oceanview.util.SessionUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();

        boolean isPublic =
                uri.endsWith("login.jsp") ||
                        uri.contains("/LoginServlet") ||
                        uri.contains("/css/") ||
                        uri.contains("/js/") ||
                        uri.contains("/images/");

        if (isPublic || SessionUtil.isLoggedIn(request)) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp");
        }
    }

    @Override public void init(FilterConfig f) {}
    @Override public void destroy() {}
}
