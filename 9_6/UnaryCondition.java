 class UnaryCondition extends Condition{
    Bunop operator;
    Condition operand;
    public UnaryCondition(Bunop operator,Condition operand) {
      this.operator=operator;
      this.operand=operand;
    }
    public Bunop getOperator() {
      return operator;
    }
    public Condition getOperand() {
      return operand;
    }  
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }