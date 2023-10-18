package io.migni.central.data.server.application.global.web.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class LogFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    protected void doFilterInternal(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final FilterChain filterChain
    ) throws ServletException, IOException {

        final String requestSequence = request.getHeader("Global-Request-Sequence");

        MDC.put("requestSequence", requestSequence);
        this.logger.info("[Request] Sequence - {}", requestSequence);

        doFilter(request, response, filterChain);

    }
}
