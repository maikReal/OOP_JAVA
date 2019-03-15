import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VeryLongTest {

    static private StringParcer parcer = new StringParcer();

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
    void testSubtraction(){

        assertEquals(parcer.getResult("10 - 5"), "5");
        assertEquals(parcer.getResult("12345678915 - 15"), "12345678900");
        assertEquals(parcer.getResult("15 - 12345678915"), "-12345678900");
        assertEquals(parcer.getResult("-1000000000 - -1000000000"), "0");
        assertEquals(parcer.getResult("-1000000000 - -5000000000"), "4000000000");
        assertEquals(parcer.getResult("-1000000000 - 5000000000"), "-6000000000");
        assertEquals(parcer.getResult("1000000000 - -1000000000"), "2000000000");
        assertEquals(parcer.getResult("1000000000 - 1000000000"), "0");

    }

    @Test
    void testAdd(){

        assertEquals(parcer.getResult("10 + 5"), "15");
        assertEquals(parcer.getResult("1000000000 + 1000000000"), "2000000000");
        assertEquals(parcer.getResult("-1000000000 + -1000000001"), "-2000000001");
        assertEquals(parcer.getResult("-1000000000 + 1000000000"), "0");
        assertEquals(parcer.getResult("-1000000000 + 5000000000"), "4000000000");
        assertEquals(parcer.getResult("5000000000 + -1000000000"), "4000000000");
        assertEquals(parcer.getResult("1000000000 + -5000000000"), "-4000000000");

    }

    @Test
    void testMultiplication(){

        assertEquals(parcer.getResult("10 * 5"), "50");
        assertEquals(parcer.getResult("123456789 * 2"), "246913578");
        assertEquals(parcer.getResult("2 * 123456789"), "246913578");
        assertEquals(parcer.getResult("123456789 * -2"), "-246913578");
        assertEquals(parcer.getResult("-123456789 * -2"), "246913578");
        assertEquals(parcer.getResult("-2 * -123456789"), "246913578");

    }

    @Test
    void testIncrementAndDecrement(){

        assertEquals(parcer.getResult("123456789000++"), "123456789001");
        assertEquals(parcer.getResult("123456789000--"), "123456788999");
        assertEquals(parcer.getResult("123456789--"), "123456788");
        assertEquals(parcer.getResult("-123456789++"), "-123456788");
        assertEquals(parcer.getResult("-123456789--"), "-123456790");

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

    @Test
    void testDivision(){
        assertEquals(parcer.getResult("4 / 2"), "2");

    }
}