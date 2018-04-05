package format;

public abstract class Expression {
  public abstract void accept(Visitor visitor);
  public String toString() {
    FormatVisitor v = new FormatVisitor();
    this.accept(v);
    return v.getResult();
    
  }
}

