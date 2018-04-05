package dijkstra_angabe;
public class Seeweg {
  private int distance;
  private Eisscholle from;
  private Eisscholle to;

  // TODO
  public Seeweg(int distance, Eisscholle from, Eisscholle to) {
    this.distance = distance;
    this.from = from;
    this.to=to;
  }

  public Eisscholle getFrom() {
    return from;
  }
  public Eisscholle getTo() {
    return to;
  }
  public int getDistance() {
    return distance;
  }
  public void setFrom(Eisscholle e) {
    this.from=e;
  }
  public void setTo(Eisscholle e) {
    this.to = e;
  }
  public void setDistance(int distance) {
    this.distance = distance;
  }
  
}
