
public class Declaration {
  String [] names;
  
  public Declaration(String [] names) {
    this.names=names;
  }
  public String[] getNames() {
    return names;
  }
  
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
  
  
}
