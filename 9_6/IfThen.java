class IfThen extends Statement{
   
    Condition condition;
    Statement then;
    
    public IfThen(Condition condition,Statement then) {
      this.condition=condition;
      this.then=then;
    }
    public Condition getCond() {
      return condition;
    }
    public Statement getThenBranch() {
      return then;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }