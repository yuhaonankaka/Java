
public class Expression {
 
  
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
 
}
