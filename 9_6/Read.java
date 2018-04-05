class Read extends Statement{
    String name;
    public Read(String name) {
      this.name=name;
    }
    public String getName() {
      return name;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }