import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MiniJavaParser {
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

 public static String[] lex(String program) {
   
   char [] s1 = program.toCharArray();
   List<String> s2=new ArrayList<String>();
   String temp = "";
   for (int i =0;i<s1.length;i++) {
     
     // System.out.println(s1[i]);
     if(isnol(s1[i]))
       temp=temp+s1[i];
     else if(s1[i]=='=' && s1[i+1]=='=') {
       s2.add(temp);
       s2.add("==");
       temp="";
       i++;
     }
     else if(s1[i]=='<' && s1[i+1]=='=') {
       s2.add(temp);
       s2.add("<=");
       temp="";
       i++;
     }
     else if(s1[i]=='>' && s1[i+1]=='=') {
       s2.add(temp);
       s2.add(">=");
       temp="";
       i++;
     }
     else if(s1[i]=='!' && s1[i+1]=='=') {
       s2.add(temp);
       s2.add("!=");
       temp="";
       i++;
     }
     else if(s1[i]=='&' && s1[i+1]=='&') {
       s2.add(temp);
       s2.add("&&");
       temp="";
       i++;
     }
     else if(s1[i]=='|' && s1[i+1]=='|') {
       s2.add(temp);
       s2.add("||");
       temp="";
       i++;
     }
     
     else {
       s2.add(temp);
       s2.add(String.valueOf(s1[i]));
       temp="";
     }
   
     
   }
   
   
   for (int i =0;i<s2.size();i++) {
     String str = s2.get(i);
    if (str.equals(" ")||str.equals("\n") || str.equals("")) {
      s2.remove(i);
      i--;
    }
   }
   // 转换成String数组，然后把空白的，空格，还有/n字符都去掉；
   
   String [] result = s2.toArray(new String [0]); 
   for (int i =0;i<result.length;i++) {
     result[i]=result[i]+"";
   }
   
   return result;  
 }

 public static int parseNumber(String[] program, int from) {
   if (from==-1) {
     System.out.println("There is something wrong");
     return -1;
   }
 String number = program[from];
 for(int i = 0;i<number.length();i++) {
   if (number.charAt(i)>=48 && number.charAt(i)<=57) {
     continue;
   }
   else return -1;
 }
   return from+1; // Todo
 }

 public static int parseName(String[] program, int from) {
   if (from==-1) {
     System.out.println("There is something wrong");
     return -1;
   }
   
   
 String name = program[from];
 if(name.equals("int")||name.equals("write")||name.equals("read")||name.equals("if")||name.equals("else")||
     name.equals("true")||name.equals("false")||name.equals("while")) {
   return -1;
 }
 if (isletter(name.charAt(0))) {
   for(int i = 1;i<name.length();i++) {
     if(isnol(name.charAt(i))) {
       continue;
     }
     else {
       return -1;
     }
   }
   
   return from+1;
 }
 
   
   return -1; // Todo
 
 }

 public static int parseType(String[] program, int from) {
   if (from==-1) {
     System.out.println("There is something wrong");
     return -1;
   }
   if(program[from].equals("int"))
     return from + 1;
   return -1; // Todo
}

public static int parseDecl(String[] program, int from) {
  if (from==-1) {
    System.out.println("There is something wrong");
    return -1;
  }
  from = parseType(program,from);
  if (from==-1) {
    return -1;
  }
  from = parseName(program,from);
  if (from==-1) {
    return -1;
  }
  while(program[from].equals(",")) {
    from = parseName(program,from+1);
    if (from==-1) {
      return -1;
    }
  }
  if (program[from].equals(";")){
    return from+1;
  }
  else {
    return -1;
  }
}

public static int parseUnop(String[] program, int from) {
  if (from==-1) {
    System.out.println("There is something wrong");
    return -1;
  }
  if (program[from].equals("-"))
    return from+1;
  else
  return -1; 
 }

  public static int parseBinop(String[] program, int from) {
    if (from==-1) {
      System.out.println("There is something wrong");
      return -1;
    }
    if (program[from].equals("-")||program[from].equals("+")||
        program[from].equals("*")||program[from].equals("/")||
        program[from].equals("%"))
      return from+1;
    else
      return -1; // Todo
   }
  
  public static int parseComp(String[] program, int from) {
    if (from==-1) {
      System.out.println("There is something wrong");
      return -1;
    }
    if (program[from].equals("==")||program[from].equals("!=")||
        program[from].equals("<=")||program[from].equals("<")||
        program[from].equals(">=")||program[from].equals(">"))
      return from+1;
    else
      return -1; 
  }
 
   public static int parseExpression(String[] program, int from) {
     if (from==-1) {
       System.out.println("There is something wrong");
       return -1;
     }
     if (parseNumber(program,from)!=-1) {
       from=parseNumber(program,from);
     }
     else if(parseName(program,from)!=-1) {
       from=parseName(program,from);
     }
     else if(program[from].equals("(")) {
       from = parseExpression(program,from+1);
       if(from==-1)
         return -1;
       if(program[from].equals(")")) {
         from=from+1;
       }
       else
         return -1;
     }
     else if(program[from].equals("-")) {
       from = parseExpression(program,from+1);
       if (from==-1) {
         return -1;
       }
     }
     else
       return -1;
     if(parseBinop(program,from)!=-1) {
       return parseExpression(program,from+1);
     }
     else
       return from;
   }
   
  public static int parseBbinop(String[] program, int from) {
    if (from==-1) {
      System.out.println("There is something wrong");
      return -1;
    }
    if (program[from].equals("&&")||program[from].equals("||"))
      return from+1;
    else
      return -1; 
  }
  
   public static int parseBunop(String[] program, int from) {
     if (from==-1) {
       System.out.println("There is something wrong");
       return -1;
     }
     if (program[from].equals("!"))
       return from+1;
     else
       return -1; 
    }
   
    public static int parseCondition(String[] program, int from) {
      if (from==-1) {
        System.out.println("There is something wrong");
        return -1;
      }
      if(program[from].equals("true")||program[from].equals("false")) {
        from++;
      }
      else if(program[from].equals("(")) {
        from=parseCondition(program,from);
        if(from==-1)
          return -1;
        if(program[from].equals(")")) {
          from= from+1;
        }
        else
          return -1;
      }
      else if(parseExpression(program,from)!=-1) {
        from = parseExpression(program,from);
        from = parseComp(program,from);
        if (from==-1)
          return -1;
        from = parseExpression(program,from);
        if(from==-1)
          return-1;
      }
      else if(program[from].equals("!")) {
        if (program[from+1].equals("(")){
          from = from+2;
          from = parseCondition(program,from);
          if(from==-1)
            return -1;
          if(program[from].equals(")")) {
            from = from+1;
          }
          else
            return -1;
          
        }
        else
          return -1;
      }
      else
        return -1;
      
      if(parseBbinop(program,from)!=-1) {
        return parseCondition(program,from+1);
      }
      else
        return from;
      
    }
  
   public static int parseStatement(String[] program, int from) {
     if (from==-1) {
       System.out.println("There is something wrong");
       return -1;
     }
     if (program[from].equals(";"))
       return from+1;
     else if(program[from].equals("{")) {
       from++;
       if(parseStatement(program,from)==-1)
         return -1;
       while(parseStatement(program,from)!=-1 && from<program.length) {
         from = parseStatement(program,from);
       }
       if(program[from].equals("}")) {
         return from+1;
       }
       else
         return -1;
     }
     else if (parseName(program,from)!=-1) {
       from=from+1;
       if(program[from].equals("=")) {
         from=from+1;
         if(parseExpression(program,from)!=-1) {
           from=parseExpression(program,from);
           if(program[from].equals(";"))
             return from+1;
           else
             return -1;
         }
         else if(program[from].equals("read")) {
           from++;
           if(program[from].equals("(")) {
             from++;
             if(program[from].equals(")")) {
               from++;
               if(program[from].equals(";")) {
                 from++;
                 return from;
               }
               else
                 return -1;
             }
             else
               return -1;
               
           }
           else
             return -1;
         }
         else
           return -1;
       }
       else 
         return -1;
     }
     
     else if(program[from].equals("write")) {
       from++;
       if(program[from].equals("(")) {
         from++;
         if(parseExpression(program,from)!=-1) {
           from=parseExpression(program,from);
           if(program[from].equals(")")) {
             from++;
             if(program[from].equals(";")) {
               return from+1;
             }
             else
               return -1;
           }
           else
             return -1;
         }
         else
           return -1;
       }
       else
         return -1;
     }
    
     
     else if(program[from].equals("if")) {
       from++;
       if(program[from].equals("(")) {
         from++;
         if(parseCondition(program,from)!=-1) {
           from=parseCondition(program,from);
           if(program[from].equals(")")) {
             from++;
             if(parseStatement(program,from)!=-1) {
               from = parseStatement(program,from);
               if(program[from].equals("else")) {
                 return parseStatement(program,from+1);
               }
               else
                 return from;
             }
             else
               return -1;
           }
           else
             return -1;
         }
         else
           return -1;
       }
       else
         return -1;
     }
     
     else if(program[from].equals("while")) {
       from++;
       if(program[from].equals("(")) {
         from++;
         if(parseCondition(program,from)!=-1) {
           from = parseCondition(program,from);
           if(program[from].equals(")")) {
             from++;
             return parseStatement(program,from);
           }
           else
             return -1;
         }
         else
           return -1;
       }
       else
         return -1;
     }
       
     else
       return -1;
    
  }
  
   public static int parseProgram(String[] program) {
     int fromfirst=0;
     int from= parseDecl(program,0);
     if (from == -1)
       return -1;
     while(from!=-1) {
       fromfirst=from;
       from = parseDecl(program,from);
     }
     
     
     fromfirst=parseStatement(program,fromfirst);
     if (fromfirst==-1)
       return -1;
     while(fromfirst!=program.length) {
       fromfirst=parseStatement(program,fromfirst);
       if (fromfirst==-1)
         return -1;
     }
     
       return fromfirst; 
    }
   
   public static boolean isnol(char c) { //See if it's a letter or nummer;
     return c >=65 && c<=90 || c>=97 && c<=122 || c>=48 && c<=57;
   }
   public static boolean isletter(char c) {
     return c >=65 && c<=90 || c>=97 && c<=122;
   }
    public static void main(String[] args) {
      String s = "int sum, n, i;\n"
          + "n = read();\n"
          + "while (n < 0) {\n"
          + "n = read();\n"
          + "}\n"
          + "\n"
          + "sum = 0;\n"
          + "i = 0;\n"
          + "while (i < n) {\n"
          + "{\n"
          + "if (i % 3 == 0 || i % 7 == 0) {\n"
          + "sum = sum + i;\n"
          + "if (i % 3 == 0 || i % 7 == 0) {\n"
          + "sum = sum + i;\n"
          + "} else\n"
          + "sum = 99;\n"
          + "}\n"
          + "i = i + 1;\n"
          + "}\n"
          + "}\n"
          + "\n"
          + "write(sum);";
      
      String[] s333 = lex(s);
      // Todo
      String s1 = "";
      s1 = s1+"1";
      
      for (int i = 0;i<s333.length;i++) {
        System.out.println(s333[i]+"    "+i);
      }
      
      if(parseProgram(s333)>0) {
      System.out.print("Syntax is right!");
      }
      else {
        System.out.print("Syntax is wrong!");
      }
   }
   
   }
 