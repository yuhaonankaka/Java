import java.util.Hashtable;
import java.util.Scanner;

public class Interpreter extends MiniJava {
  public static final int NOP = 0;
  public static final int ADD = 1;
  public static final int SUB = 2;
  public static final int MUL = 3;
  public static final int MOD = 4;
  public static final int LDI = 5;
  public static final int LDS = 6;
  public static final int STS = 7;
  public static final int JUMP = 8;
  public static final int JE = 9;
  public static final int JNE = 10;
  public static final int JLT = 11;
  public static final int IN = 12;
  public static final int OUT = 13;
  public static final int CALL = 14;
  public static final int RETURN = 15;
  public static final int HALT = 16;
  public static final int ALLOC = 17;
  public static final int DIV = 18;
  public static final int AND = 19;
  public static final int OR = 20;
  public static final int NOT = 21;
  public static final int EQ = 22;
  public static final int LT = 23;
  public static final int LE = 24;
  
  
  
  static void error(String message) {
    throw new RuntimeException(message);
  }
  
  public static String lookup2(int instruction) {
    switch (instruction) {
      case NOP:
        return "NOP";
      case ADD:
        return "ADD";
      case SUB:
        return "SUB";
      case MUL:
        return "MUL";
      case MOD:
        return "MOD";
      case LDI:
        return "LDI";
      case LDS:
        return "LDS";
      case STS:
        return "STS";
      case JUMP:
        return "JUMP";
      case JE:
        return "JE";
      case JNE:
        return "JNE";
      case JLT:
        return "JLT";
      case IN:
        return "IN";
      case OUT:
        return "OUT";
      case CALL:
        return "CALL";
      case RETURN:
        return "RETURN";
      case HALT:
        return "HALT";
      case ALLOC:
        return "ALLOC";
      case DIV:
        return "DIV";
      case AND:
        return "AND";
      case OR:
        return "OR";
      case NOT:
        return "NOT";
      case EQ:
        return "EQ";
      case LT:
        return "LT";
      case LE:
        return "LE";
      default:
        error("Invalid instruction: " + instruction);
    }
    return null;
  }


  public static String programToString(int[] program) {
    
    String s="";
    int programCounter = 0;
    while (programCounter!=program.length) {
      if (programCounter < 0 || programCounter >= program.length)
        error("Invalid instruction access"); 
      int nextCommand = program[programCounter] >>> 16;
     
      int parameter = program[programCounter] & 0xffff;
      
      if ((parameter & 0x8000) != 0)
        parameter |= 0xffff0000;
      
      s=s+programCounter+":"+" "+lookup2(nextCommand);
      if(nextCommand==DIV || nextCommand==OR ||nextCommand==NOT || nextCommand==AND || nextCommand==EQ
          || nextCommand==LT || nextCommand==LE || nextCommand==NOP || nextCommand==ADD || nextCommand==SUB
          || nextCommand==MUL || nextCommand==MOD || nextCommand==IN || nextCommand==OUT || nextCommand==HALT)
        s=s+"\n";
      else
        s=s+" "+parameter+"\n";
      programCounter++;
      
    }
    return s;
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

  public static int lookup(String instruction) {
    switch (instruction) {
      case "NOP":
        return NOP;
      case "ADD":
        return ADD;
      case "SUB":
        return SUB;
      case "MUL":
        return MUL;
      case "MOD":
        return MOD;
      case "LDI":
        return LDI;
      case "LDS":
        return LDS;
      case "STS":
        return STS;
      case "JUMP":
        return JUMP;
      case "JE":
        return JE;
      case "JNE":
        return JNE;
      case "JLT":
        return JLT;
      case "IN":
        return IN;
      case "OUT":
        return OUT;
      case "CALL":
        return CALL;
      case "RETURN":
        return RETURN;
      case "HALT":
        return HALT;
      case "ALLOC":
        return ALLOC;
      case "DIV":
        return DIV;
      case "AND":
        return AND;
      case "OR":
        return OR;
      case "NOT":
        return NOT;
      case "EQ":
        return EQ;
      case "LT":
        return LT;
      case "LE":
        return LE;
      default:
        error("Invalid instruction: " + instruction);
    }
    return 0;
  }

  public static int[] stack = new int[256];
  public static int stackPointer;

  public static int pop() {
    if (stackPointer < 0 || stackPointer >= stack.length)
      error("Invalid stack access");
    return stack[stackPointer--];
  }

  public static void push(int value) {
    stackPointer++;
    if (stackPointer < 0 || stackPointer >= stack.length)
      error("Invalid stack access");
    stack[stackPointer] = value;
  }
  
  public static int[] parse(String textProgram) {
    String[] lines = textProgram.split("\n");
    int[] program = new int[lines.length];
    // Man kann auch z.B. ein mehrdimensionales String-Array verwenden,
    // das ist aber mühsam und unschön, daher hier die Hashtabelle
    Hashtable<String, Integer> labels = new Hashtable<>();
    for (int i = 0; i < lines.length; i++) {
      String trimmed = lines[i].trim().replaceAll("\\s+", " ");
      if (trimmed.endsWith(":")) {
        labels.put(trimmed.substring(0, trimmed.length() - 1), i);
        continue;
      }
    }
    for (int i = 0; i < lines.length; i++) {
      if (lines[i].length() == 0) {
        // NOP wird durch NOP überschrieben, wie philosophisch
        program[i] = NOP << 16;
        continue;
      }
      String trimmed = lines[i].trim().replaceAll("\\s+", " ");
      if (trimmed.endsWith(":")) {
        // NOP wird durch NOP überschrieben, wie philosophisch
        program[i] = NOP << 16;
        continue;
      }
      String[] cmdParam = trimmed.split(" ");
      if (cmdParam.length > 2)
        error("Unable to parse input");
      int parameter = 0;
      if (cmdParam.length > 1) {
        // Es gibt einen Parameter
        if (labels.containsKey(cmdParam[1]))
          // Der Parameter wurde irgendwo als Label definiert
          parameter = labels.get(cmdParam[1]);
        else
          // Der Parameter wurde nicht als Label definiert, ist also eine Zahl
          parameter = Integer.parseInt(cmdParam[1]);
      }
      program[i] = lookup(cmdParam[0]) << 16;
      if(parameter < Short.MIN_VALUE || parameter > Short.MAX_VALUE)
        error("Parameter out of range");
      program[i] |= parameter & 0xffff;
    }
    return program;
  }

  public static int execute(int[] program) {
    // Pointer zurücksetzen
    stackPointer = -1;
    int programCounter = 0;
    int framePointer = -1;
    execution: while (true) {
      if (programCounter < 0 || programCounter >= program.length)
        error("Invalid instruction access"); // Sollte niemals passieren, siehe unten
      // Obere 16 Bit (OpCode) extrahieren
      int nextCommand = program[programCounter] >>> 16;
      // Untere 16 Bit (Immediate) extrahieren
      int parameter = program[programCounter] & 0xffff;
      // Sign extension
      if ((parameter & 0x8000) != 0)
        parameter |= 0xffff0000;
      programCounter++;
      switch (nextCommand) {
        case NOP: {
          break;
        }
        case ADD: {
          int a = pop();
          int b = pop();
          push(a + b);
          break;
        }
        case SUB: {
          int a = pop();
          int b = pop();
          push(a - b);
          break;
        }
        case MUL: {
          int a = pop();
          int b = pop();
          push(a * b);
          break;
        }
        case MOD: {
          int a = pop();
          int b = pop();
          push(a % b);
          break;
        }
        case LDI: {
          push(parameter);
          break;
        }
        case LDS: {
          int stackAddress = framePointer + parameter;
          if (stackAddress < 0 || stackAddress >= stack.length)
            error("Invalid stack access");
          push(stack[stackAddress]);
          break;
        }
        case STS: {
          int stackAddress = framePointer + parameter;
          if (stackAddress < 0 || stackAddress >= stack.length)
            error("Invalid stack access");
          int value = pop();
          stack[stackAddress] = value;
          break;
        }
        case JUMP: {
          int a = pop();
          if (a==-1)
          programCounter = parameter;
          break;
        }
        case JE: {
          if (parameter < 0 || parameter > program.length)
            error("Invalid jump destionation address");
          int a = pop();
          int b = pop();
          if (a == b)
            programCounter = parameter;
          break;
        }
        case JNE: {
          if (parameter < 0 || parameter > program.length)
            error("Invalid jump destionation address");
          int a = pop();
          int b = pop();
          if (a != b)
            programCounter = parameter;
          break;
        }
        case JLT: {
          if (parameter < 0 || parameter > program.length)
            error("Invalid jump destionation address");
          int a = pop();
          int b = pop();
          if (a < b)
            programCounter = parameter;
          break;
        }
        case IN: {
          int value = read();
          push(value);
          break;
        }
        case OUT: {
          int value = pop();
          write(value);
          break;
        }
        case CALL: {
          if (parameter < 0)
            error("Invalid function argument count");
          int functionAddress = pop();
          if (functionAddress < 0 || functionAddress > program.length)
            error("Invalid function address");
          int[] arguments = new int[parameter];
          for (int i = 0; i < arguments.length; i++)
            arguments[i] = pop();
          push(framePointer);
          push(programCounter);
          for (int i = 0; i < arguments.length; i++)
            push(arguments[arguments.length - 1 - i]);
          framePointer = stackPointer;
          programCounter = functionAddress;
          break;
        }
        case RETURN: {
          if (parameter < 0)
            error("Invalid stack frame size");
          int retVal = pop();
          for (int i = 0; i < parameter; i++)
            pop();
          programCounter = pop();
          if (programCounter < 0 || programCounter > program.length)
            error("Invalid return address on stack; the stack has been destroyed by the program.");
          framePointer = pop();
          if (framePointer < -1 || framePointer >= stack.length)
            error("Invalid frame pointer on stack; the stack has been destroyed by the program.");
          push(retVal);
          break;
        }
        case HALT: {
          break execution;
        }
        case ALLOC: {
          if (parameter < 0)
            error("Invalid stack allocation");
          stackPointer += parameter;
          break;
        }
        case DIV: {
          int a = pop();
          int b = pop();
          push(a / b);
          break;
        }
        case AND: {
          int a = pop();
          int b = pop();
          push(a & b);
          break;
        }
        case OR: {
          int a = pop();
          int b = pop();
          push(a | b);
          break;
        }
        case NOT: {
          int a = pop();          
          push(~a);
          break;
        }
        case EQ: {
          int a = pop();
          int b = pop();
          if (a == b)
          push(-1);
          else
          push(0);
          break;
        }
        case LT: {
          int a = pop();
          int b = pop();
          if (a < b)
          push(-1);
          else
          push(0);
          break;
        }
        case LE: {
          int a = pop();
          int b = pop();
          if (a <= b)
          push(-1);
          else
          push(0);
          break;
        }
        default: {
          error("Invalid instruction: " + nextCommand + ".");
        }
      }
    }
    int retVal = pop();
    return retVal;
  }

  public static void main(String[] args) {
    String textProgram =
        "LDI 34\n"+
        "CALL 0\n"+
        "HALT\n"+
        "ALLOC 1\n"+
        "LDS -1\n"+
        "LDS 0\n"+
        "LE\n"+
        "NOT\n"+
        "NOT\n"+
        "JUMP 16\n"+
        "LDS -1\n"+
        "STS 1\n"+
        "LDS 0\n"+
        "STS -1\n"+
        "LDS 1\n"+
        "STS 0\n"+
        "LDI 0\n"+
        "LDS 0\n"+
        "EQ\n"+
        "NOT\n"+
        "NOT\n"+
        "JUMP 32\n"+
        "LDS 0\n"+
        "STS 1\n"+
        "LDS 0\n"+
        "LDS -1\n"+
        "MOD\n"+
        "STS 0\n"+
        "LDS 1\n"+
        "STS -1\n"+
        "LDI -1\n"+
        "JUMP 16\n"+
        "LDS -1\n"+
        "RETURN 3\n"+
        "LDI 3528\n"+
        "LDI 3780\n"+
        "LDI 3\n"+
        "CALL 2\n"+
        "RETURN 0";
    int[] program = Interpreter.parse(textProgram);
    System.out.print(programToString(program));
  }

}
