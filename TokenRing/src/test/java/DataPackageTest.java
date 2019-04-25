import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataPackageTest {

    private static int getRandomNum() {
        return (int) (Math.random());
    }

    @Test
    void getDestinationNode() {

        var randomDestination = getRandomNum();
        var randomDataString = getRandomData();

        var data = new DataPackage(randomDestination, randomDataString);

        assertEquals(data.getDestinationNode(), randomDestination);

    }

    @Test
    void getData() {

        var randomDestination = getRandomNum();
        var randomDataString = getRandomData();

        var data = new DataPackage(randomDestination, randomDataString);

        assertEquals(data.getData(), randomDataString);

    }

    private String getRandomData() {

        byte[] array = new byte[7];
        new Random().nextBytes(array);

        return new String(array, Charset.forName("UTF-8"));

    }
}