package horrorhaufen;

public class True extends Condition {

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

}
