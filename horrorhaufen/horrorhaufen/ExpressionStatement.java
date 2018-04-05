package horrorhaufen;

public class ExpressionStatement extends Statement {
  private Expression expression;
  
  public Expression getExpression() {
    return expression;
  }
  
  public ExpressionStatement(Expression expression) {
    super();
    this.expression = expression;
  }

  @Override
  public void accept(Visitor visitor) {
  }

}
