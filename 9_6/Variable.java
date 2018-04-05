 class Variable extends Expression{
    String name;
    public Variable(String name) {
      this.name=name;
    }
    public String getName() {
      return name;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }