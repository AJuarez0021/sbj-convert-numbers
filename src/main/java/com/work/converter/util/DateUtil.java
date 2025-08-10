package com.work.converter.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Class DateUtil.
 *
 * @author ajuar
 */
public final class DateUtil {

    /** The Constant PATTERN_DATE. */
    public static final String PATTERN_DATE = "yyyy-MM-dd HH:mm:ss";

    /** The Constant TIME_ZONE. */
    public static final String TIME_ZONE = "America/Mexico_City";

    /**
     * Instantiates a new date util.
     */
    private DateUtil() {

    }

    /**
     * Gets the format.
     *
     * @param dateTime the date time
     * @return the format
     */
    public static String getFormat(LocalDateTime dateTime) {
        return getFormat(PATTERN_DATE, dateTime);
    }

    /**
     * Gets the format.
     *
     * @param pattern the pattern
     * @param dateTime the date time
     * @return the format
     */
    public static String getFormat(String pattern, LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }
}
