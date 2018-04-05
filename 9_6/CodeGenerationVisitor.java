import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CodeGenerationVisitor extends Visitor{
  int [] program;
  List<String> l = new ArrayList<String>();
  Hashtable<String, Integer> labels = new Hashtable<>();
  
  static void error(String message) {
    throw new RuntimeException(message);
  }
  public int[] getProgram() {
    return this.program;
  }
  
  public void visit(Program program) {
    System.out.println("Program ruft!");
    for(Function f:program.getFunctions()) {
      
      if(f.name.equals("main")) {
        f.accept(this);
        String h = "HALT\n";
       
        l.add(h);
      }
    }
    
    
    for(Function f:program.getFunctions()) {
      if(!(f.name.equals("main"))) {
        f.accept(this);
        String h = "RETURN ";
        int n  = f.getDeclarations().length+f.getParameters().length;
        h=h+n+"\n";
        
        l.add(h); 
      }
    }
    
     String ss="";
    for(int i = 0;i<l.size();i++) {
     ss = ss+l.get(i);
    }
    int [] p = Interpreter.parse(ss);
    this.program=p;
  
  }
  
  public void visit(Assignment assignment) {
    // TODO Auto-generated method stub
    String name = assignment.getName();
    Expression e = assignment.getExpression();
    e.accept(this);
    if(!labels.containsKey(name))
      error("This is a not valid Viriable name!!");
    String s = "STS "+labels.get(name)+"\n";
    l.add(s);
  }

  public void visit(Binary binary) {
    System.out.println("Binary ruft!");
    // TODO Auto-generated method stub
    
    String s="";
   
     // if(binary.getOperator().equals(Binop.Minus)) {
        binary.getRhs().accept(this);
        binary.getLhs().accept(this);
        switch (binary.getOperator()) {
          case Minus: {
            s=s+"SUB"+"\n";
            
            break;
          }
          case Plus: {
            s=s+"ADD"+"\n";
           
            
            break;
          }
          case MultiplicationOperator: {
            s=s+"MUL"+"\n";
            
            
            break;
          }
          case DivisionOperator: {
            s=s+"DIV"+"\n";
                       
            break;
          }
          case Modulo: {
            s=s+"MOD"+"\n";
                       
            break;
          }
          default:
            System.out.println("Check the input binary operator!! ");
            break;
        }
        
        l.add(s);
          
   //   }
    
      
    
  }

  public void visit(Expression expression) {
    // TODO Auto-generated method stub
    
  }

  public void visit(BinaryCondition binaryCondition) {
    // TODO Auto-generated method stub
    binaryCondition.getRhs().accept(this);
    binaryCondition.getLhs().accept(this);
    if(binaryCondition.operator.equals(Bbinop.And)) {
      String s= "OR\n";
      l.add(s);
    }
    if(binaryCondition.operator.equals(Bbinop.Or)) {
      String s= "AND\n";
      l.add(s);
    }
    
    
  }

  public void visit(Call call) {
    // TODO Auto-generated method stub
    String functionname = call.getFunctionName();
    
    
    
    
    Expression [] e=call.getArguments();
    int num = e.length;
    for(int i = num-1;i>-1;i--) {
      e[i].accept(this);
    }
    String s = ""+"LDI "+functionname+"\n";
    s=s+"CALL "+num+"\n";
    l.add(s);
    
    
    
  }

  public void visit(Comparison comparison) {
    // TODO Auto-generated method stub
    comparison.getRhs().accept(this);
    comparison.getLhs().accept(this);
    if(comparison.operator.equals(Comp.Greater)) {
      String s= "LE\n";
      l.add(s);
    }
    if(comparison.operator.equals(Comp.NotEquals)) {
      String s= "EQ\n";
      l.add(s);
    }
    if(comparison.operator.equals(Comp.Equals)) {
      String s= "EQ\nNOT\n";
      l.add(s);
    }
    if(comparison.operator.equals(Comp.GreaterEqual)) {
      String s= "LT\n";
      l.add(s);
    }
    if(comparison.operator.equals(Comp.LessEqual)) {
      String s= "LT\nNOT\n";
      l.add(s);
    }
    if(comparison.operator.equals(Comp.Less)) {
      String s= "LE\nNOT\n";
      l.add(s);
    }
    
  }

  public void visit(Composite composite) {
    // TODO Auto-generated method stub
    Statement [] s = composite.getStatements();
    for(Statement sss:s){
      sss.accept(this);
    }
  }

  public void visit(Declaration declaration) {
    // TODO Auto-generated method stub
    String [] names = declaration.getNames();
    int i =1;
    for(String name:names) {
      labels.put(name, i);
      i++;
    }
    int d = declaration.getNames().length;
    String s = "ALLOC "+d+"\n";
    l.add(s);
  }

  public void visit(Function function) {
    System.out.println("function ruft!");
    // TODO Auto-generated method stub
    String name = function.getName();
    if(!name.equals("main")) {
      l.add(name+":"+"\n");
    }
    Declaration [] decl = function.getDeclarations();
    for(Declaration d : decl) {
      d.accept(this);
    }
    
    String [] parameters = function.getParameters();
    int num = parameters.length;
    for(int i = num-1;i>-1;i--) {
      
      labels.put(parameters[num-1-i], -i);
    }
    
    Statement [] sta = function.getStatements();
    for(Statement s:sta) {
      
      
      
      if(s instanceof IfThen) {
        Statement state = ((IfThen) s).getThenBranch();
        Condition con = ((IfThen) s).getCond();
        if (state instanceof Return) {
          con.accept(this);
          l.add("JUMP "+"then"+l.size()+"\n");
          String st = "then"+(l.size()-1)+":"+"\n";
       
         state.accept(this);
          String str = "RETURN "+num+"\n";
          l.add(str);
          l.add(st);
          continue;
        }
      }
      if(s instanceof IfThenElse) {
        Statement state = ((IfThenElse) s).getThenBranch();
        Statement elsebran = ((IfThenElse) s).getElseBranch();
        Condition con = ((IfThenElse) s).getCond();
        if (state instanceof Return && elsebran instanceof Return) {
          con.accept(this);
          l.add("JUMP "+"then"+l.size()+"\n");
          String st = "then"+(l.size()-1)+":"+"\n";
       
         state.accept(this);
          String str = "RETURN "+num+"\n";
          l.add(str);
          l.add(st);
          elsebran.accept(this);
          l.add(str);
          continue;
        }
        else if (state instanceof Return ) {
          con.accept(this);
          l.add("JUMP "+"then"+l.size()+"\n");
          String st = "then"+(l.size()-1)+":"+"\n";
       
         state.accept(this);
          String str = "RETURN "+num+"\n";
          l.add(str);
          l.add(st);
          elsebran.accept(this);
          
          continue;
        }
        else if (elsebran instanceof Return) {
          con.accept(this);
          l.add("JUMP "+"then"+l.size()+"\n");
          String st = "then"+(l.size()-1)+":"+"\n";
       
         state.accept(this);
          String str = "RETURN "+num+"\n";
          
          l.add(st);
          elsebran.accept(this);
          l.add(str);
          continue;
        }
      }
      s.accept(this);
    }
    
  }

  public void visit(IfThen ifThen) {
    // TODO Auto-generated method stub
    Condition c = ifThen.getCond();
    c.accept(this);
    l.add("JUMP "+"then"+l.size()+"\n");
    String s = "then"+(l.size()-1)+":"+"\n";
   Statement ifthen = ifThen.getThenBranch();
   
   ifthen.accept(this);
   
   l.add(s);
  }

  public void visit(IfThenElse ifThenElse) {
    // TODO Auto-generated method stub
    Condition c = ifThenElse.getCond();
    c.accept(this);
    l.add("JUMP "+"then"+l.size()+"\n");
    String s = "then"+(l.size()-1)+":"+"\n";
   Statement ifthen = ifThenElse.getThenBranch();
   
   ifthen.accept(this);
   
   l.add(s);
   Statement ifelse = ifThenElse.getElseBranch();
   ifelse.accept(this);
  }

  public void visit(Number number) {
    System.out.println("Number ruft!");
    // TODO Auto-generated method stub
    String s = "LDI"+" "+number.getValue()+"\n";
   l.add(s);
  }

  public void visit(Read read) {
    // TODO Auto-generated method stub
    l.add("IN \n");
    l.add("STS "+labels.get(read.getName())+"\n");
  }

  public void visit(Return return1) {
    System.out.println("Return ruft!");
    
    // TODO Auto-generated method stub
    Expression e = return1.getExpression();
    e.accept(this);
    
   
    
  }

  public void visit(Unary unary) {
    // TODO Auto-generated method stub
    unary.getOperand().accept(this);
    String s="LDI 0\n"+"SUB\n";
    l.add(s);
  }

  public void visit(UnaryCondition unaryCondition) {
    // TODO Auto-generated method stub
    Condition c = unaryCondition.getOperand();
    c.accept(this);
    
    l.add("LDI -1\n");
    l.add("MUL\n");
    l.add("LDI -1\n");
    l.add("ADD\n");
    
  }
  
  public void visit(Statement statement) {
    // TODO Auto-generated method stub
    
  }

  public void visit(Variable variable) {
    // TODO Auto-generated method stub
    int a = labels.get(variable.getName());
    String s = ""+"LDS "+a+"\n";
    l.add(s);
  }

  public void visit(While while1) {
    // TODO Auto-generated method stub
    int lsizestart=l.size();
    String start = "whilestart"+l.size()+":"+"\n";
    l.add(start);
    Condition c = while1.getCond();
    c.accept(this);
    int size = l.size();
    String s = "JUMP "+"whileend"+l.size()+"\n";
    l.add(s);
    Statement sta = while1.getBody();
    sta.accept(this);
    l.add("LDI -1\n");
    String back = "JUMP "+"whilestart"+lsizestart+"\n";
    l.add(back);
    
    String end = "whileend"+size+":"+"\n";
    l.add(end);
    
  }

  public void visit(Write write) {
    // TODO Auto-generated method stub
    Expression e =  write.getExpression();
    e.accept(this);
    l.add("OUT\n");
  }

  public void visit(True true1) {
    // TODO Auto-generated method stub
    l.add("LDI 0\n");
  }
  
  public void visit(False false1) {
    // TODO Auto-generated method stub
    l.add("LDI -1\n");
  }
  
  
  
  
  
 
  
  
}
