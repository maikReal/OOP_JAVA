public class Distance {

    private Point A, B, C, D;

    public Distance(Point A, Point B, Point C, Point D) {

        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;

    }

    public double distFromAtoC() {

        return (double) Math.round(Utils.distFromPointToPoint(this.A, this.C) * 10000000000d) / 10000000000d;
    }

    public double distFromAtoCDsection() {

        double dist;
        Section CD = new Section(this.C, this.D);

        dist = Utils.distFromPointToSection(this.A, CD);

        return (double) Math.round(dist * 10000000000d) / 10000000000d;
    }

    public double distFromAtoCDstraight() {

        double dist;

        Straight CD = new Straight(this.C, this.D);

        Section AC = new Section(this.A, this.C);
        Section AD = new Section(this.A, this.D);

        dist = Utils.getHeight(AC, AD, CD.toSection());

        return (double) Math.round(dist * 10000000000d) / 10000000000d;
    }

    public double distFromAtoCDray() {

        return (double) Math.round(Utils.distFromRayToPoint(new Ray(this.C, this.D), this.A) * 10000000000d) / 10000000000d;

    }

    public double distFromСtoABsection() {

        double dist;
        Section AB = new Section(this.A, this.B);

        dist = Utils.distFromPointToSection(this.C, AB);
        return (double) Math.round(dist * 10000000000d) / 10000000000d;

    }

    public double distFromABtoCDsection() throws ParallelLinesException {

        Section AB = new Section(this.A, this.B);
        Section CD = new Section(this.C, this.D);

        return (double) Math.round(Utils.distFromSectionToSection(AB, CD) * 10000000000d) / 10000000000d;

    }

    public double distFromABtoCDsectionStraight() {

        Section AB = new Section(this.A, this.B);
        Straight CD = new Straight(this.C, this.D);

        return (double) Math.round(Utils.distFromSectionToStraight(AB, CD) * 10000000000d) / 10000000000d;
    }

    public double distFromABtoCDsectionRay() {

        Ray CD = new Ray(this.C, this.D);

        Section AB = new Section(this.A, this.B);

        return (double) Math.round(Utils.distFromSectionToRay(AB, CD) * 10000000000d) / 10000000000d;

    }


    public double distFromABtoCray() {

        Ray AB = new Ray(this.A, this.B);

        return (double) Math.round(Utils.distFromRayToPoint(AB, this.C) * 10000000000d) / 10000000000d;
    }

    public double distABtoCDraySection() {

        double dist;

        Ray AB = new Ray(this.A, this.B);
        Section CD = new Section(this.C, this.D);

        dist = Utils.distFromSectionToRay(CD, AB);

        return (double) Math.round(dist * 10000000000d) / 10000000000d;

    }

    public double distABtoCDrayStraight() {

        Ray AB = new Ray(this.A, this.B);

        Straight CD = new Straight(this.C, this.D);

        return (double) Math.round(Utils.distFromStraightToRay(CD, AB) * 10000000000d) / 10000000000d;

    }

    public double distABtoCDrayRay() {

        Ray AB = new Ray(this.A, this.B);

        Ray CD = new Ray(this.A, this.B);

        return (double) Math.round(Utils.distFromRayToRay(AB, CD) * 10000000000d) / 10000000000d;


    }

    public double distABtoCstraight() {

        Straight AB = new Straight(this.A, this.B);

        return (double) Math.round(Utils.distFromPointToStraight(this.C, AB) * 10000000000d) / 10000000000d;
    }

    public double distABtoCDstraightSection() {

        Straight AB = new Straight(this.A, this.B);
        Section CD = new Section(this.C, this.D);

        return (double) Math.round(Utils.distFromSectionToStraight(CD, AB) * 10000000000d) / 10000000000d;

    }

    public double distABtoCDstraightStraight() {

        Straight AB = new Straight(this.A, this.B);
        Straight CD = new Straight(this.C, this.D);

        return (double) Math.round(Utils.distFromStraightToStraight(AB, CD) * 10000000000d) / 10000000000d;

    }

    public double distABtoCDstraigtRay() {


        Ray CD = new Ray(this.C, this.D);

        Straight AB = new Straight(this.A, this.B);

        return (double) Math.round(Utils.distFromStraightToRay(AB, CD) * 10000000000d) / 10000000000d;


    }

}
