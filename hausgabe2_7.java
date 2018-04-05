public class hausgabe2_7 extends MiniJava {

	public static void main(String[] args) {
		int n;
		int sum = 0;
		n = read("Please type in a number n ");
		while(n<0) {
			n = read("Please type in a postive number!!!");
		}
		for(int i=0; i<n;i=i+1) {
			if(i%3==0 || i%7==0) {				
				sum=sum+i;
			}
		}
		write("The Sum is "+sum);
	}

}
