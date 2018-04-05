class Assignment extends Statement{
    public Assignment(String name,Expression expression) {
      this.name=name;
      this.expression=expression;
    }
    String name;
    Expression expression;
    public String getName() {
      return name;
    }
    public Expression getExpression() {
      return expression;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
    
  }