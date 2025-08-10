package com.work.converter.filter;

import com.work.converter.dto.RequestResponseDataDTO;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import com.work.converter.service.LoggingService;
import org.slf4j.MDC;

/**
 *
 * @author ajuar
 */
@Component
@Slf4j
@Order(1)
public class LoggingFilter implements Filter {

    /**
     * The executor.
     */
    private final Executor executor = Executors.newFixedThreadPool(5);
    /**
     * The loggingService.
     */
    private final LoggingService loggingService;

    public LoggingFilter(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ContentCachingRequestWrapper requestWrapper
                = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper responseWrapper
                = new ContentCachingResponseWrapper((HttpServletResponse) response);

        try {

            chain.doFilter(requestWrapper, responseWrapper);

        } finally {
            RequestResponseDataDTO data = createData(requestWrapper, responseWrapper);

            printLog(data);

            responseWrapper.copyBodyToResponse();
        }
    }

    private void printLog(RequestResponseDataDTO data) {

        Map<String, String> contextMap = MDC.getCopyOfContextMap();

        executor.execute(() -> {
            MDC.setContextMap(contextMap);
            try {
                this.loggingService.printLogReqRes(data);
            } finally {
                MDC.clear();
            }
        });
    }

    private RequestResponseDataDTO createData(ContentCachingRequestWrapper requestWrapper,
            ContentCachingResponseWrapper responseWrapper) throws UnsupportedEncodingException {

        RequestResponseDataDTO data = new RequestResponseDataDTO(
                requestWrapper.getMethod(),
                requestWrapper.getRequestURI(),
                requestWrapper.getQueryString(),
                getHeaders(requestWrapper),
                new String(requestWrapper.getContentAsByteArray(),
                        requestWrapper.getCharacterEncoding()),
                responseWrapper.getStatus(),
                getHeaders(responseWrapper),
                new String(responseWrapper.getContentAsByteArray(),
                        responseWrapper.getCharacterEncoding()),
                requestWrapper.getRemoteAddr()
        );
        return data;
    }

    /**
     * Gets the headers.
     *
     * @param res the res
     * @return the headers
     */
    private Map<String, String> getHeaders(ContentCachingResponseWrapper res) {
        return res.getHeaderNames().stream().distinct()
                .collect(Collectors.toMap(k -> k, res::getHeader));
    }

    /**
     * Gets the headers.
     *
     * @param req the req
     * @return the headers
     */
    private Map<String, String> getHeaders(ContentCachingRequestWrapper req) {
        return Collections.list(
                req.getHeaderNames()).stream()
                .collect(Collectors.toMap(k -> k, req::getHeader));
    }

}
