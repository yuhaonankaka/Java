package horrorhaufen;

public class ArrayInitializer extends Expression{
private Expression size;
public Expression getSize() {
  return size;
}
public ArrayInitializer(Expression size) {
  super();
  this.size=size;
}
  @Override
  public void accept(Visitor visitor) {
    // TODO Auto-generated method stub
    visitor.visit(this);
  }

}
