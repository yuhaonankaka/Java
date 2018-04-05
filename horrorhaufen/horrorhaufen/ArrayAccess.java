package horrorhaufen;

public class ArrayAccess extends Expression{
  private Expression name;
  public Expression getName() {
    return name;
  }
  private Expression index;
  public Expression getIndex() {
    return index;
  }
  public ArrayAccess (Expression name,Expression index) {
    super();
    this.name = name;
    this.index = index;
  }

  @Override
  public void accept(Visitor visitor) {
    // TODO Auto-generated method stub
    visitor.visit(this);
  }

}
