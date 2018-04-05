
public class hausaufgabe3_7_1 extends MiniJava {

	public static void main(String[] args) {
		int n = readInt("Please type in the size of the array!");
				while(n<2) {
					write("the size must be at least 2!!!");
					n = readInt("Please type in the valid size of the array!");				
				}
		int [] a = new int [n];
for (int i = 0; i<n;i++) {
	a[i] = readInt("Please type in the number in position "+(i));
}

int sum_gerade = 0;
int sum_ungerade = 0;
for (int i = 0; i<n;i++) {
	if (i % 2 == 0) {
		sum_gerade = sum_gerade + a[i];
	}
	else {
		sum_ungerade = sum_ungerade + a[i];
	}	
}

write("sum_gerade = "+sum_gerade);
write("sum_ungerade = "+sum_ungerade);

int sub = sum_ungerade - sum_gerade;

write("The result of 'sum_ungerade - sum_gerade' is "+sub);
	}

}








public class hausaufgabe3_7_2 extends MiniJava {

	public static void main(String[] args) {
		int n = readInt("Please type in the size of the array!");
				while(n<2) {
					write("the size must be at least 2!!!");
					n = readInt("Please type in the valid size of the array!");				
				}
		int [] a = new int [n];
for (int i = 0; i<n;i++) {
	a[i] = readInt("Please type in the number in position "+(i));
}
int max = a[0],max2=a[0];
for (int i = 0; i<n;i++) {
	if (a[i]>max2) {
		if (a[i]>max) {
			max2 = max;
			max = a[i];		
		}
		else {
			max2 = a[i];
		}
	}
}

write("The 2nd biggest number is " + max2);


	}

}










public class hasaufgabe3_7_3 extends MiniJava {

	public static void main(String[] args) {
		int n = readInt("Please type in the size of the array!");
				while(n<2) {
					write("the size must be at least 2!!!");
					n = readInt("Please type in the valid size of the array!");				
				}
		int [] a = new int [n];
for (int i = 0; i<n;i++) {
	a[i] = readInt("Please type in the number in position "+(i));
}
if (n % 2 ==0) {
	for (int i = 0; i<n;i=i+2) {
		int sum = a[i]+a[i+1];
		a[i]=sum;
	}
}

else {
for (int i = 0; i<n-1;i=i+2) {
	int sum = a[i]+a[i+1];
	a[i]=sum;
	}
}

for (int i = 0; i<n;i=i+1) {
    writeConsole(a[i]+"   ");
}
	}

}



