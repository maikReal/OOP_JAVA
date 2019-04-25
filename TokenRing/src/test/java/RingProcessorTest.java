import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RingProcessorTest {

    private static RingProcessor ring;

    @BeforeAll
    static void createClass() throws IOException {

        ring = new RingProcessor(getRandomNum(), getRandomNum(), "testLogInfo.log");

    }

    private static int getRandomNum() {
        return (int) (Math.random());
    }

    @Test
    void testCreatingRingProcessing() throws IOException {

        var randomNumberNodes = getRandomNum();
        var randomNumberData = getRandomNum();

        var ring = new RingProcessor(randomNumberNodes, randomNumberData, "testLogInfo.log");

        assertEquals(ring.getNodesAmount(), randomNumberNodes);
        assertEquals(ring.getDataAmount(), randomNumberData);

    }

    @Test
    void testInitializing() {

        assertEquals(ring.getNodes().size(), ring.getNodesAmount());

    }

}