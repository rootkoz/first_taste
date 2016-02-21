/***
 * by rootkoz
 * >(((*>
 */


public class PointUse {

    public static void main(String[] args) {
        Point p = new Point();

        Point p1 = new Point(2,6);
        Point p2 = new Point(6,9);

        System.out.println(" function out >> Distance = " + distance(p1, p2));
        System.out.println(" method out   >> distance = " + p.distance(p1, p2));
    }

    public  static double distance(Point p1, Point p2){
        return Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y -p2.y)*(p1.y -p2.y));
    }
}