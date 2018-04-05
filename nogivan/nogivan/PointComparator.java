package nogivan;

import java.util.Comparator;



public class PointComparator implements Comparator<Point>{
  @Override
  public int compare(Point e1, Point e2) {
    int d1 = e1.getDistance();
    int d2 = e2.getDistance();
    return d1 > d2 ? 1 : (d1 == d2 ? 0 : -1);
  }
}
