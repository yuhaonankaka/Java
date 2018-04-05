public class Ast extends MultiObject{

  public Ast(int x, int y, int breite) {
    super(x, y, breite);
    AstLinks l = new AstLinks(x,y);
    super.parts.add(l);
    for(int i = 0;i<2*breite;i++) {
    AstMitte m = new AstMitte(x+i+1,y);
    super.parts.add(m);
    }
    AstRechts r = new AstRechts(x+1+2*breite,y);
    super.parts.add(r);
  }
  
}
