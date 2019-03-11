package main.java.company.evo.GeometricDistances;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParallelLinesException {

        Scanner sc = new Scanner(System.in);

        Point A = new Point(sc.nextDouble(), sc.nextDouble());
        Point B = new Point(sc.nextDouble(), sc.nextDouble());
        Point C = new Point(sc.nextDouble(), sc.nextDouble());
        Point D = new Point(sc.nextDouble(), sc.nextDouble());

        Distance dist = new Distance(A, B, C, D);

        System.out.println(dist.distFromAtoC()); //yes
        System.out.println(dist.distFromAtoCDsection()); //yes

        System.out.println(dist.distFromAtoCDstraight()); //yes
        System.out.println(dist.distFromAtoCDray()); //yes
        System.out.println(dist.distFromСtoABsection()); //yes
        System.out.println(dist.distFromABtoCDsection()); //yes
        System.out.println(dist.distFromABtoCDsectionStraight()); //yes
        System.out.println(dist.distFromABtoCDsectionRay()); //yes
        System.out.println(dist.distFromABtoCray()); //yes
        System.out.println(dist.distABtoCDraySection()); //yes
        System.out.println(dist.distABtoCDrayStraight()); //yes
        System.out.println(dist.distABtoCDrayRay()); //yes
        System.out.println(dist.distABtoCstraight());//yes
        System.out.println(dist.distABtoCDstraightSection());//yes
        System.out.println(dist.distABtoCDstraightStraight()); // yes
        System.out.println(dist.distABtoCDstraigtRay()); // yes

    }
}
