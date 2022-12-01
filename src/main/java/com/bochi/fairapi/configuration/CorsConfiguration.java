package com.bochi.fairapi.configuration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.springframework.http.HttpMethod.OPTIONS;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsConfiguration implements Filter {

    private static final String ORIGIN_NAME = "Access-Control-Allow-Origin";
    private static final String EXPOSE = "Access-Control-Expose-Headers";
    private static final String METHODS_NAME = "Access-Control-Allow-Methods";
    private static final String HEADERS_NAME = "Access-Control-Allow-Headers";
    private static final String MAX_AGE_NAME = "Access-Control-Max-Age";

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader(ORIGIN_NAME, "*");
        response.setHeader(METHODS_NAME, "POST, GET, PUT, PATCH, OPTIONS, DELETE");
        response.setHeader(MAX_AGE_NAME, "3600");
        response.setHeader(HEADERS_NAME, "x-requested-with, authorization, Content-Type, Authorization, Token");
        response.setHeader(EXPOSE, "Content-Disposition");

        if (OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
