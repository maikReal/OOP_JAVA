import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NodeTest {

    private static RingProcessor ringProcessor;


    @BeforeAll
    static void createClasses() throws IOException {
        ringProcessor = new RingProcessor(getRandomNum(), getRandomNum(), "testLogInfo.log");
    }

    private static int getRandomNum() {
        return (int) (Math.random());
    }

    @Test
    void testCreatingNode() {

        var randomNodeId = getRandomNum();
        var randomCorId = getRandomNum();

        var node = new Node(randomNodeId, randomCorId, ringProcessor);

        assertEquals(node.getId(), randomNodeId);
        assertEquals(node.getCorId(), randomCorId);

    }

    @Test
    void testSettingData() {

        var node = new Node(getRandomNum(), getRandomNum(), ringProcessor);
        var data = new DataPackage(getRandomNum(), getRandomData());

        node.setData(data);

        assertEquals(node.getBuffer().size(), 1);
    }

    @Test
    void testGettingData() throws InterruptedException {

        var node = new Node(getRandomNum(), getRandomNum(), ringProcessor);
        var data = new DataPackage(getRandomNum(), getRandomData());

        node.setData(data);

        var gettedData = node.getData();

        assertNotNull(gettedData);


    }

    private String getRandomData() {

        byte[] array = new byte[7];
        new Random().nextBytes(array);

        return new String(array, Charset.forName("UTF-8"));

    }
}