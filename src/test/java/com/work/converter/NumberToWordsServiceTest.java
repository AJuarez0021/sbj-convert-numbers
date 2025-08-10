package com.work.converter;

import com.work.converter.service.NumberToWordsService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NumberToWordsServiceTest {

    @Autowired
    private NumberToWordsService service;

    @Test
    void testConvertZero() {
        assertEquals("cero", service.convertNumberToWords(0));
    }

    @Test
    void testConvertSimpleNumbers() {
        assertEquals("uno", service.convertNumberToWords(1));
        assertEquals("cinco", service.convertNumberToWords(5));
        assertEquals("diez", service.convertNumberToWords(10));
        assertEquals("quince", service.convertNumberToWords(15));
    }

    @Test
    void testConvertTens() {
        assertEquals("veinte", service.convertNumberToWords(20));
        assertEquals("veintiuno", service.convertNumberToWords(21));
        assertEquals("treinta y cinco", service.convertNumberToWords(35));
        assertEquals("noventa y nueve", service.convertNumberToWords(99));
    }

    @Test
    void testConvertHundreds() {
        assertEquals("cien", service.convertNumberToWords(100));
        assertEquals("ciento uno", service.convertNumberToWords(101));
        assertEquals("doscientos cincuenta", service.convertNumberToWords(250));
        assertEquals("novecientos noventa y nueve", service.convertNumberToWords(999));
    }

    @Test
    void testConvertThousands() {
        assertEquals("mil", service.convertNumberToWords(1000));
        assertEquals("dos mil quinientos", service.convertNumberToWords(2500));
        assertEquals("quince mil", service.convertNumberToWords(15000));
    }

    @Test
    void testConvertMillions() {
        assertEquals("un millon", service.convertNumberToWords(1000000));
        assertEquals("cinco millones", service.convertNumberToWords(5000000));
        assertEquals("un millon quinientos mil", service.convertNumberToWords(1500000));
    }

    @Test
    void testNegativeNumbers() {
        assertEquals("menos uno", service.convertNumberToWords(-1));
        assertEquals("menos cien", service.convertNumberToWords(-100));
    }
}
