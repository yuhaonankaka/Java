class While extends Statement{
    Condition condition;
    Statement body;
    public While(Condition condition,Statement body) {
      this.condition=condition;
      this.body=body;
    }
    public Condition getCond() {
      return condition;
    }
    public Statement getBody() {
      return body;
    }
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
    
  }