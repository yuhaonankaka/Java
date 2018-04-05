class Number extends Expression{
    int value;
    public Number(int value) {
      this.value=value;
    }
    public int getValue() {
      return value;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }