package com.work.converter.config;

import com.work.converter.filter.TransactionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author ajuar
 */
@Configuration
public class FilterConfig implements WebMvcConfigurer {

    /**
     * The txInterceptor.
     */
    private final TransactionInterceptor txInterceptor;

    public FilterConfig(TransactionInterceptor txInterceptor) {
        this.txInterceptor = txInterceptor;
    }

    /**
     * Adds the interceptors.
     *
     * @param registry the registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(txInterceptor);
    }

}
