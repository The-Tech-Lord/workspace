// A helper library for assignment 2 (Global Sequence Alignment).
public class GSA {
    // Returns the char at index i in the string s.
    public static char charAt(String s, int i) {
        return s.charAt(i);
    }

    // Returns the length of the string s.
    public static int length(String s) {
        return s.length();
    }

    // Returns min(x, y, z).
    public static int min(int x, int y, int z) {
        return Math.min(x, Math.min(y, z));
    }
}