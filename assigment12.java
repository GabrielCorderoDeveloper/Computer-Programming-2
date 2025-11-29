// Gabriel Cordero assigment 12

public class Module12 {

    public static int fibRec(int n) {
        if (n <= 1) return n;
        return fibRec(n - 1) + fibRec(n - 2);
    }

    public static int fibIter(int n) {
        if (n <= 1) return n;
        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public static String decToBinRec(int n) {
        if (n == 0) return "0";
        if (n == 1) return "1";
        return decToBinRec(n / 2) + (n % 2);
    }

    public static String decToBinIter(int n) {
        if (n == 0) return "0";
        String r = "";
        while (n > 0) {
            r = (n % 2) + r;
            n = n / 2;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(fibRec(6));
        System.out.println(fibIter(6));
        System.out.println(decToBinRec(13));
        System.out.println(decToBinIter(13));
    }
}
