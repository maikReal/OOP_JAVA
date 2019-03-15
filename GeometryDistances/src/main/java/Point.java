public class Point {
    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Finding the vector product between two points
     *
     * @param p1 point of class Point
     * @param p2 point of class Point
     * @return vector product
     */
    public static double crossProduct(Point p1, Point p2) {
        return p1.x * p2.y - p1.y * p2.x;
    }

    /**
     * This function finding the new coordinate substraction between two points
     *
     * @param point1 point of class Point
     * @param point2 point of class Point
     * @return (x1 - x2, y1 - y2) point
     */
    public static Point substraction(Point point1, Point point2) {
        return new Point(point1.x - point2.x, point1.y - point2.y);
    }

    /**
     * This function finding the new coordinates `x` and `y`, which are multiply by the number `c`
     *
     * @param point point of class Point
     * @param c     constant
     * @return new point
     */
    public static Point multiplication(Point point, double c) {
        return new Point(point.x * c, point.y * c);
    }

    /**
     * This function finding the coordinate sum between two points
     *
     * @param point1 point of class Point
     * @param point2 point of class Point
     * @return (x1 + x2, y1 + y2) point
     */
    public static Point add(Point point1, Point point2) {
        return new Point(point1.x + point2.x, point1.y + point2.y);
    }

    /**
     * This function finding the scalar product between two points
     *
     * @param point1 point of class Point
     * @param point2 point of class Point
     * @return scalar product
     */
    public static double dotProduct(Point point1, Point point2) {
        return point1.x * point2.x + point1.y * point2.y;
    }
}
