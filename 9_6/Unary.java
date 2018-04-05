 
  class Unary extends Expression{
    Unop operator;
    Expression operand;
    public Unary(Unop operator,Expression operand) {
      this.operator=operator;
      this.operand=operand;
    }
    public Unop getOperator() {
      return operator;
    }
    public Expression getOperand() {
      return operand;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }