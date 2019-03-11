package test.java.company.evo;

import main.java.company.evo.GeometricDistances.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DistanceTest {

    private static Distance dist;

    @Test
    void pointSet1() throws ParallelLinesException {

        dist = new Distance(new Point(1, 2), new Point(7, 1),
                new Point(5, 6), new Point(8, 2));

        assertEquals(dist.distFromAtoC(), 5.6568542495);
        assertEquals(dist.distFromAtoCDsection(), 5.6000000000); // написать проверку, что точка лежит на отрезке
        assertEquals(dist.distFromAtoCDstraight(), 5.6000000000); // написать проверку, что точка лежит на прямой
        assertEquals(dist.distFromAtoCDray(), 5.6000000000); // возможно нельзя будет опустить высоту
        assertEquals(dist.distFromСtoABsection(), 4.6031716446);
        assertEquals(dist.distFromABtoCDsection(), 1.4142135624);
        assertEquals(dist.distFromABtoCDsectionStraight(), 1.4000000000);
        assertEquals(dist.distFromABtoCDsectionRay(), 1.4000000000);
        assertEquals(dist.distFromABtoCray(), 4.6031716446);
        assertEquals(dist.distABtoCDraySection(), 1.1507929111);
        assertEquals(dist.distABtoCDrayStraight(), 0.0000000000); // no
        assertEquals(dist.distABtoCDrayRay(), 0.0000000000);
        assertEquals(dist.distABtoCstraight(), 4.6031716446);
        assertEquals(dist.distABtoCDstraightSection(), 1.1507929111);
        assertEquals(dist.distABtoCDstraightStraight(), 0.0000000000);
        assertEquals(dist.distABtoCDstraigtRay(), 0.0000000000);
    }


    @Test
    void pointSet2() throws ParallelLinesException {

        dist = new Distance(new Point(0, 0), new Point(0, 10),
                new Point(-1, 9), new Point(1, 11));

        assertEquals(dist.distFromAtoC(), 9.0553851381);
        assertEquals(dist.distFromAtoCDsection(), 9.0553851381);
        assertEquals(dist.distFromAtoCDstraight(), 7.0710678119);
        assertEquals(dist.distFromAtoCDray(), 9.0553851381); // видимо ошибка в ЯК
        assertEquals(dist.distFromСtoABsection(), 1.0000000000);
        assertEquals(dist.distFromABtoCDsection(), 0.0000000000);
        assertEquals(dist.distFromABtoCDsectionStraight(), 0.0000000000);
        assertEquals(dist.distFromABtoCDsectionRay(), 0.0000000000);
        assertEquals(dist.distFromABtoCray(), 1.0000000000);
        assertEquals(dist.distABtoCDraySection(), 0.0000000000); // no
        assertEquals(dist.distABtoCDrayStraight(), 0.0000000000);
        assertEquals(dist.distABtoCDrayRay(), 0.0000000000);
        assertEquals(dist.distABtoCstraight(), 1.0000000000);
        assertEquals(dist.distABtoCDstraightSection(), 0.0000000000);
        assertEquals(dist.distABtoCDstraightStraight(), 0.0000000000);
        assertEquals(dist.distABtoCDstraigtRay(), 0.0000000000);


    }

    @Test
    void typeAngle() {
        assertEquals(Utils.typeAngle(new Point(1, 2), new Point(5, 6),
                new Point(8, 2)), "Острый");

        assertEquals(Utils.typeAngle(new Point(0, 0), new Point(0, 1),
                new Point(1, 1)), "Прямой");

        assertEquals(Utils.typeAngle(new Point(-5, 0), new Point(0, 1),
                new Point(1, 1)), "Тупой");

        assertEquals(Utils.typeAngle(new Point(-5, -10), new Point(-1, 9),
                new Point(1, 11)), "Тупой");

        assertEquals(Utils.typeAngle(new Point(0, 0), new Point(-1, 9),
                new Point(1, 11)), "Тупой");


    }

    @Test
    void isParallel() {

        assertTrue(Utils.isParallel(new Section(new Point(0, 0), new Point(5, 0)),
                new Section(new Point(-5, 10), new Point(10, 10))));

        assertTrue(Utils.isParallel(new Section(new Point(0, 0), new Point(0, 10)),
                new Section(new Point(-5, 5), new Point(-5, 40))));
    }
}