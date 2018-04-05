package format;


public class Binary extends Expression {
  private Expression lhs;
  
  public Expression getLhs() {
    return lhs;
  }
  
  private Binop operator;
  
  public Binop getOperator() {
    return operator;
  }
  
  private Expression rhs;
  
  public Expression getRhs() {
    return rhs;
  }
  
  public Binary(Expression lhs, Binop operator, Expression rhs) {
    super();
    this.lhs = lhs;
    this.operator = operator;
    this.rhs = rhs;
  }
  
  public int firstLevelPriority() {
    switch (operator) {
      case Minus:
        return 4;
      case Plus:
        return 4;
      case MultiplicationOperator:
        return 3;
      case DivisionOperator:
        return 3;
      case Modulo:
        return 3;
      default:
        return -1;
    }
  }
  

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
