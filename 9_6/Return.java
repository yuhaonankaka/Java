class Return extends Statement{
    Expression expression;
    public Return(Expression expression) {
      this.expression=expression;
    }
    public Expression getExpression() {
      return expression;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }