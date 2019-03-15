public class Straight {

    protected Point p1;
    protected Point p2;
    protected double a, b, c;
    private final double eps = 1e-6;

    public Straight(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.a = this.getEquationStraight()[0];
        this.b = this.getEquationStraight()[1];
        this.c = this.getEquationStraight()[2];

    }

    /**
     * Finding the coefficients for the equation of straight
     *
     * @return coefficients for the straight
     */
    private double[] getEquationStraight() {

        double A = p1.y - p2.y;
        double B = p2.x - p1.x;
        double C = p1.x * p2.y - p2.x * p1.y;


        return new double[]{A, B, C};

    }

    /**
     * Getter for point1 of the straight
     *
     * @return point1
     */
    public Point getP1() {
        return p1;
    }

    /**
     * Getter for point2 of the straight
     *
     * @return point2
     */
    public Point getP2() {
        return p2;
    }

    /**
     * Convert straight to section
     *
     * @return section
     */
    public Section toSection() {
        return new Section(this.p1, this.p2);
    }

    /**
     * Get the nearest point for the `point`
     *
     * @param point point of class Point
     * @return nearest point
     */
    public Point nearestPoint(Point point) {
        double dist = Utils.distFromPointToStraight(point, this);
        Point res = Point.add(point, Point.multiplication(new Point(this.a, this.b), dist));
        if (!this.contains(res)) {
            res = Point.substraction(point, Point.multiplication(new Point(this.a, this.b), dist));
        }
        return res;
    }

    /**
     * This function checking whether straight contains point `p` or not
     *
     * @param p point of class Point
     * @return boolean statement
     */
    public boolean contains(Point p) {
        return Math.abs(this.a * p.x + this.b * p.y + this.c) < eps;
    }


}
