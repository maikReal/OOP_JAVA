package main.java.company.evo.GeometricDistances;

public class Ray extends Straight {

    private final double eps = 1e-6;

    public Ray(Point p1, Point p2) {
        super(p1, p2);

    }

    /**
     * Get the nearest point for the `point`
     *
     * @param point point of class Point
     * @return nearest point
     */
    public Point nearestPoint(Point point) {
        Point nearestPoint = super.nearestPoint(point);
        return (this.contains(nearestPoint)) ? nearestPoint : this.p1;
    }

    /**
     * Convert ray to section
     *
     * @return section
     */
    public Section toSection() {
        return new Section(this.p1, this.p2);
    }

    /**
     * Getter for point1 of the ray
     *
     * @return point1
     */
    public Point getP1() {
        return this.p1;
    }

    /**
     * Getter for point2 of the ray
     *
     * @return point2
     */
    public Point getP2() {
        return this.p2;
    }

    /**
     * This function checking whether ray contains point `p` or not
     *
     * @param p point of class Point
     * @return boolean statement
     */
    public boolean contains(Point p) {
        return super.contains(p) && (Point.dotProduct(Point.substraction(this.p2, this.p1), Point.substraction(p, this.p1)) > -eps);
    }

    /**
     * Convert ray to straight
     *
     * @return straight
     */
    public Straight toStraight() {
        return new Straight(this.p1, this.p2);
    }
}
