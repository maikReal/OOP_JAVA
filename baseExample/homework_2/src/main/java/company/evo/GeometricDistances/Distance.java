package main.java.company.evo.GeometricDistances;


public class Distance {

    private Point A, B, C, D;

    public Distance(Point A, Point B, Point C, Point D) {

        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;

    }

    private double distPointPoint(Point p1, Point p2) {

        return Math.sqrt(Math.pow((p1.y - p2.y), 2) + Math.pow((p2.x - p1.x), 2));

    }

    private boolean checkObtuseAngle(Section s1, Section s2, Section s3) {

        return s1.getLength() + s2.getLength() > s3.getLength() ||
                s2.getLength() + s3.getLength() > s1.getLength() ||
                s3.getLength() + s1.getLength() > s2.getLength();

    }

    private double getPerimeter(Section s1, Section s2, Section s3) {

        return (s1.getLength() + s2.getLength() + s3.getLength()) / 2;

    }

    private double getHeight(Section s1, Section s2, Section a) {

        // a - сторона на которую опускается высота

        double p = getPerimeter(s1, s2, a);

        return (2 * Math.sqrt(p * (p - a.getLength()) * (p - s1.getLength()) * (p - s2.getLength()))) / a.getLength();

    }

    private double distPointStraight(Point p, Straight s) {

        double A = s.getP1().y - s.getP2().y;
        double B = s.getP2().x - s.getP1().x;
        double C = s.getP1().x * s.getP2().y - s.getP2().x * s.getP1().y;

        return Math.abs((A * p.x + B * p.y
                + C) / (Math.sqrt(Math.pow(A, 2) + Math.pow(B, 2))));
    }

    private double distPointSection(Section s1, Section s2, Section a) {

        double dist;

        if (checkObtuseAngle(s1, s2, a)) {
            dist = getHeight(s1, s2, a);
        } else {
            if (s1.getLength() < s2.getLength()) {
                dist = s1.getLength();
            } else {
                dist = s2.getLength();
            }
        }

        return dist;

    }


    private double distSectioSection(Section s1, Section s2) {

        double dist1 = isParallelVals(s1, s2)[0];
        double dist2 = isParallelVals(s1, s2)[1];

        if (dist1 == dist2) {
            return dist1;
        } else {
            return dist1 > dist2 ? dist2 : dist1;
        }
    }

    private double[] isParallelVals(Section s1, Section s2) {
        double dist1 = (distPointPoint(s1.getP1(), s2.getP1()) > distPointPoint(s1.getP1(), s2.getP2()))
                ? distPointPoint(s1.getP1(), s2.getP2()) : distPointPoint(s1.getP1(), s2.getP1());


        double dist2 = (distPointPoint(s1.getP2(), s2.getP1()) > distPointPoint(s1.getP2(), s2.getP2()))
                ? distPointPoint(s1.getP2(), s2.getP2()) : distPointPoint(s1.getP2(), s2.getP1());

        double[] dists = {dist1, dist2};

        return dists;

    }

    private double distRayPoint(Ray r, Point p) {

        double dist;

        double A = r.getEquationStraight()[0];
        double B = r.getEquationStraight()[1];
        double C = r.getEquationStraight()[2];

        Section s = new Section(r.getP1(), p);

        double A1 = s.getEquationStraight()[0];
        double B1 = s.getEquationStraight()[1];
        double C1 = s.getEquationStraight()[2];

        double cos = (A * A1 + B * B1) / (Math.sqrt(Math.pow(A, 2) + Math.pow(B, 2)) * Math.sqrt(Math.pow(A1, 2) + Math.pow(B1, 2)));

        if (cos > -1) {
            dist = distPointStraight(p, new Straight(r.getP1(), r.getP2()));
        } else {
            dist = distPointPoint(p, r.getP1());
        }


        return dist;

    }

    private boolean isItersected(Straight s1, Straight s2) {

        double ua = ((s1.getP1().x - s1.getP2().x) * (s2.getP1().y - s1.getP2().y) - (s1.getP1().y - s1.getP2().y) * (s2.getP1().x - s1.getP2().x)) /
                ((s1.getP1().y - s1.getP2().y) * (s2.getP2().x - s2.getP1().x) - (s1.getP1().x - s1.getP2().x) * (s2.getP2().y - s2.getP1().y));

        double x = s1.getP1().x + ua * (s1.getP2().x - s1.getP1().x);
        double y = s1.getP1().y + ua * (s1.getP2().y - s1.getP1().y);

        return s1.isBelong(x, y) && s2.isBelong(x, y);

    }


    public double distFromAtoC() {

        double dist;

        dist = distPointPoint(this.A, this.C);

        return dist;
    }

    public double distFromAtoCDsection() {

        double dist;

        Section AC = new Section(this.A, this.C);
        Section AD = new Section(this.A, this.D);
        Section CD = new Section(this.C, this.D);


        dist = distPointSection(AC, AD, CD);
        return (double) Math.round(dist * 10000000000d) / 10000000000d;
    }


    public double distFromAtoCDstraight() {


        Straight CD = new Straight(this.C, this.D);

        return (double) Math.round(distPointStraight(this.A, CD) * 10000000000d) / 10000000000d;
    }


    public double distFromAtoCDray() {

        return (double) Math.round(distRayPoint(new Ray(this.C, this.D), this.A) * 10000000000d) / 10000000000d;

    }

    public double distFromСtoABsection() {

        Section AC = new Section(this.A, this.C);
        Section CB = new Section(this.C, this.B);
        Section AB = new Section(this.A, this.B);

        return (double) Math.round(distPointSection(AC, CB, AB) * 10000000000d) / 10000000000d;

    }

    public double distFromABtoCDsection() {

        Section AB = new Section(this.A, this.B);
        Section CD = new Section(this.C, this.D);

        return (double) Math.round(distSectioSection(AB, CD) * 10000000000d) / 10000000000d;

    }

    public double distFromABtoCDsectionStraight() {

        Straight CD = new Straight(this.C, this.D);

        return distPointStraight(this.A, CD) > distPointStraight(this.B, CD) ? distPointStraight(this.B, CD) : distPointStraight(this.A, CD);
    }


    public double distFromABtoCDsectionRay() {

        double dist;

        Ray CD = new Ray(this.C, this.D);

        dist = distRayPoint(CD, this.A) > distRayPoint(CD, this.B) ? distRayPoint(CD, this.B) : distRayPoint(CD, this.A);

        return (double) Math.round(dist * 10000000000d) / 10000000000d;

    }


    public double distFromABtoCray() {

        return (double) Math.round(distRayPoint(new Ray(this.A, this.B), this.C) * 10000000000d) / 10000000000d;
    }

    public double distABtoCDraySection() {

        double dist;

        Ray AB = new Ray(this.A, this.B);

        dist = distRayPoint(AB, this.C) > distRayPoint(AB, this.D) ? distRayPoint(AB, this.D) : distRayPoint(AB, this.C);

        return (double) Math.round(dist * 10000000000d) / 10000000000d;

    }

    public double distABtoCDrayStraight() {

        Ray ABray = new Ray(this.A, this.B);

        Straight CD = new Straight(this.C, this.D);
        Straight AB = ABray.makeStraight();

        if (isItersected(AB, CD)) {
            return 0;
        } else {
            return distPointStraight(AB.getP1(), CD);
        }

    }

    public double distABtoCDrayRay() {

        double dist;

        Ray ABray = new Ray(this.A, this.B);
        Straight AB = ABray.makeStraight();

        Ray CDray = new Ray(this.A, this.B);
        Straight CD = CDray.makeStraight();

        if (isItersected(AB, CD)) {
            dist = 0;
        } else {
            dist = distPointPoint(ABray.getP1(), CDray.getP1());
        }

        return dist;


    }

    public double distABtoCstraight() {

        Straight AB = new Straight(this.A, this.B);

        return (double) Math.round(distPointStraight(this.C, AB) * 10000000000d) / 10000000000d;
    }

    public double distABtoCDstraightSection() {

        Straight AB = new Straight(this.A, this.B);
        double dist;

        dist = distPointStraight(this.C, AB) > distPointStraight(this.D, AB) ? distPointStraight(this.D, AB) : distPointStraight(this.C, AB);


        return Math.round(dist * 10000000000d) / 10000000000d;
    }

    public double distABtoCDstraightStraight() {

        double dist;

        Straight AB = new Straight(this.A, this.B);
        Straight CD = new Straight(this.C, this.D);

        if (isItersected(AB, CD)) {
            dist = 0;
        } else {
            dist = distPointPoint(AB.getP1(), CD.getP1()) > distPointPoint(AB.getP1(), CD.getP2()) ? distPointPoint(AB.getP1(), CD.getP2()) : distPointPoint(AB.getP1(), CD.getP1());
        }

        return dist;

    }

    public double distABtoCDstraigtRay() {

        double dist;

        Ray CDray = new Ray(this.C, this.D);
        Straight CD = CDray.makeStraight();

        Straight AB = new Straight(this.A, this.B);

        if (isItersected(AB, CD)) {
            dist = 0;
        } else {
            dist = distPointPoint(AB.getP1(), CD.getP1()) > distPointPoint(AB.getP2(), CD.getP1()) ? distPointPoint(AB.getP2(), CD.getP1()) : distPointPoint(AB.getP1(), CD.getP1());
        }


        return dist;

    }

}
