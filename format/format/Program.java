package format;


public class Program {
  
  public String toString() {
    FormatVisitor v = new FormatVisitor();
    this.accept(v);
    return v.getResult();
    
  }
  
  private Function[] functions;
  
  public Function[] getFunctions() {
    return functions;
  }
  
  
  public Program(Function[] functions) {
    super();
    this.functions = functions;
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
