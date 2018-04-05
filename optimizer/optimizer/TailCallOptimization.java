package optimizer;

import java.util.ArrayList;
import java.util.List;
import optimizer.Compiler;
import optimizer.Interpreter;

public class TailCallOptimization {
  
  
  
  public static void optimize(int[] program) {
    String s = Interpreter.programToString(program);
    //System.out.println(s);
    for(int i = 0;i<program.length;i++) {
      s = s.replaceFirst(""+i+": ","");
    }
    String [] code = s.split("\n");
    
    /* for(int i = 0;i<assembly.length;i++) {
      System.out.println(code[i]);
    } */
    
    
    int mainindex = Integer.parseInt(code[0].replace("LDI ", ""));
   // System.out.println(mainindex);
    //System.out.println(s);
    int [] a = Interpreter.parse(s);
    //System.out.println(Interpreter.execute(a));
    int callfunctionindex = 0;
    for(int i = mainindex;i<code.length;i++) {
      if(code[i].contains("CALL")) {
        callfunctionindex = Integer.parseInt(code[i-1].replace("LDI ",""));
        break;
      }
    }
    //System.out.println(callfunctionindex);
    int callself = 0;
    for(int i = callfunctionindex;i<code.length;i++) {
      if(code[i].contains("LDI "+callfunctionindex)) {
        callself = i;
        break;
      }
    }
   // System.out.println(callself);
    int pnum = 0;
    pnum = Integer.parseInt(code[callself+1].replace("CALL ", ""));
    if(code[callself+1].contains("CALL") && code[callself+2].contains("RETURN")) {
      System.out.println("Success Optimized!");
      List<String> codelist= new ArrayList<String>();
      for(int i = 0;i<code.length;i++) {
        codelist.add(code[i]+"\n");
      }
      int end = 0;
      for(int i=0;i>0-pnum;i--) {
        if(i == 1-pnum)
          end = callself -i+1;
        if (i>-3)
          codelist.set(callself-i, "STS "+i+"\n");
        else {
          codelist.add(callself-i, "STS "+i+"\n");
          mainindex++;
          codelist.remove(codelist.size()-1);
        }
      }
      
      if(codelist.get(end).contains("RETURN"))
        codelist.set(end, "LDI 0\n");
      else{
          codelist.add(end, "LDI 0\n");
          mainindex++;
          codelist.remove(codelist.size()-1);
      }
      codelist.add(end+1, "NOT\n");
      mainindex++;
      codelist.remove(codelist.size()-1);
      codelist.add(end+2,"JUMP "+callfunctionindex+"\n");
      mainindex++;
      codelist.remove(codelist.size()-1);
      codelist.set(0, "LDI "+mainindex+"\n");
      String ret="";
      for(int i =0;i<program.length;i++) {
        ret = ret+codelist.get(i);
        //System.out.println(i+" : "+codelist.get(i));
      }
      
      int [] re = Interpreter.parse(ret);
      for(int i =0;i<program.length;i++) {
        program[i] = re[i];
      }
      
  
      
      
      
    }
    else {
      System.out.println("Can't be optimized!!");
      
    }
    
    /*String s2 = "LDI 22\r\n" + 
        "CALL 0\r\n" + 
        "HALT\r\n" + 
        "NOP\r\n" + 
        "LDI 0\r\n" + 
        "LDS 0\r\n" + 
        "EQ\r\n" + 
        "NOT\r\n" + 
        "JUMP 11\r\n" + 
        "LDI 1\r\n" + 
        "RETURN 1\r\n" + 
        "LDI 1\r\n" + 
        "LDS 0\r\n" + 
        "SUB \r\n" + 
        "LDS 0\r\n" + 
        "MUL\r\n" + 
        "STS 0\r\n" + 
        "LDI 0\r\n" + 
        "NOT\r\n" + 
        "JUMP 3\r\n" + 
        "NOP\r\n" + 
        "LDI 12\r\n" + 
        "LDI 3\r\n" + 
        "CALL 1\r\n" + 
        "RETURN 0";
    int [] n = Interpreter.parse(s2);
    for(int i = 0;i<n.length;i++) {
      program[i]=n[i];
    }    */
    /* String s ="LDI 21\n" + 
        "CALL 0\n" + 
        "HALT\n" + 
        "LDI 0\n" + 
        "LDS -1\n" + 
        "EQ\n" + 
        "NOT\n" + 
        "JUMP 10\n" + 
        "LDS 0\n" + 
        "RETURN 2\n" + 
        "LDI 1\n" + 
        "LDS -1\n" + 
        "SUB\n" + 
        "LDS -1\n" + 
        "LDS 0\n" + 
        "MUL\n" + 
        "STS 0\n" + 
        "STS -1\n" + 
        "LDI 0\n" +
        "NOT\n" +
        "JUMP 3\n" + 
        "LDI 12\n" + 
        "LDI 1\n" + 
        "LDI 3\n" + 
        "CALL 2\n" + 
        "RETURN 0\n";
    int [] n = Interpreter.parse(s);
    for(int i = 0;i<n.length;i++) {
      program[i]=n[i];
    }
    */
  }
}
