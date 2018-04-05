 class Call extends Expression{
    String functionname;
    Expression [] arguments;
    public Call(String functionname,Expression[] arguments) {
      this.functionname=functionname;
      this.arguments=arguments;
    }
    public String getFunctionName() {
      return functionname;
    }
    public Expression[] getArguments() {
      return arguments;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }