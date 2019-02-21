package main.java.company.evo;

public class Ray extends Section {

    private Point p;

    public Ray(Point p1, Point p2) {
        super(p1, p2);
    }

//    public Ray(Point p1){
//        this.p = p1;
//    }

    public Point getPoint(){
        return this.p;
    }
}
