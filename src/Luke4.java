public class Luke4 {

    public static void main(String[] args) {
        int[] sequence = new int[1337];
        int count = 0;

        for (int i = 1; i <= sequence.length; i++) {
            sequence[i - 1] = i % 7 == 0 || Integer.toString(i).contains("7") ? sequence[count++] : i;
        }

        System.out.println(sequence[1336]);
    }
}
