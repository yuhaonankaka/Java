package compiler;

public abstract class Expression {
  public abstract void accept(Visitor visitor);
}
