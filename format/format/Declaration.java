package format;

public class Declaration {
  public String toString() {
    FormatVisitor v = new FormatVisitor();
    this.accept(v);
    return v.getResult();
    
  }
  
  private String[] names;
  
  public String[] getNames() {
    return names;
  }
  
  public Declaration(String[] names) {
    super();
    this.names = names;
  }
  
  public Declaration(String a) {
    this.names = new String [] { a };
  }

  public Declaration(String a, String b) {
    this.names = new String [] { a, b };
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
