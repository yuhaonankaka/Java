class BinaryCondition extends Condition{
    Condition lhs;
    Bbinop operator;
    Condition rhs;
    public BinaryCondition(Condition lhs,Bbinop operator,Condition rhs) {
      this.lhs=lhs;
      this.operator=operator;
      this.rhs=rhs;
    }
    public Condition getLhs() {
      return lhs;
    }
    public Bbinop getOperator() {
      return operator;
    }
    public Condition getRhs() {
      return rhs;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }