package format;



public class FormatVisitor extends Visitor{
  String result = "";
  public String getResult() {
    return result;
  }
  
  @Override
  public void visit(Number number) {
   result+=number.getValue();
  }

  @Override
  public void visit(Variable variable) {
    result+=variable.getName();
  }

  @Override
  public void visit(Unary unary) {
   
   Expression e = unary.getOperand();
   if(e instanceof Binary) {
     if(((Binary) e).getOperator() ==  Binop.MultiplicationOperator) {
       result+="-";
       unary.getOperand().accept(this);       
     }
     else {
       result+="-(";
       unary.getOperand().accept(this);
       result+=")";
     }
       
   }
   else {
     result+="-(";
     unary.getOperand().accept(this);
     result+=")";
   }
   
  }

  @Override
  public void visit(Binary binary) {
    Expression l = binary.getLhs();
    Expression r = binary.getRhs();
    if(l instanceof Binary) {
      int pofl = ((Binary) l).firstLevelPriority();
      int pofthis = binary.firstLevelPriority();
      if(pofthis < pofl) {
        result+="(";
        binary.getLhs().accept(this);
        result+=")";
      }
      else {
        binary.getLhs().accept(this);
      }
    }
    else{
      binary.getLhs().accept(this);
    }
     
    
      switch (binary.getOperator()) {
        case Minus:
          result+=" - ";
          if(r instanceof Binary) {
            result+="(";
            binary.getRhs().accept(this);
            result+=")";
          }
          else
            binary.getRhs().accept(this);
          
          break;
        case Plus:
          result+=" + ";
          binary.getRhs().accept(this);
          break;
        case MultiplicationOperator:
          result+=" * ";
          if(r instanceof Binary) {
            int pofr = ((Binary) r).firstLevelPriority();
            int pofthis = binary.firstLevelPriority();
            if(pofr > pofthis) {
              result+="(";
              binary.getRhs().accept(this);
              result+=")";
            }
            else
              binary.getRhs().accept(this);
          }
          else
          binary.getRhs().accept(this);
          break;
        case DivisionOperator:
          result+=" / ";
          if(r instanceof Binary) {
            result+="(";
            binary.getRhs().accept(this);
            result+=")";
          }
          else
            binary.getRhs().accept(this);
          break;
        case Modulo:
          result+=" % ";
          result+="(";
          binary.getRhs().accept(this);
          result+=")";
      }
    
  }
  
  @Override
  public void visit(Call call) {
    result+=call.getFunctionName();
    Expression[] e = call.getArguments();
    result+="(";
    for(int i = 0; i<e.length;i++) {
      e[i].accept(this);
      if(i!=e.length-1)
      result+=", ";
    }
    result+=")";
  }
  
  /*
   * Statement
   */

  @Override
  public void visit(Read read) {
   String name = read.getName();
   result+=name+" = read();\n";
   
  }

  @Override
  public void visit(Write write) {
   Expression e = write.getExpression();
   result+="write(";
   e.accept(this);
   result+=")\n";
  }

  @Override
  public void visit(Assignment assignment) {
   Expression e= assignment.getExpression();
   String name = assignment.getName();
   result+=name+" = ";
   e.accept(this);
   result+=";\n";
  }

  @Override
  public void visit(Composite composite) {
   Statement [] s = composite.getStatements();
   for(Statement ss:s) {
     ss.accept(this);
   }
  }

  @Override
  public void visit(IfThenElse ifThenElse) {
   Condition c = ifThenElse.getCond();
   Statement s1 = ifThenElse.getThenBranch();
   Statement s2 = ifThenElse.getElseBranch();
   result+="if(";
   c.accept(this);
   result+=")\n";
   s1.accept(this);
   result+="else\n";
   s2.accept(this);
   
   
  }

  @Override
  public void visit(IfThen ifThen) {
    Condition c = ifThen.getCond();
    Statement s1 = ifThen.getThenBranch();
    result+="if(";
    c.accept(this);
    result+=")\n";
    s1.accept(this);
  }
  
  @Override
  public void visit(While while_) {
    Condition c= while_.getCond();
    Statement s = while_.getBody();
    result+="while (";
    c.accept(this);
    result+=")\n";
    s.accept(this);
        
  }

  @Override
  public void visit(Return return_) {
   Expression e = return_.getExpression();
   result+="return ";
   e.accept(this);
   result+=";\n";
   
  }

  @Override
  public void visit(EmptyStatement emptyStatement) {}
  
  /*
   * Condition
   */

  @Override
  public void visit(True true_) {
    result+="true";
  }

  @Override
  public void visit(False false_) {
    result+="false";
  }
  
  @Override
  public void visit(Comparison comparison) {
    
    comparison.getLhs().accept(this);
    switch (comparison.getOpeator()) {
      case Equals:
        result+=" == ";
        break;
      case NotEquals:
        result+=" 1= ";
        break;
      case Greater:
        result+=" > ";
        break;
      case GreaterEqual:
        result+=" >= ";
        break;
      case Less:
        result+=" < ";
        break;
      case LessEqual:
        result+=" <= ";
        break;
    }
    comparison.getRhs().accept(this);
  }

  @Override
  public void visit(UnaryCondition unaryCondition) {
   result+="!(";
   unaryCondition.getOperand().accept(this);
   result+=")";
  }

  @Override
  public void visit(BinaryCondition binaryCondition) {
    if(!(binaryCondition.getLhs() instanceof BinaryCondition))
    binaryCondition.getLhs().accept(this);
    else {
      result+="(";
      binaryCondition.getLhs().accept(this);
      result+=")";
    }
    
    switch (binaryCondition.getOperator()) {
      case And:
       result+=" && ";
       Condition e = binaryCondition.getRhs();
       if( e instanceof BinaryCondition) {
         if(((BinaryCondition) e).getOperator() == Bbinop.Or) {
           result+="(";
           binaryCondition.getRhs().accept(this);
           result+=")";
         }
         else
           binaryCondition.getRhs().accept(this);
       }
       else
         binaryCondition.getRhs().accept(this);
        break;
      case Or:
        result+=" || ";
          binaryCondition.getRhs().accept(this);
        break;
    }
    
  }
  
  /*
   * Rest
   */
  
  @Override
  public void visit(Declaration declaration) {
   String [] names = declaration.getNames();
   int number = names.length;
   result+="int ";
   for(int i = 0;i<number;i++) {
     if(i==(number-1)) {
       result+=names[i]+";\n";
       break;
     }
       result+=names[i]+",";
   }
  }
  
  @Override
  public void visit(Function function) {
   Declaration[] d = function.getDeclarations();
    String name  = function.getName();
    String[] parameters = function.getParameters();
    Statement [] statements = function.getStatements();
    
    result+="int "+name+" (";
    for(int i = 0;i<parameters.length;i++) {
      if(i==(parameters.length-1)) {
        result+="int"+parameters[i]+") {\n";
        break;
      }
      result+="int "+parameters[i]+",";
    }
    for(Declaration dd :d) {
      dd.accept(this);
    }
    for(Statement s:statements) {
      s.accept(this);
    }
    result+="}\n";
    
  }

  @Override
  public void visit(Program program) {
   for(Function f:program.getFunctions()) {
     f.accept(this);
   }
  }
  

  
  
}
