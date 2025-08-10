package com.work.converter.dto;

import java.util.Map;

/**
 * @author ajuar
 * @param method
 * @param path
 * @param query
 * @param requestHeaders
 * @param requestBody
 * @param status
 * @param responseHeaders
 * @param responseBody
 * @param remoteAddr
 */
public record RequestResponseDataDTO(
        String method,
        String path,
        String query,
        Map<String, String> requestHeaders,
        String requestBody,
        int status,
        Map<String, String> responseHeaders,
        String responseBody,
        String remoteAddr) {

}
