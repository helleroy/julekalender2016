import java.util.ArrayList;
import java.util.List;

public class Luke2 {

    public static void main(String[] args) {
        fibonacci(4000000000L)
                .stream()
                .filter(n -> n % 2 == 0)
                .reduce(Long::sum)
                .ifPresent(System.out::println);
    }

    public static List<Long> fibonacci(long limit) {
        List<Long> sequence = new ArrayList<>();
        long previous = 0;
        long current = 1;
        long sum;

        while (previous + current < limit) {
            sum = previous + current;
            previous = current;
            current = sum;
            sequence.add(sum);
        }

        return sequence;
    }
}
