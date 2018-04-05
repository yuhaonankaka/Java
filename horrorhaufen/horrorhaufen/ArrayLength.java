package horrorhaufen;

public class ArrayLength extends Expression{
private Expression name;
public Expression getName() {
  return name;
}
public ArrayLength(Expression name) {
  super();
  this.name = name;
}
  @Override
  public void accept(Visitor visitor) {
    // TODO Auto-generated method stub
    visitor.visit(this);
  }

}
