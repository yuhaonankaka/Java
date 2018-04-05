
public class Condition {
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
