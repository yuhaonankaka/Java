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
 
 static String textProgram =
     "LDI 10\n"+
         "LDI fak\n"+
         "CALL 1\n"+
         "HALT\n"+
         "fak:\n"+
         "ALLOC 1\n"+
         "LDI 1\n"+
         "STS 1\n"+
         "LDS 0\n"+
         "LDI 1\n"+
         "JE end\n"+
         "LDI 1\n"+
         "LDS 0\n"+
         "SUB\n"+
         "LDI fak\n"+
         "CALL 1\n"+
         "LDS 0\n"+
         "MUL\n"+
         "STS 1\n"+
         "end:\n"+
         "LDS 1\n"+
         "RETURN 2";

 static void error(String message) {
 throw new RuntimeException(message);
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

 
 public static int [] parse(String textProgram) {
   
   String [] s = textProgram.split("\n");
   int n = s.length;
   int [] result = new int [n];
   int funtionnum = 0;
   for (int i =0;i<n;i++) {
     if (s[i].endsWith(":")) {
       funtionnum++;      
     }
   }
   int [] funtion = new int [funtionnum];
   
   int position = 0;
   for (int i =0;i<n;i++) {
     if (s[i].endsWith(":")) {
       funtion[position]=i;
       position++;
     }
   }
   
   
     
   
   
   
   for (int i =0;i<n;i++) {
    
     
     if (s[i].startsWith("ADD")) {
       int n1 = ADD;
       n1 = n1<<16;
       result[i]=n1;
     }
     
     else if (s[i].startsWith("SUB")) {
       int n1 = SUB;
       n1 = n1<<16;
       result[i]=n1;
     }
     
     else if (s[i].startsWith("MUL")) {
       int n1 = MUL;
       n1 = n1<<16;
       result[i]=n1;
     }
     
     else if (s[i].startsWith("MOD")) {
       int n1 = MOD;
       n1 = n1<<16;
       result[i]=n1;
     }
     
     else if (s[i].startsWith("LDI")) {
       String [] command = s[i].split(" ");
       int n1 = LDI;
       int n2 = 0;
       if (funtionnum==0)
       {
        n2 =  Integer.parseInt(command[1]);
       }
       else {
       for(int x =0;x<funtionnum;x++) {
         if(s[funtion[x]].startsWith(command[1])) {
           n2 = funtion[x];
           break;
           }
         if (x==(funtionnum-1)) {
           n2 =  Integer.parseInt(command[1]);
         }
         }
       }
       n1 = n1<<16;
       n2 = n1 + n2;
       result[i]=n2;
     }
     //注意负数！！！
     else if (s[i].startsWith("LDS")) {
       String [] command = s[i].split(" ");
       int n1 = LDS;
       int n2 =  Integer.parseInt(command[1]);
       
       if(n2<0) {
         n2=n2<<16;
         
         n2=n2>>>16;
         
       }
       
       
       n1 = n1<<16;
       n2 = n1 + n2;
       result[i]=n2;
       
     }
     
     else  if (s[i].startsWith("STS")) {
       String [] command = s[i].split(" ");
       int n1 = STS;
       int n2;
       n2 =  Integer.parseInt(command[1]);
       
       if(n2<0) {
         n2=n2<<16;
         n2=n2>>>16;
       }
       n1 = n1<<16;
       n2 = n1 + n2;
       
       result[i]=n2;
     }
     
     else if (s[i].startsWith("JUMP")) {
       String [] command = s[i].split(" ");
       int n1 = JUMP;
       int n2 = 0;
       if (funtionnum==0)
       {
        n2 =  Integer.parseInt(command[1]);
       }
       else {
       for(int x =0;x<funtionnum;x++) {
         if(s[funtion[x]].startsWith(command[1])) {
           n2 = funtion[x];
           break;
           }
         if (x==(funtionnum-1)) {
           n2 =  Integer.parseInt(command[1]);
         }
         }
       }
       n1 = n1<<16;
       n2 = n1 + n2;
       result[i]=n2;
     }
     
     else  if (s[i].startsWith("JE")) {
       String [] command = s[i].split(" ");
       int n1 = JE;
       int n2 = 0;
       if (funtionnum==0)
       {
        n2 =  Integer.parseInt(command[1]);
       }
       else {
       for(int x =0;x<funtionnum;x++) {
         if(s[funtion[x]].startsWith(command[1])) {
           n2 = funtion[x];
           break;
           }
         if (x==(funtionnum-1)) {
           n2 =  Integer.parseInt(command[1]);
         }
         }
       }
       n1 = n1<<16;
       n2 = n1 + n2;
       result[i]=n2;
     }
     
     else  if (s[i].startsWith("JNE")) {
       String [] command = s[i].split(" ");
       int n1 = JNE;
       int n2 = 0;
       if (funtionnum==0)
       {
        n2 =  Integer.parseInt(command[1]);
       }
       else {
       for(int x =0;x<funtionnum;x++) {
         if(s[funtion[x]].startsWith(command[1])) {
           n2 = funtion[x];
           break;
           }
         if (x==(funtionnum-1)) {
           n2 =  Integer.parseInt(command[1]);
         }
         }
       }
       n1 = n1<<16;
       n2 = n1 + n2;
       result[i]=n2;
     }
     
     else if (s[i].startsWith("JLT")) {
       String [] command = s[i].split(" ");
       int n1 = JLT;
       int n2 = 0;
       if (funtionnum==0)
       {
        n2 =  Integer.parseInt(command[1]);
       }
       else {
       for(int x =0;x<funtionnum;x++) {
         if(s[funtion[x]].startsWith(command[1])) {
           n2 = funtion[x];
           break;
           }
         if (x==(funtionnum-1)) {
           n2 =  Integer.parseInt(command[1]);
         }
         }
       }
       n1 = n1<<16;
       n2 = n1 + n2;
       result[i]=n2;
     }
     
     else if (s[i].startsWith("CALL")) {
       String [] command = s[i].split(" ");
       int n1 = CALL;
       int n2;
       n2 =  Integer.parseInt(command[1]);
       n1 = n1<<16;
       n2 = n1 + n2;
       result[i]=n2;
     }
     
     else if (s[i].startsWith("RETURN")) {
       String [] command = s[i].split(" ");
       int n1 = RETURN;
       int n2;
       n2 =  Integer.parseInt(command[1]);
       n1 = n1<<16;
       n2 = n1 + n2;
       result[i]=n2;
     }
     
     
     else if (s[i].startsWith("IN")) {
       int n1 = IN;
       n1 = n1<<16;
       result[i]=n1;
     }
     
     else if (s[i].startsWith("OUT")) {
       int n1 = OUT;
       n1 = n1<<16;
       result[i]=n1;
     }
     
     else  if (s[i].startsWith("HALT")) {
       int n1 = HALT;
       n1 = n1<<16;
       result[i]=n1;
     }
     
     else if (s[i].startsWith("ALLOC")) {
       String [] command = s[i].split(" ");
       int n1 = ALLOC;
       int n2;
       n2 =  Integer.parseInt(command[1]);
       n1 = n1<<16;
       n2 = n1 + n2;
       result[i]=n2;
     }
     
     else {
       result[i]=NOP;
     }
    
   }
   return result;
   
 }
 
 static int [] stack = new int [128]; 
 static int currenttop=0;
 static int framepointer=0;
 
 
 
 public static int pop() {
   if(currenttop==0) {
     System.out.print("There is nothing to pop");
     return -1;
   }
   else {
     currenttop--;
     return stack[currenttop];
   }
 }
 public static void push(int value) {
   stack[currenttop]=value;
   currenttop++;
 }
 
 
 
 public static int execute(int[] program) {
   for (int i = 0;i<program.length;i++) {
     int n1 = program[i]>>16;
     int n2 = program[i]<<16>>16;
     if (n1==NOP) {
       continue;
     }
     else if (n1 == ADD) {
       stack[currenttop-2]=stack[currenttop-1]+stack[currenttop-2];
       pop();
     }
     else if (n1 == SUB) {
       stack[currenttop-2]=stack[currenttop-1]-stack[currenttop-2];
       pop();
     }
     else if (n1 == MUL) {
       stack[currenttop-2]=stack[currenttop-1]*stack[currenttop-2];
       pop();
     }
     else if (n1 == MOD) {
       stack[currenttop-2]=stack[currenttop-1]%stack[currenttop-2];
       pop();
     }
     else if (n1 == LDI) {
       push(n2);
     }
     else if (n1 == LDS) {
       push(stack[framepointer+n2]);
     }
     else if (n1 == STS) {
       stack[framepointer+n2]=pop();
     }
     else if (n1 == JUMP) {
       i=n2-1;
       continue;
     }
     else if (n1 == JE) {
       if (stack[currenttop-1]==stack[currenttop-2]) {
         i=n2-1;
         pop();
         pop();
         continue;
       }
       else {
         pop();
         pop();
       }
     }
     
     else if (n1 == JNE) {
       if (stack[currenttop-1]!=stack[currenttop-2]) {
         i=n2-1;
         pop();
         pop();
         continue;
       }
       else {
         pop();
         pop();
       }
     }
     
     else if (n1 == JLT) {
       if (stack[currenttop-1]<stack[currenttop-2]) {
         i=n2-1;
         pop();
         pop();
         continue;
       }
       else {
         pop();
         pop();
       }
     }
     
     else if (n1 == CALL) {
       int backindex = i;
       i = pop()-1;
       int [] s = new int [n2];
       for (int j =0;j<n2;j++) {
         s[j] = pop(); 
       }
       push(framepointer);
       push(backindex);
       
       for (int j =n2-1;j>-1;j--) {
         push(s[j]);
         
       }
       
      framepointer = currenttop-1;
       
     }
     
     
     else if (n1 == RETURN) {
       int r =pop();
       for (int j = 0;j<n2;j++) {
         pop();
       }
       i=pop();
       framepointer = pop();
       push(r);
       
       
       
     }
     
     else if (n1 == IN) {
       push(MiniJava.readInt());
     }
     
     else if (n1 == OUT) {
       MiniJava.write(pop());
     }
     
     else if (n1 == HALT) {
       return stack[currenttop-1];
       
     }
     
     else if (n1 == ALLOC) {
       for (int j=0;j<n2;j++) {
       stack[currenttop]=0;
       currenttop++;
           
      }
      
     }
   }
   return stack[currenttop-1];
 }
 
 
 /*n2= n2<<16>>16;
 MiniJava.write(n2); 用来回复负数的STS和LDS
 
 做程序的时候，for i=0，i++如果需要跳，直接把i+3之类的，把之后的跳过去，然后回跳的时候，i-3之类的，跳回去
 */
 
 public static void main(String[] args) {
   String [] s = textProgram.split("\n");
   for (int i = 0;i<s.length;i++) {
     System.out.print(s[i]+"\n");
       }
   int [] result = parse(textProgram);
   for (int i = 0;i<result.length;i++) {
 System.out.print(result[i]+"\n");
   }
 
   int ssss = execute(result);
   MiniJava.write(ssss);
   
   
 
 }

}



