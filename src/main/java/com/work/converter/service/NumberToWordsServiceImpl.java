package com.work.converter.service;

import static com.work.converter.util.Constants.HUNDREDS;
import static com.work.converter.util.Constants.TENS;
import static com.work.converter.util.Constants.SCALES;
import static com.work.converter.util.Constants.SPECIALS;
import static com.work.converter.util.Constants.UNITS;

/**
 * The Class NumberToWordsServiceImpl.
 *
 * @author ajuar
 */
public class NumberToWordsServiceImpl implements NumberToWordsService {

    /**
     * Convierte un número long a su representación en letras.
     *
     * @param number el número a convertir (0 a 999,999,999,999,999)
     * @return la representación en letras del número
     */
    @Override
    public String convertNumberToWords(long number) {
        if (number == 0) {
            return "cero";
        }

        if (number < 0) {
            return "menos " + convertNumberToWords(-number);
        }

        var result = new StringBuilder();
        int scale = 0;
        boolean firstGroup = true;

        while (number > 0) {
            int group = (int) (number % 1000);
            number /= 1000;

            if (group != 0) {
                String textGroup = convertGroup(group);

                if (scale > 0) {
                    String[] scaleNames = SCALES.get(scale);
                    if (scaleNames != null) {
                        if ((scale == 3 || scale == 9) && group == 1) {
                            textGroup = scaleNames[0];
                        } else if (scale == 6 && group == 1) {
                            textGroup = "un millon";
                        } else {
                            String escalaTexto = (group == 1)
                                    ? scaleNames[0]
                                    : scaleNames[1];
                            textGroup += " " + escalaTexto;
                        }
                    }
                }

                if (!firstGroup) {
                    result.insert(0, " ");
                }
                result.insert(0, textGroup);
                firstGroup = false;
            }

            scale += 3;
        }

        return result.toString();
    }

    /**
     * Convierte un grupo de tres dígitos (0-999) a letras.
     *
     * @param number the number
     * @return the string
     */
    private static String convertGroup(int number) {
        if (number == 0) {
            return "";
        }

        var result = new StringBuilder();

        // Centenas
        int hundreds = number / 100;
        int remainder = number % 100;

        if (hundreds > 0) {
            if (hundreds == 1 && remainder == 0) {
                result.append("cien");
            } else {
                result.append(HUNDREDS[hundreds]);
            }
        }

        // Decenas y unidades
        if (remainder > 0) {
            if (hundreds > 0) {
                result.append(" ");
            }

            if (remainder < 10) {
                result.append(UNITS[remainder]);
            } else if (remainder < 20) {
                if (remainder == 10) {
                    result.append("diez");
                } else {
                    result.append(SPECIALS[remainder - 10]);
                }
            } else {
                int tens = remainder / 10;
                int units = remainder % 10;

                if (tens == 2 && units > 0) {
                    result.append("veinti").append(UNITS[units]);
                } else {
                    result.append(TENS[tens]);
                    if (units > 0) {
                        result.append(" y ").append(UNITS[units]);
                    }
                }
            }
        }

        return result.toString();
    }

    /**
     * Convierte un número int a su representación en letras.
     *
     * @param number the number
     * @return la representación en letras del número
     */
    @Override
    public String convertNumberToWords(int number) {
        return convertNumberToWords((long) number);
    }
}
