class IfThenElse extends Statement{
    Condition condition;
    Statement thenbranch;
    Statement elsebranch;
    public IfThenElse(Condition condition,Statement thenbranch,Statement elsebranch) {
      this.condition=condition;
      this.thenbranch=thenbranch;
      this.elsebranch=elsebranch;
    }
    public Condition getCond() {
      return condition;
    }
    public Statement getThenBranch() {
      return thenbranch;
    }
    public Statement getElseBranch() {
      return elsebranch;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }