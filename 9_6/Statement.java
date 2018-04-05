
public class Statement {
  
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
  
}
