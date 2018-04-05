class Binary extends Expression{
    Expression lhs;
    Binop operator;
    Expression rhs;
    public Binary(Expression lhs,Binop operator,Expression rhs) {
      this.lhs=lhs;
      this.operator=operator;
      this.rhs=rhs;
    }
    public Expression getLhs() {
      return lhs;
    }
    public Binop getOperator() {
      return operator;
    }
    public Expression getRhs() {
      return rhs;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }