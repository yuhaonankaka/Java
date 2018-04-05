class Comparison extends Condition{
    Expression lhs;
    Comp operator;
    Expression rhs;
    public Comparison(Expression lhs,Comp operator,Expression rhs) {
      this.lhs=lhs;
      this.operator=operator;
      this.rhs=rhs;
    }
    public Expression getLhs() {
      return lhs;
    }
    public Comp getOperator() {
      return operator;
    }
    public Expression getRhs() {
      return rhs;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }