
public class GGT {
  public static int ggt(int a, int b) {
    int temp;
    if(b>a) {
      temp=a;
      a=b;
      b=temp;
    }
    while(b!=0) {
      temp=b;
      b=a%b;
      a=temp;
    }
    return a;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println(ggt(3528,3780));
    System.out.print(~~(0));
  }

}
