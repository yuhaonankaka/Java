package format;

public class False extends Condition {

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

}
