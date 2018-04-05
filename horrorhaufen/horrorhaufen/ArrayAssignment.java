package horrorhaufen;

public class ArrayAssignment extends Statement{
private String name;
  
  public String getName() {
    return name;
  }
  private Expression index;
  public Expression getIndex() {
    return index;
  }
  private Expression value;
    public Expression getValue() {
      return value;
    }
    
    public ArrayAssignment(String name,Expression index,Expression value) {
      super();
      this.name = name;
      this.index = index;
      this.value = value;
    }
  
  

  @Override
  public void accept(Visitor visitor) {
    // TODO Auto-generated method stub
    visitor.visit(this);
  }

}
