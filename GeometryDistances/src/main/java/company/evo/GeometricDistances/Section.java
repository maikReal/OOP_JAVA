package main.java.company.evo.GeometricDistances;

public class Section extends Ray {

    private final double eps = 1e-6;

    public Section(Point p1, Point p2) {
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
        return (this.contains(nearestPoint)) ? nearestPoint : p2;
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
     * This function finding the length of the section. It means that we just finding the distance between 2 points
     *
     * @return length of section
     */
    public double getLength() {
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p1.y - p2.y), 2));
    }


    /**
     * Convert section to straight
     *
     * @return straight
     */
    public Straight toStraight() {

        return new Straight(this.p1, this.p2);

    }

    /**
     * This function checking whether section contains point `p` or not
     *
     * @param p point of class Point
     * @return
     */
    public boolean contains(Point p) {
        return super.contains(p) && (new Ray(p2, p1)).contains(p);
    }


}
