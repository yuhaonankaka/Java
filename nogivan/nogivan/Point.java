package nogivan;

import java.util.Comparator;

public class Point {
  public final static int UNBEKANNT = 0;
  public final static int VERMUTET = 1;
  public final static int BEKANNT = 2;

  private int distance;
  private OSMNode vorgaenger;
  private OSMNode node;
  private int state = UNBEKANNT;
  
  public OSMNode getNode() {
    return node;
  }

  public Point(OSMNode node) {
    this.node = node;
    distance = Integer.MAX_VALUE;
    vorgaenger = null;
  }

  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) {
    if (distance >= 0) {
      this.distance = distance;
    } else {
      this.distance = Integer.MAX_VALUE;
    }
  }

  public OSMNode getVorgaenger() {
    return vorgaenger;
  }

  public void setVorgaenger(OSMNode vorgaenger) {
    this.vorgaenger = vorgaenger;
  }

  public int getState() {
    return state;
  }

  public void setState(int newState) {
    this.state = newState;
  }

  @Override
  public String toString() {
    return "Point {" + "distance=" + distance + ", vorgaenger=" + vorgaenger.getId() + ", ID='"
        +node.getId() + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Point that = (Point) o;

    return that.node.getId() == this.node.getId();
  }
  
  /*@Override
  public int compareTo(Point p) {
    int d1 = this.getDistance();
    int d2 = p.getDistance();
    return d1 > d2 ? 1 : (d1 == d2 ? 0 : -1);
  }*/
  
  
}
