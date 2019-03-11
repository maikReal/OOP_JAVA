package test.java.company.evo;


import main.java.company.evo.VeryLong.StringParcer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VeryLongTest {

    static private StringParcer parcer;

    @BeforeAll
    private static void setParams() {

        parcer = new StringParcer();

    }

    @Test
    void testingAdd() {

        assertEquals(parcer.getResult("10 * 5++ == 51"), "false");
        assertEquals(parcer.getResult("123448134 += 4"), "123448138");
        assertEquals(parcer.getResult("13423243345678908765 >= 233456789"), "true");
        assertEquals(parcer.getResult("2347205 * 123 + 233"), "288706448");
        assertEquals(parcer.getResult("5++"), "6");
        assertEquals(parcer.getResult("123456789--"), "123456788");
        assertEquals(parcer.getResult("4 + 1234567890"), "1234567894");
        assertEquals(parcer.getResult("4 - 1234567894"), "-1234567890");
        assertEquals(parcer.getResult("1234567894 - 4"), "1234567890");
        assertEquals(parcer.getResult("123456789 * 2"), "246913578");

        assertEquals(parcer.getResult("123456789 *= 2"), "246913578");
        assertEquals(parcer.getResult("2 *= 123456789"), "246913578");
        assertEquals(parcer.getResult("1234567894 -= 4"), "1234567890");
        assertEquals(parcer.getResult("4 -= 1234567894"), "-1234567890");
        assertEquals(parcer.getResult("1234567890 += 4"), "1234567894");
        assertEquals(parcer.getResult("4 += 1234567890"), "1234567894");

        assertEquals(parcer.getResult("4 > 1234567890"), "false");
        assertEquals(parcer.getResult("123456789 > 4"), "true");
        assertEquals(parcer.getResult("123456789 == 123456789"), "true");
        assertEquals(parcer.getResult("123456789 >= 123456789"), "true");
        assertEquals(parcer.getResult("123456789 <= 123456789"), "true");
        assertEquals(parcer.getResult("123456789 < 4"), "false");
        assertEquals(parcer.getResult("12345678910 != 1234567432"), "true");


    }

    @Test
    void testDiffSentences() {

        assertEquals(parcer.getResult("123456789 * 2 - 77++"), "246913500");
        assertEquals(parcer.getResult("123456788++ * 2 - 77++"), "246913500");
        assertEquals(parcer.getResult("123456790-- * 2 - 79--"), "246913500");
        assertEquals(parcer.getResult("123456790-- * 2 - 77++"), "246913500");
        assertEquals(parcer.getResult("79--"), "78");
        assertEquals(parcer.getResult("123456790--"), "123456789");
        assertEquals(parcer.getResult("123456790-- * 1++"), "246913578");

    }

}