import stdlib.StdOut;

public class Ramanujan1 {
    // Entry point.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
		
		for (int a = 1; Math.pow(a, 3) <= n; a++) {
			for (int b = a + 1; Math.pow(b, 3) <= n - Math.pow(a, 3); b++) {
				for (int c = a + 1; Math.pow(c, 3) <= n; c++) {
					for (int d = c + 1; Math.pow(d, 3) <= n - Math.pow(c, 3); d++) {
						int left_result = a * a * a + b * b * b;
						int right_result = c * c * c + d * d * d;
						
						if (left_result == right_result && left_result <= n && right_result <= n) {
							StdOut.println(left_result + " = " + a + "^3 + " + b + "^3 = " + c + "^3 + " + d + "^3");
						}
					}
				}
			}
		}
    }
}
