package com.work.converter.service;

/**
 * The Interface NumberToWordsService.
 *
 * @author ajuar
 */
public interface NumberToWordsService {

    /**
     * Convert number to words.
     *
     * @param number the number
     * @return the string
     */
    String convertNumberToWords(long number);

    /**
     * Convert number to words.
     *
     * @param number the number
     * @return the string
     */
    String convertNumberToWords(int number);
}
