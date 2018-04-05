package dijkstra_angabe;
public class Eisscholle {

  public final static int UNBEKANNT = 0;
  public final static int VERMUTET = 1;
  public final static int BEKANNT = 2;


  private int distance;
  private Eisscholle vorgaenger;
  private final String name;
  private int state = UNBEKANNT;

  // TODO
  public Eisscholle(String name) {
    this.name = name;
    
    distance=Integer.MAX_VALUE;
    vorgaenger = null;
  }
  
  
  public int getDistance() {
    return distance;
  }
  
  public Eisscholle getVorgaenger() {
    return vorgaenger;
  }
  
  public String getName() {
    return name;
  }
  public int getState() {
    return state;
  }
  public void setDistance(int distance) {
    if(distance>=0) 
      this.distance = distance;
    else
      this.distance = Integer.MAX_VALUE;
  }
  public void setVorgaenger(Eisscholle vorgaenger) {
    this.vorgaenger = vorgaenger;
  }
  public void setState(int state) {
    if(state==0 || state==1 || state==2)
    this.state = state;
    else
    System.out.println("Wrong input state! Please try again!");
  }
  public boolean equals(Eisscholle e) {
    if(e.name.equals(this.name)) {
      return true;
    }
    else
      return false;
  }
  
  public String toString() {
    String s = "";
    s=s+"Name: " + this.name +" Distance: "+this.distance+" Vorgaenger: "+this.vorgaenger.name
        +" State: ";
    if(this.state == 0) {
      s=s+"UNBEKANNT\n";
      return s;
    }
    else if(this.state == 1) {
      s=s+"VERMUTET\n";
      return s;
    }
    else {
      s=s+"BEKANNT\n";
      return s;
              }
  }
  
}
