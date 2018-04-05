package horrorhaufen;

import static horrorhaufen.SteckOperation.*;
import java.util.Hashtable;
import java.util.Scanner;
import horrorhaufen.MiniJava;

public class Interpreter extends MiniJava {
  public static SteckOperation getOperationByString(String instruction) {
    try {
      return SteckOperation.valueOf(instruction);
    } catch (IllegalArgumentException iax) {
      error("Invalid instruction: " + instruction);
      return ADD; // Never reached
    }
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

  public static String programToString(int[] program) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < program.length; i++) {
      if (i > 0)
        sb.append('\n');
      sb.append(i + ": ");
      sb.append(SteckInstruction.decode(program[i]));
    }
    return sb.toString();
  }

  public static int true_() {
    return -1;
  }

  public static int false_() {
    return 0;
  }

  static void error(String message) {
    throw new RuntimeException(message);
  }

  public int[] stack = new int[128];
  public int stackPointer = -1;

  public int pop() {
    if (stackPointer < 0 || stackPointer >= stack.length)
      error("Invalid stack access");
    return stack[stackPointer--];
  }

  public void push(int value) {
    stackPointer++;
    if (stackPointer < 0 || stackPointer >= stack.length)
      error("Invalid stack access");
    stack[stackPointer] = value;
  }

  public static int execute(int[] program) {
    return new Interpreter().executeHelp(program);
  }

  private int executeHelp(int[] program) {
      heap[127]=127;
    int programCounter = 0;
    int framePointer = -1;
    execution: while (true) {
      SteckInstruction insn = SteckInstruction.decode(program[programCounter]);
      if (insn == null)
        error("Invalid instruction at " + programCounter + ": " + program[programCounter] + ".");
      int parameter = insn.getImmediate();
      programCounter++;
      switch (insn.getOperation()) {
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
        case SHL: {
          int a = pop();
          push(a << parameter);
          break;
        }
        case LDI: {
          push(parameter & 0xffff);
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
          if (parameter < 0 || parameter > program.length)
            error("Invalid jump destionation address");
          int cond = pop();
          if (cond == -1)
            programCounter = parameter;
          break;
        }
        case EQ: {
          int a = pop();
          int b = pop();
          if (a == b)
            push(true_());
          else
            push(false_());
          break;
        }
        case LT: {
          int a = pop();
          int b = pop();
          if (a < b)
            push(true_());
          else
            push(0);
          break;
        }
        case LE: {
          int a = pop();
          int b = pop();
          if (a <= b)
            push(true_());
          else
            push(false_());
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
          if (programCounter < 0 || programCounter >= program.length)
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
        case LDH: {
          int o1 = pop();
          int o2 = pop();
          if(o1>127)
            error("Not a valid index of heap!!");
          int object = heap[o1];
          int to = object >>> 16;
          int from = object <<16;
          from = from >>> 16;
          int index = from+o2;
          int result = heap[index];
          push(result);
          break;
        }
        case STH: {
          int o1 = pop();
          int o2 = pop();
          int o3 = pop();
          if(o1>127)
            error("Not a valid index of heap!!");
          int object = heap[o1];
          int to = object >>> 16;
          int from = object <<16;
          from = from >>> 16;
          int index = from+o2;
          heap[index] = o3;
          break;
        }
        case ALLOCH: {          
          int o1 = pop();
          if(o1>126)
            error("Too big array!!");
          int headerend = heap[127];
          if(headerend==127) {
            int from = 0;
            int to = o1-1;
            heap[126] = to<<16;
            heap[127]=126;
            push(126);
            break;
          }
          else {
            int index = heap[127];
            int to = heap[index]>>>16;
            int from = to+1;
            to = from + o1-1;
            index--;
            heap[127]--;
            heap[index] = (to<<16) + from;
            push(index);
            break;
          }
        }
      }
    }
    int retVal = pop();
    if(stackPointer >= 0)
      error("Stack is tainted" + stackPointer);
    return retVal;
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
        program[i] = NOP.encode();
        continue;
      }
      String trimmed = lines[i].trim().replaceAll("\\s+", " ");
      if (trimmed.endsWith(":")) {
        program[i] = NOP.encode();
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
      program[i] = getOperationByString(cmdParam[0]).encode();
      if (parameter < Short.MIN_VALUE || parameter > Short.MAX_VALUE)
        error("Parameter out of range");
      program[i] |= parameter & 0xffff;
    }
    return program;
  }
  public int[] heap = new int[128];
  
}
