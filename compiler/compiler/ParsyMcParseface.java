package compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParsyMcParseface {
  private String [] program;
  private int from;
  
  
  public ParsyMcParseface(String program){
    this.program = lex(program);
    from = 0;
  }
  public String [] getProgram() {
    return program;
  }
  
  
  public static String readProgramConsole() {
    @SuppressWarnings("resource")
    Scanner sin = new Scanner(System.in);
    StringBuilder builder = new StringBuilder();
    while (true) {
      String nextLine = sin.nextLine();
      if (nextLine.equals("")) {
        nextLine = sin.nextLine();
        if (nextLine.equals(""))
          break;
      }
      if (nextLine.startsWith("//"))
        continue;
      builder.append(nextLine);
      builder.append('\n');
    }
    return builder.toString();
  }

  private static final int PADDING = 3;

  public static String[] lex(String input) {
    ArrayList<String> tokens = new ArrayList<>();
    int index = 0;
    while (index < input.length()) {
      char next = input.charAt(index);
      if (next == ' ' || next == '\n' || next == '\r' || next == '\t') {
        index++;
        continue;
      }
      switch (next) {
        case '{':
        case '}':
        case '(':
        case ')':
        case '+':
        case '-':
        case '/':
        case '*':
        case '%':
        case ';':
          index++;
          tokens.add("" + next);
          continue;

      }
      StringBuilder tokenBuilder = new StringBuilder();
      compBinAssLoop: while (index < input.length()) {
        char nextOp = input.charAt(index);
        switch (nextOp) {
          case '=':
          case '!':
          case '<':
          case '>':
          case '&':
          case '|':
            tokenBuilder.append(nextOp);
            break;
          default:
            break compBinAssLoop;
        }
        index++;
      }
      if (tokenBuilder.length() == 0) {
        while (index < input.length()) {
          char nextLetterNumber = input.charAt(index);
          if (nextLetterNumber >= 'a' && nextLetterNumber <= 'z'
              || nextLetterNumber >= 'A' && nextLetterNumber <= 'Z'
              || nextLetterNumber >= '0' && nextLetterNumber <= '9')
            tokenBuilder.append(nextLetterNumber);
          else
            break;
          index++;
        }
      }
      if (tokenBuilder.length() > 0)
        tokens.add(tokenBuilder.toString());
      else {
        index++;
        tokens.add("" + next);
      }
    }
    // Padding
    for (int i = 0; i < PADDING; i++)
      tokens.add("");
    return tokens.toArray(new String[0]);
  }

  private Number parseNumber() {
    for (int i = 0; i < program[from].length(); i++) {
      char next = program[from].charAt(i);
      if (!(next >= '0' && next <= '9')) {
        return null;
      }
    }
    int value = Integer.parseInt(program[from]);
    Number num = new Number(value);
    from++;
    return num;
  }

  public static boolean isKeyword(String token) {
    switch (token) {
      case "true":
      case "false":
      case "for":
      case "while":
      case "if":
      case "else":
      case "read":
      case "write":
        return true;
      default:
        return false;
    }
  }

  private Variable parseName() {
    if (isKeyword(program[from]))
      return null;
    if (program[from].length() == 0)
      return null;
    char first = program[from].charAt(0);
    if (!(first >= 'a' && first <= 'z') && !(first >= 'A' && first <= 'Z'))
      return null;
    for (int i = 1; i < program[from].length(); i++) {
      char next = program[from].charAt(i);
      if (!(next >= 'a' && next <= 'z') && !(next >= 'A' && next <= 'Z')
          && !(next >= '0' && next <= '9')) {
        return null;
      }
    }
    Variable v = new Variable(program[from]);
    from++;
    return v;
  }

  private void parseType() {
    if (program[from].equals("int"))
      from++;
    /*else
      System.out.println("Wrong declaration!!Not an Integer!!"); */
  }

  private Declaration parseDecl() {
    int f = from;
    parseType();
    if (from == f) {
      return null;
    }
List<String> strarray = new ArrayList<String>();
    while (true) {
      Variable v = parseName();
      if (v==null)
        {
        throw new RuntimeException("Not a valid name!");
        //return null;
        }
      if (!program[from].equals(",")) {
        strarray.add(v.getName());
        break;
      }
      strarray.add(v.getName());
      from++;
    }
    if (program[from].equals(";"))
      from += 1;
    else
      throw new RuntimeException("there is no ; at the end!");
      //return null;
    String [] s = new String [strarray.size()];
    for(int i = 0;i<s.length;i++) {
      s[i]=strarray.get(i);
    }
    Declaration d = new Declaration(s);
    return d;
  }

  private Unop parseUnop() {
    if (program[from].equals("-")) {
      from++;
      return Unop.Minus;
    }
      
    else return null;
  } //TODO

  private Binop parseBinop() {
    switch (program[from]) {
      case "+":
          from++;
          return Binop.Plus;
      case "-":
        from++;
        return Binop.Minus;
      case "*":
        from++;
        return Binop.MultiplicationOperator;
      case "/":
        from++;
        return Binop.DivisionOperator;
      case "%":
        from++;
        return Binop.Modulo;
        
      default:
        return null;
    }
  }//TODO

  private Comp parseComp() {
    switch (program[from]) {
      case "==":
              from++;
              return Comp.Equals;
      case "!=":
        from++;
        return Comp.NotEquals;
      case "<=":
        from++;
        return Comp.LessEqual;
      case "<":
        from++;
        return Comp.Less;
      case ">=":
        from++;
        return Comp.GreaterEqual;
      case ">":
        from++;
        return Comp.Greater;
        
      default:
        return null;
    }
  }

  private Expression parseExpressionNoBinop() {
    // <number>
        Number num = parseNumber();
        
    if (num !=null) {
      
      return num;
    }
      
    // <name>
    Variable v = parseName();
    if (v!=null) {
      if(!program[from].equals("("))
        return v;
      else {
        from++;
        Expression e = parseExpression();
        List<Expression> exp = new ArrayList<Expression>();
        if (e==null) {
          if (program[from].equals(")"))
            return new Call(v.getName(),new Expression[] {});
          else
            throw new RuntimeException("There is no ) !");
            //return null;
        }
        else {
          while(true) {
            exp.add(e);
            if(program[from].equals(",")) {
              from++;
              e=parseExpression();
              if (e==null)
                throw new RuntimeException("Wrong Expression!");
                //return null;
            }
            else if(program[from].equals(")")) {
              from++;
              break;
            }
            else
              throw new RuntimeException("Here should be ) or , but none if them are here!");
              //return null;
          }
          
          Expression [] ep =new Expression[exp.size()];
          for(int i =0;i<ep.length;i++) {
            ep[i]=exp.get(i);
          }
          return new Call(v.getName(),ep);
          
        }
          
      }
    }
      
    // (<expr>)
    if (program[from].equals("(")) {
      from += 1;
      Expression exp = parseExpression();
      if (exp==null)
        throw new RuntimeException("Here is a invalid expression!");
        //return null;
      if (program[from].equals(")")) {
        from++;
        return exp;
      }
      else
        throw new RuntimeException("Lack of ( !");
       // return null;
    }
    // <unop> <expr>
    Unop u = parseUnop();
    
    if (u!=null) {
     
      Expression ex = parseExpression();
      Unary un = new Unary(u,ex);
      return un;
     
    }
    else
      return null;
  }

  private Expression parseExpression() {
    Expression e1 = parseExpressionNoBinop();
    if (e1==null)
      throw new RuntimeException("Here is a invalid expression!");
      //return null;

    while (true) {
      Binop bin = parseBinop();
      if (bin==null)
        break;
       Expression e2 = parseExpressionNoBinop();
      if (e2 == null)
        //return null;
      throw new RuntimeException("Here is a invalid expression!");
      e1 = new Binary(e1,bin,e2);
      
    }
    return e1;
  }

  private Bbinop parseBbinop() {
    switch (program[from]) {
      case "&&":
        from++;
        return Bbinop.And;
      case "||":
        from++;
        return Bbinop.Or;
      default:
        return null;
    }
  }
  
  private Bunop parseBunop() {
    if (program[from].equals("!")) {
      from++;
      return Bunop.Not;
    }
      
    return null;
  }

  private Condition parseConditionNoBinary() {
    // true
    if (program[from].equals("true")) {
      from++;
      return new True();     
    }

    // false
    if (program[from].equals("false")) {
      from++;
      return new False();    
    }
    // (<cond>)
    if (program[from].equals("(")) {
      from += 1;
      Condition con = parseCondition();
      if (program[from].equals(")")) {
        
        from++;
        return con;
      }
        
      else
        throw new RuntimeException("Here lacks of )");
        //return null;
    }
    Bunop bun = parseBunop();
    if(bun != null) {

      if (program[from].equals("(")) {
        from += 1;
        Condition con = parseCondition();
        if (program[from].equals(")")) {
          UnaryCondition uc = new UnaryCondition(bun,con);
          from++;
          return uc;
        }
          
        else
          throw new RuntimeException("Here lacks of )");
          //return null;
      } 
    }
    // <expr> <comp> <expr>
    Expression expr = parseExpression();
    if (expr !=null) {
      Comp co = parseComp();
      if (co == null)
       // return null;
      throw new RuntimeException("Here is a invalid comparision sign!");
      Expression expr2 = parseExpression();
      if(expr2==null)
        //return null;
      throw new RuntimeException("Here is a invalid expression!");
      Comparison compari = new Comparison(expr,co,expr2);
      return compari;
    }
    //return null;
    throw new RuntimeException("Here is a invalid expression!");
  }

  private Condition parseCondition() {
    Condition con1 = parseConditionNoBinary();
    if (con1==null)
      //return null;
    throw new RuntimeException("Here is a invalid Condition!");

    while (true) {
      Bbinop binop = parseBbinop();
      if (binop == null)
        break;
      Condition con2 = parseConditionNoBinary();
      if (con2 ==null)
        //return null;
        throw new RuntimeException("Here is a invalid Condition!");
      con1 = new BinaryCondition(con1,binop,con2);
    }
    return con1;
  }

  private Statement parseAssigment() {
    Variable v = parseName();
    if (v==null)
      return null;
      
    if (!program[from].equals("="))
     // return null;
    throw new RuntimeException("Here lacks of = ");
    from += 1;
    if (from + 2 < program.length
        && (program[from] + program[from + 1] + program[from + 2]).equals("read()")) {
      from = from+3;
      return new Read(v.getName());
      
    }
      
    Expression ex = parseExpression();
    return new Assignment(v.getName(),ex);
  }

  private Condition parseWhileIteCond() {
    if (program[from].equals("("))
      from++;
    else
      throw new RuntimeException("Here lacks of ( !");
      //return null;
    Condition con = parseCondition();
    if (con==null)
      //return con;
    throw new RuntimeException("Here is a invalid Condition!");
    if (!program[from].equals(")")) {
      //return null;
      throw new RuntimeException("Here lacks of ) !");
    }
    from++;
    return con;
  }

  private Statement parseIte() {
    if (!program[from].equals("if"))
      //return null;
    throw new RuntimeException("Should be an If !!");
    from++;
    Condition con = parseWhileIteCond();
    if (con==null)
      throw new RuntimeException("Here is a invalid Condition!");
      //return null;
    Statement st = parseStatement();
    if (st == null)
      return null;
    if (from < program.length && program[from].equals("else")) {
      from++;
      Statement st2 = parseStatement();
      IfThenElse ifte = new IfThenElse(con,st,st2);  
      return ifte;
      
    }
    IfThen ift = new IfThen(con,st);  
    return ift;
  }

  private Statement parseStatement() {
    switch (program[from]) {
      case ";":
        from++;
        return new EmptyStatement();
      case "{":
        from++;
        List<Statement> stlist = new ArrayList<Statement>();
        Statement s = parseStatement();
        if(s==null)
         // return null;
        throw new RuntimeException("Here is a invalid Statement!");
        stlist.add(s);
        
        do {
                    s = parseStatement();
                    if(s!=null)
                    stlist.add(s);
        } while (s != null);
        if (program[from].equals("}")) {
          from++;
          Statement [] sa = new Statement [stlist.size()];
          for(int i = 0;i<sa.length;i++) {
            sa[i]=stlist.get(i);
          }
          Composite compo = new Composite(sa);
          return compo;
        }
          
        else
          throw new RuntimeException("Here is a invalid Statement!should have } ");
          //return null;
      case "while":
        from++;
        Condition con = parseWhileIteCond();
        if (con==null)
          //return null;
        throw new RuntimeException("Here is a invalid Statement!");
        Statement s2 = parseStatement();
        if(s2==null)
          //return null;
        throw new RuntimeException("Here is a invalid Statement!");
        While wh = new While(con,s2);
        return wh;
      case "if":
        return parseIte();
        
      case "write":
        if (!program[from + 1].equals("("))
          //return null;
        throw new RuntimeException("Here is a invalid Statement! should have ( ");
        from=from+2;
        Expression ex = parseExpression();
        if (ex==null || !program[from].equals(")"))
          //return null;
        throw new RuntimeException("Here is a invalid Statement!no ) or ivalid expression!");
        if (!program[from + 1].equals(";"))
          //return null;
        throw new RuntimeException("lacks of ;");
        from = from + 2;
        Write wr = new Write(ex);
        return wr;
      case "return":
        from++;
        Expression e = parseExpression();
        if (e==null)
          //return null;
        throw new RuntimeException("must have an expression ");
        if(program[from].equals(";"))
        {
          from++;
          Return r = new Return(e);
          return r;
        }
        else
          throw new RuntimeException("no ;!");
          //return null;
      default: {
        Statement ass = parseAssigment();
        if (ass != null) {
          if (program[from].equals(";")) {
            from++;
            return ass;
          }
            
          else
            throw new RuntimeException("no ;!");
            //return null;
        }
        return null;
        //throw new RuntimeException("invalid Statement");
      }
    }
  }
  
  
  private  String [] parseParameters() {
    if(program[from].equals(")")){
      return new String[] {};
    }
    int num = from;
    parseType();
    if(num==from)
      //return null;
    throw new RuntimeException("no int as type !");
    Variable v = parseName();
    if(v==null)
      //return null;
    throw new RuntimeException("invalid name ");
    List<String> namelist = new ArrayList<String>();
    namelist.add(v.getName());
    while(program[from].equals(",")) {
      from++;
      num = from;
      parseType();
      if(num==from)
        //return null;
      throw new RuntimeException("no int as type !");
      v=parseName();
      if(v==null)
       // return null;
      throw new RuntimeException("invalid name ");
      namelist.add(v.getName());
    }
    String [] s = new String [namelist.size()];
    for(int i = 0;i<s.length;i++) {
      s[i]=namelist.get(i);
    }
    return s;
    
  }
  
  private  Function parseFunction() {
    //分别找出各个属性，然后建立LIst，然后导入；
    int n = from;
    parseType();
    if(n==from)
      //return null;
    throw new RuntimeException("no int before ");
    Variable v = parseName();
    if(v==null)
      //return null;
    throw new RuntimeException("invalid name ");
    if(program[from].equals("("))
      from++;
    else
      //return null;
    throw new RuntimeException("no ( ");
    String [] s = parseParameters();
    if(s==null)
      //return null;
    throw new RuntimeException("invalid parameters ");
    if(program[from].equals(")"))
      from++;
    else
      //return null;
    throw new RuntimeException("no ) ");
    if(program[from].equals("{"))
      from++;
    else
      //return null;
    throw new RuntimeException("no { ");
    //一堆Declaration
    List<Declaration> decl = new ArrayList<Declaration> ();
    Declaration dec = parseDecl();
    
    while(dec!=null) {
      decl.add(dec);
      dec = parseDecl();
      
    }
    
    Declaration[] decla = new Declaration [decl.size()];
    for(int i = 0;i<decla.length;i++) {
      decla[i]=decl.get(i);
    }
    //一堆Statement
    List<Statement> statlist = new ArrayList<Statement> ();
    Statement st = parseStatement();
    while(st!=null) {
      statlist.add(st);
      st = parseStatement();
    }
    
    Statement [] state = new Statement [statlist.size()];
    for(int i = 0;i<state.length;i++) {
      state[i]=statlist.get(i);
    }
    
    if(program[from].equals("}"))
      from++;
    else
      //return null;
    throw new RuntimeException("no } ");
    Function f = new Function(v.getName(),s,decla,state);
    return f;
  }

 
  
  public Program parse() {
   // 同上；
    List<Function> fl = new ArrayList<Function>();
    Function f=parseFunction();
    if (f==null)
      //return null;
    throw new RuntimeException("no functions ");
    while(f!=null) {
      fl.add(f);
      if(from == program.length- PADDING)
        break;
      f=parseFunction();
      if (f==null)
        //return null;
      throw new RuntimeException("invalid function ");
    }
    Function [] func = new Function[fl.size()];
    for(int i =0;i<func.length;i++) {
      func[i]=fl.get(i);
    }
    return new Program(func);
    
  }
  
}

  /* public static void main(String[] args) {
    String textProgram = readProgramConsole();
    // System.out.println("Read program, lexing...");
    String[] program = lex(textProgram);

//    System.out.println(java.util.Arrays.toString(program));

    int parsed = parseProgram(program);
    if (parsed < 0)
      System.out.println("Error: Unable to parse program");
    else
      System.out.println("Parse successful.");
  }
}
*/
