package horrorhaufen;

public class Function {
private Type type;
  
  public Type getType() {
    return type;
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

  public Function(Type type, String name, String[] parameters, Declaration[] declarations,
      Statement[] statements) {
    super();
    this.type = type;
    this.name = name;
    this.parameters = parameters;
    this.declarations = declarations;
    this.statements = statements;
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
