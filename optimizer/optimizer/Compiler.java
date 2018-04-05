package optimizer;

import optimizer.Interpreter;

public class Compiler {
  public static int[] compile(String code) {
    ParsyMcParseface parse = new ParsyMcParseface(code);
    /* String[] s = parse.getProgram();
    for(int i =0;i<s.length;i++) {
      System.out.println(""+i+": "+s[i]);
      
    }   */
    Program p = parse.parse();
    CodeGenerationVisitor cgv = new CodeGenerationVisitor();
    p.accept(cgv);
    
    return cgv.getProgram();
  }


}
