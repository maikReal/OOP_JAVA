package test.java.company.evo;

import main.java.company.evo.GeometricDistances.Distance;
import main.java.company.evo.GeometricDistances.Point;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceTest {

    private static Distance dist;
    private static Point point;

    @BeforeAll
    private static void setParams () {

        List<Integer> A = new ArrayList<>();
        A.add(1);
        A.add(2);

        List<Integer> B = new ArrayList<>();
        B.add(7);
        B.add(1);

        List<Integer> C = new ArrayList<>();
        C.add(5);
        C.add(6);

        List<Integer> D = new ArrayList<>();
        D.add(8);
        D.add(2);

        dist = new Distance(new Point(1,2), new Point(7,1),
                            new Point(5,6), new Point(8,2));
    }




    @Test
    void distFromAtoC() {

        assertEquals(dist.distFromAtoC(), 5.656854249492381, 0);

    }

    @Test
    void distFromAtoCDstraight() {

        assertEquals(dist.distFromAtoCDstraight(), 5.6000000000, 0);

    }

    @Test
    void distFromAtoCDsection() {

        assertEquals(dist.distFromAtoCDsection(), 5.6000000000, 0);

    }

    @Test
    void distFromAtoCDray(){
        assertEquals(dist.distFromAtoCDray(), 5.6000000000, 0);
    }

    @Test
    void distFromСtoABsection() {
        assertEquals(dist.distFromСtoABsection(), 4.6031716446, 0);
    }

    @Test
    void distFromABtoCDsection(){
        assertEquals(dist.distFromABtoCDsection(),  1.4142135624, 0);
    }

    @Test
    void distFromABtoCDsectionStraight (){
        assertEquals(dist.distFromABtoCDsectionStraight(),1.4000000000,0);
    }

    @Test
    void distFromABtoCDsectionRay (){

        assertEquals(dist.distFromABtoCDsectionRay(), 1.4000000000, 0);

    }
    @Test
    void distFromABtoCray() {
        assertEquals(dist.distFromABtoCray(), 4.6031716446, 0);
    }

    @Test
    void distABtoCDraySection(){
        assertEquals(dist.distABtoCDraySection(), 1.1507929111, 0);
    }

    @Test
    void distABtoCDrayStraight(){
        assertEquals(dist.distABtoCDrayStraight(), 0.0000000000, 0);
    }

    @Test
    void distABtoCDrayRay(){
        assertEquals(dist.distABtoCDrayRay(), 0.0000000000,0);
    }

    @Test
    void distABtoCstraight() {
        assertEquals(dist.distABtoCstraight(), 4.6031716446, 0);
    }

    @Test
    void distABtoCDstraightSection(){
        assertEquals(dist.distABtoCDstraightSection(), 1.1507929111, 0);
    }

    @Test
    void distABtoCDstraightStraight(){
        assertEquals(dist.distABtoCDstraightStraight(), 0.0000000000, 0);
    }

    @Test
    void distABtoCDstraigtRay(){
        assertEquals(dist.distABtoCDstraigtRay() , 0.0000000000, 0);
    }

}