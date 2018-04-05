
public class hasugabe2_9 extends MiniJava {

	public static void main(String[] args) {
		int n;
		n = read("Please type in the size of the table! ");
		writeConsole("\\");
		writeConsole("begin{tabular}");
		writeConsole("{");
		for (int i = 0; i < n; i = i + 1) {
			writeConsole("l");
		}
		
		writeLineConsole("}");
		for (int i = 0; i < n; i = i + 1) {
			for (int j = 0; j < n; j = j + 1) {
				int k ;
				k = (int) Math.pow(i+1, j);
				
				if(j == n-1) {
					writeConsole(" "+k);
					writeConsole("\\");
					writeLineConsole("\\");
				}
				else {
					writeConsole(k);
					writeConsole(" &");
				}
			}
		}
		writeLineConsole("\\end{tabular}");
				
	}

}
