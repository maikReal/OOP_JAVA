import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
class MainTest {


    private String [] types = new String[]{"Banana", "Bread", "Clothes"};
    private Integer[] size  = new Integer[]{10, 50, 100};
    private Tunnel tunnel;
    private static ShipsGenerator generator;

    @Test
    public void testTunnel() throws InterruptedException {
        tunnel = new Tunnel();

        var numberShips = 6;
        var bool = true;
        for (var i=0; i < numberShips; i++){

            bool = tunnel.add(new Ship(getRandomSize(), getRandomType(), tunnel));

        }

        assertTrue(bool);
    }

    @Test
    public void testCountsShips(){
        generator = new ShipsGenerator(10, tunnel);
        assertEquals(generator.getNumberShips(), 10);
    }


    private String getRandomType(){

        int index = ThreadLocalRandom.current().nextInt(3);
        return types[index];

    }

    private Integer getRandomSize(){

        int index = ThreadLocalRandom.current().nextInt(3);
        return size[index];

    }

}