package com.work.converter.config;

import com.work.converter.service.LoggingServiceImpl;
import com.work.converter.service.NumberToWordsServiceImpl;
import org.springframework.context.annotation.Bean;
import com.work.converter.service.NumberToWordsService;
import com.work.converter.service.LoggingService;
import org.springframework.context.annotation.Configuration;

/**
 * The Class AppConfig.
 *
 * @author ajuar
 */
@Configuration
public class AppConfig {

    /**
     * Numbers to words service.
     *
     * @return NumberToWordsService
     */
    @Bean
    NumberToWordsService numbersToWordsService() {
        return new NumberToWordsServiceImpl();
    }

    /**
     * Loggin Service.
     *
     * @return LoggingService
     */
    @Bean
    LoggingService loggingService() {
        return new LoggingServiceImpl();
    }
}
