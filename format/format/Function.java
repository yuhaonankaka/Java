package format;

public class Function {
  public String toString() {
    FormatVisitor v = new FormatVisitor();
    this.accept(v);
    return v.getResult();
    
  }
  
  private String name;

  public String getName() {
    return name;
  }

  private String[] parameters;

  public String[] getParameters() {
    return parameters;
  }

  private Declaration[] declarations;

  public Declaration[] getDeclarations() {
    return declarations;
  }

  private Statement[] statements;

  public Statement[] getStatements() {
    return statements;
  }

  public Function(String name, String[] parameters, Declaration[] declarations,
      Statement[] statements) {
    super();
    this.name = name;
    this.parameters = parameters;
    this.declarations = declarations;
    this.statements = statements;
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
