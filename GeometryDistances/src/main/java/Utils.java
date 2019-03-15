import java.util.Objects;

public class Utils {


    private static double eps = 1e-6;

    /**
     * This function find the angle between sections p1p2 and p2p3. For this we are using cosine theorem. After the finding
     * the current type of angle we are using library Math and method acos for searching the degrees of angle
     *
     * @param p1 point of class Point
     * @param p2 point of class Point
     * @param p3 point of class Point
     * @return Name of angle
     */
    public static String typeAngle(Point p1, Point p2, Point p3) {

        Section p1p2 = new Section(p1, p2);
        Section p2p3 = new Section(p2, p3);
        Section p1p3 = new Section(p1, p3);

        float cos = (float) (Math.acos((Math.pow(p1p2.getLength(), 2) + Math.pow(p2p3.getLength(), 2) - Math.pow(p1p3.getLength(), 2)) /
                (2 * p1p2.getLength() * p2p3.getLength())) * 180 / Math.PI);

        if (cos < 90) return "Острый";
        if (cos == 90) return "Прямой";
        if (cos > 90) return "Тупой";

        return null;

    }

    /**
     * Current method return the euclidean distance between two point
     *
     * @param p1 instance of class Point
     * @param p2 instance of class Point
     * @return Euclidean distance between two point
     */
    public static double distFromPointToPoint(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p1.y - p2.y), 2) + Math.pow((p2.x - p1.x), 2));
    }

    /**
     * Finding perimeter for current angle, which consist of such sections
     *
     * @param s1 section of class Section
     * @param s2 section of class Section
     * @param s3 section of class Section
     * @return Perimeter of triangle
     */
    private static double getPerimetr(Section s1, Section s2, Section s3) {
        return (s1.getLength() + s2.getLength() + s3.getLength()) / 2;
    }

    /**
     * Current function finding the height, which falls to the side s3. Current method works just for sharp corner
     *
     * @param s1 section of class Section
     * @param s2 section of class Section
     * @param s3 section of class Section
     * @return Height, which falls to the side s3
     */
    public static double getHeight(Section s1, Section s2, Section s3) {

        double perimeter = getPerimetr(s1, s2, s3);

        return (2 * Math.sqrt(perimeter * (perimeter - s3.getLength())
                * (perimeter - s1.getLength()) * (perimeter - s2.getLength()))) / s3.getLength();

    }

    /**
     * Current function determines whether straight lines are parallel or not
     *
     * @param s1 section of class Section
     * @param s2 section of class  Section
     * @return Boolean statement
     */
    public static boolean isParallel(Section s1, Section s2) {

        return s1.a * s2.b - s2.a * s1.b == 0;

    }

    /**
     * Finding distance between two parallel sections
     *
     * @param section1 section of class Section
     * @param section2 section of class Section
     * @return Distance between two parallel sections
     */
    private static double getDistBetweenParallelSections(Section section1, Section section2) {

        return distFromPointToPoint(section1.getP1(), section2.getP1()) > distFromPointToPoint(section1.getP1(), section2.getP2()) ?
                distFromPointToPoint(section1.getP1(), section2.getP2()) : distFromPointToPoint(section1.getP1(), section2.getP1());

    }


    /**
     * Finding the type of angle and after this we finding the certain distance. Note: the height falls on the side
     * `section`
     *
     * @param point   point of class Point
     * @param section section of class Section
     * @return Certain distance
     */
    public static double distFromPointToSection(Point point, Section section) {

        switch (Objects.requireNonNull(typeAngle(point, section.getP1(), section.getP2()))) {
            case "Прямой":
                return distFromPointToPoint(point, section.getP1());
            case "Тупой":
                return distFromPointToPoint(point, section.getP1());
            case "Острый":

                Section p1p2 = new Section(point, section.getP1());
                Section p1p3 = new Section(point, section.getP2());

                return getHeight(p1p2, p1p3, section);
        }

        return Double.parseDouble(null);
    }

    /**
     * Finding the type of angle and after this we finding the certain distance. Note: the height falls on the side
     * `ray`
     *
     * @param ray   ray of class Ray
     * @param point point of class Point
     * @return Certain distance
     */
    public static double distFromRayToPoint(Ray ray, Point point) {

        switch (Objects.requireNonNull(typeAngle(point, ray.getP1(), ray.getP2()))) {
            case "Прямой":
                return distFromPointToPoint(point, ray.getP1());
            case "Тупой":
                return distFromPointToPoint(point, ray.getP1());
            case "Острый":
                Section p1p2 = new Section(point, ray.getP1());
                Section p1p3 = new Section(point, ray.getP2());

                return getHeight(p1p2, p1p3, ray.toSection());

        }
        return Double.parseDouble(null);

    }

    /**
     * Try to know whether straights are intersected or no. After this we define the method which we will use for
     * searching the certain distance. If straights bot intersected, then we will finding the distance between the parallel
     * sections
     *
     * @param s1 section of class Section
     * @param s2 section of class Section
     * @return Certain distance
     * @throws ParallelLinesException
     */
    public static double distFromSectionToSection(Section s1, Section s2) throws ParallelLinesException {
        try {
            Point intersectionPoint = intersection(s1.toStraight(), s2.toStraight());

            if (intersectionPoint.x >= s1.p1.x && intersectionPoint.x <= s1.p2.x ||
                    intersectionPoint.x > s2.p1.x && intersectionPoint.x < s2.p2.x) {
                return (double) 0;
            } else {
                double lengthFromS1 = distFromPointToPoint(s1.getP1(), s2.getP1()) > distFromPointToPoint(s1.getP1(), s2.getP2()) ?
                        distFromPointToPoint(s1.getP1(), s2.getP2()) : distFromPointToPoint(s1.getP1(), s2.getP1());

                double lengthFromS2 = distFromPointToPoint(s1.getP2(), s2.getP1()) > distFromPointToPoint(s1.getP2(), s2.getP2()) ?
                        distFromPointToPoint(s1.getP2(), s2.getP2()) : distFromPointToPoint(s1.getP2(), s2.getP1());

                return lengthFromS1 > lengthFromS2 ? lengthFromS2 : lengthFromS1;
            }

        } catch (ParallelLinesException e) {
            return getDistBetweenParallelSections(s1, s2);
        }
    }

    /**
     * This function finding the normal from point to the straight. Here we are using the straight equation
     *
     * @param point    point of class Point
     * @param straight straight of class Straight
     * @return Normal to
     */
    public static double distFromPointToStraight(Point point, Straight straight) {

        return Math.abs((straight.a * point.x + straight.b * point.y
                + straight.c)) / (Math.sqrt(Math.pow(straight.a, 2) + Math.pow(straight.b, 2)));

    }

    /**
     * This function try to know whether the straights are intersected or no. If they are intersected and this intersection
     * point is satisfied the condition for section, then we return 0, else we finding the distance between point and straight.
     * If straights don't intersected, we finding the distance between two parallel straights
     *
     * @param section  section of class Section
     * @param straight straight of class Straight
     * @return Certain distance
     */
    public static double distFromSectionToStraight(Section section, Straight straight) {
        try {
            Point intersectionPoint = intersection(section, straight);

            if (intersectionPoint.x > section.p1.x && intersectionPoint.x < section.p2.x ||
                    intersectionPoint.x > straight.p1.x && intersectionPoint.x < straight.p2.x) {
                return (double) 0;
            } else {
                double distToP1 = distFromPointToStraight(section.getP1(), straight);
                double distToP2 = distFromPointToStraight(section.getP2(), straight);

                return distToP1 > distToP2 ? distToP2 : distToP1;
            }
        } catch (ParallelLinesException e) {
            return getDistBetweenParallelSections(section, straight.toSection());
        }

    }

    /**
     * This function try to know whether the straights are intersected or no. If they are intersected and this intersection
     * point is satisfied the condition for section, then we return 0, else we finding the distance between point and straight.
     * If straights don't intersected, we finding the distance between two parallel straights
     *
     * @param section section of class Section
     * @param ray     ray of class Ray
     * @return Certain distance
     */
    public static double distFromSectionToRay(Section section, Ray ray) {

        try {
            Point intersectionPoint = intersection(section, ray);

            if (intersectionPoint.x >= section.p1.x && intersectionPoint.x <= section.p2.x && intersectionPoint.x >= ray.p1.x ||
                    intersectionPoint.x >= section.p2.x && intersectionPoint.x <= section.p1.x && intersectionPoint.x >= ray.p1.x) {
                return (double) 0;
            } else {
                double rayP1 = distFromPointToStraight(section.getP1(), ray.toStraight());
                double rayP2 = distFromPointToStraight(section.getP2(), ray.toStraight());

                return rayP1 > rayP2 ? rayP2 : rayP1;
            }
        } catch (ParallelLinesException e) {
            return getDistBetweenParallelSections(section, ray.toSection());
        }

    }

    /**
     * This function try to know whether the straights are intersected or no. If they are intersected and this intersection
     * point is satisfied the condition for section, then we return 0, else we finding the distance between point and straight.
     * If straights don't intersected, we finding the distance between two parallel straights
     *
     * @param straight straight of class Straight
     * @param ray      ray of class Ray
     * @return Certain distance
     */
    public static double distFromStraightToRay(Straight straight, Ray ray) {

        try {
            Point intersectionPoint = intersection(straight, ray);

            if (intersectionPoint.x >= ray.p1.x) {
                return (double) 0;
            } else {
                return distFromPointToStraight(ray.getP1(), straight);
            }
        } catch (ParallelLinesException e) {
            return getDistBetweenParallelSections(straight.toSection(), ray.toSection());
        }

    }

    /**
     * This function try to know whether the straights are intersected or not. If they are and this point of intersection
     * is satisfied the all necessary conditions, we will return 0, else we will find the distance between the point
     * and straight. If the straights are parallel, we will finding the distance between the parallel straights
     *
     * @param ray1 ray of class Ray
     * @param ray2 ray of class Ray
     * @return Certain distance
     */
    public static double distFromRayToRay(Ray ray1, Ray ray2) {

        try {
            Point intersectionPoint = intersection(ray1, ray2);

            if (intersectionPoint.x >= ray1.p1.x && intersectionPoint.x >= ray2.p1.x) {
                return (double) 0;
            } else {
                return distFromPointToStraight(ray1.p1, ray2);
            }
        } catch (ParallelLinesException e) {
            return getDistBetweenParallelSections(ray1.toSection(), ray2.toSection());
        }

    }

    /**
     * This function try to know whether the straights are intersected or not. If they are, we will return 0,
     * else we will find the distance between the point and straight. If the straights are parallel, we will
     * finding the distance between the parallel straights
     *
     * @param straight1 straight of class Straight
     * @param straight2 straight of class Straight
     * @return Certain distance
     */
    public static double distFromStraightToStraight(Straight straight1, Straight straight2) {
        try {
            intersection(straight1, straight2);
            return 0;
        } catch (ParallelLinesException e) {
            return distFromPointToStraight(straight1.p1, straight2);
        }
    }

    /**
     * This function finding the point of intersection between two straights. If they are parallel, we will throw the exception.
     *
     * @param straight1 straight of class Straight
     * @param straight2 straight of class Straight
     * @return Point of intersection
     * @throws ParallelLinesException
     */
    public static Point intersection(Straight straight1, Straight straight2) throws ParallelLinesException {
        double x;
        double y;

        if (Math.abs(Point.crossProduct(Point.substraction(straight1.p2, straight1.p1), Point.substraction(straight2.p2, straight2.p1))) < 1e-6) {
            throw new ParallelLinesException();
        }
        if (Math.abs(straight1.a) < eps) {
            y = -straight1.c / straight1.b;
            x = (-straight2.c - straight2.b * y) / straight2.a;
        } else if (Math.abs(straight1.b) < eps) {
            x = -straight1.c / straight1.a;
            y = (-straight2.c - straight2.a * x) / straight2.b;
        } else {
            x = (straight2.c / straight2.b - straight1.c / straight1.b) / (straight1.a / straight1.b - straight2.a / straight2.b);
            y = (straight2.c / straight2.a - straight1.c / straight1.a) / (straight1.b / straight1.a - straight2.b / straight2.a);
        }

        if (String.valueOf(x).equals("NaN")) {
            x = 0;
        }
        if (String.valueOf(y).equals("NaN")) {
            y = 0;
        }
        return new Point(x, y);
    }


}