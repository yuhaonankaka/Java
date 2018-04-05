
public class hausgabe2_8 extends MiniJava {

	public static void main(String[] args) {
		int n;
		n = read("Please type in a integer to check if it is a prime number?");
		while (n <= 1) {
			n = read("Please type in a positive integer which is bigger than 1!");
		}
		for(int i = 2; i<n; i = i + 1) {
			if(n%i == 0) {
				write(n+" is not a prime number!");
				System.exit(1);
			}
			
		}
		write(n+" is a prime number!");
	}

}
