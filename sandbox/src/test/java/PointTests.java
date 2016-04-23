/***
 * by rootkoz
 * >(((*>
 */

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

   @Test
   public void PointDistance(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(3,4);
        Point p3 = new Point(0,8);

        Assert.assertEquals(p1.distance(p1,p2),5.0);
        Assert.assertEquals(p1.distance(p1,p3),8.0);
    }
}
