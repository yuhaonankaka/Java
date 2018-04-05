package optimizer;

public class Write extends Statement {
  private Expression expression;
  
  public Expression getExpression() {
    return expression;
  }
  
 public Write(Expression ex) {
   this.expression=ex;
 }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
