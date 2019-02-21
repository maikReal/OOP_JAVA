package main.java.company.evo.GeometricDistances;

public class Section {

    private Point p1, p2;

    public Section(Point p1, Point p2){

        this.p1 = p1;
        this.p2 = p2;

    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public double getLength(){
        return Math.sqrt(Math.pow((p1.y - p2.y), 2) + Math.pow((p2.x - p1.x), 2));
    }

    public double[] getEquationStraight(){

        double A = p1.y - p2.y;
        double B = p2.x - p1.x;
        double C = p1.x * p2.y - p2.x * p1.y;

        double[] coefs = {A,B,C};
        return coefs;

    }

    public boolean isBelong(double x, double y){

        return this.getEquationStraight()[0] * x + this.getEquationStraight()[1] * y + this.getEquationStraight()[2] == 0;

    }

    public Straight makeStraight(){

        return new Straight(this.p1, this.p2);

    }
}
