package horrorhaufen;

public abstract class Statement {
  public abstract void accept(Visitor visitor);
}
