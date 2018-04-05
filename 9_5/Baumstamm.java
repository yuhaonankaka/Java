public class Baumstamm extends MultiObject {
  public Baumstamm(int x, int y, int breite) {
    super(x, y, breite);
    StammLinks l = new StammLinks(x,y);
    super.parts.add(l);
    for(int i = 0;i<2*breite;i++) {
    StammMitte m = new StammMitte(x+i+1,y);
    super.parts.add(m);
    }
    StammRechts r = new StammRechts(x+1+2*breite,y);
    super.parts.add(r);
  }
  
  
  
}
