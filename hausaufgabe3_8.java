
public class hausaufgabe3_8 extends MiniJava {

	public static void main(String[] args) {
		int n = read("Please type in a positive number!");
		while(n<=0) {
			n = read("Please type in a positive number!");
		}
		int i=n;
		int count = 0;
		while (i!=0) {
			count=count+1;
			i=i/10;
		}
		int [] a = new int [count];
		int d = 1;
		for (int j = 0; j<count;j++) {
			a[j] =  ((n/d)-10*(n/(10*d)));
			d = d*10;
		}
	
		
		int count2 = 0;
		
for (int j = 0;j<count;j++) {
	if(a[j]==a[count-j-1]) {
		count2++;
	}
}
if (count2 == count) {
	write("It's a Palindrom!!!");
}
else {
	write("It's not a Palindrom!!!!!");
}

	}

}
