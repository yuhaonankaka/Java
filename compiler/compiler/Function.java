package compiler;

public class Function {
  private String name;

  public String getName() {
    return name;
  }

  private String[] parameters;

  public String[] getParameters() {
    return parameters;
  }

  private Declaration[] delcarations;

  public Declaration[] getDelcarations() {
    return delcarations;
  }

  private Statement[] statements;

  public Statement[] getStatements() {
    return statements;
  }

  public Function(String name, String[] parameters, Declaration[] delcarations,
      Statement[] statements) {
    super();
    this.name = name;
    this.parameters = parameters;
    this.delcarations = delcarations;
    this.statements = statements;
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
