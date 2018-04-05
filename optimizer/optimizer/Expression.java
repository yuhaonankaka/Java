package optimizer;

public abstract class Expression {
  public abstract void accept(Visitor visitor);
}
