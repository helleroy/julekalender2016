public class Luke1 {

    public static void main(String[] args) {
        long i = parasiticNumber(4, 6);
        System.out.println(i);
    }

    public static long parasiticNumber(int n, int endDigit) {
        for (long i = 1; ; i++) {
            if (i % 10 == endDigit && endDigit * (long) Math.pow(10, (int) Math.log10(i)) + i / 10 == n * i) {
                return i;
            }
        }
    }
}
