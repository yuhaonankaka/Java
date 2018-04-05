 class Composite extends Statement{
    
    Statement [] statements;
    
    public Composite(Statement [] statements) {
      this.statements=statements;
    }
    public Statement[] getStatements() {
      return statements;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }