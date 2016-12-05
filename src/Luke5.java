import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Luke5 {

    private static final String[] ENCRYPTED_MESSAGE = {"IV", "VI", "I", "II", "III", "V", "XI", "VI", "IV", "III", "VI", "VI", "V", "IV", "III", "V", "XI", "VI", "III", "VII", "IV", "VI", "III", "IX", "II", "I", "VI", "III", "IX", "VII", "III", "II", "V", "X", "VI", "V", "XI", "VI", "IV", "V", "X", "II", "III", "III", "VI", "XIII", "XI", "III", "IX", "VII", "III", "II", "VIII", "I", "IX", "I", "II", "V", "X", "IV", "IX", "VIII", "VII", "X", "IV", "XI", "VIII", "IX", "II", "III", "X", "III", "IX", "IV", "XI", "I", "III", "VIII", "IX", "IV", "XI", "II", "III", "IX", "X", "VI", "V", "VIII", "VII", "X", "VI", "VIII", "VII", "VII", "VI", "V", "IV", "V", "I", "VI", "I", "VII", "X", "VIII", "X", "X", "II", "III", "IV", "I", "IX", "VI", "VIII", "VII", "VII", "VI", "V", "IV", "V", "I", "VI", "I", "VII", "X", "VIII", "X", "X", "II", "III", "IV", "I", "IX", "V", "XI", "VI", "III", "III", "IX", "XIII", "II", "III", "XI", "V", "IV", "III", "III", "IX", "XIII", "II", "IV", "III", "VI", "VI", "V", "IV", "X", "I", "VII", "IV", "VII", "III", "II", "IV", "V", "VII", "VII", "III", "VI", "X", "VI", "VI", "XIII", "II", "II", "III", "X", "XI", "I", "IX", "III", "VII", "IV", "VI", "III", "IX", "X", "VIII", "VII", "IV", "XIII", "IX", "II", "III", "VII", "III", "X", "I", "II", "III", "VII", "IV", "I", "VII", "IV", "IV", "III", "IX", "IX", "III", "VII", "V", "VI", "IX", "XIII", "I", "I", "III", "VII", "VI", "I", "III", "XI", "V", "IV", "III", "IX", "III", "VII", "IV", "VI", "III", "VII", "III", "X", "X", "I", "VII", "IV", "III", "XI", "V", "IV", "III", "IX", "III", "VII", "IV", "VI", "III", "VII", "III", "X", "X", "I", "VII", "IV", "X", "I", "VI", "V", "IV", "III", "IX", "III", "II", "IV", "V", "VII", "VII", "III", "VI", "X", "VI", "III", "IX", "III", "II", "X", "VIII", "VII", "III", "IX", "V", "XI", "VI", "III", "VII", "I", "X", "X", "IV", "III", "IX", "VII", "III", "II", "III", "VII", "IV", "VI", "III", "IX", "I", "IX", "V", "VII", "IV", "III", "IX", "X", "V", "VI", "X", "X", "VIII", "IX", "III", "VIII", "IV", "X", "VII", "I", "I", "XI", "II", "VIII", "VII", "IV", "I", "VII", "X", "VIII", "VII", "V", "VI", "IX", "XIII", "I", "I", "III", "VII", "VI", "I", "III", "IX", "XIII", "II", "II", "III", "IV", "IV", "XI", "III", "IX", "X", "V", "III", "VI", "IV", "I", "VII", "IV", "I", "IX", "III", "IX", "III", "VI", "X", "X", "III", "IX", "XIII", "II", "II", "III", "IV", "IV", "XI", "III", "IX", "X", "V", "III", "VI", "IV", "I", "VII", "IV", "I", "IX", "III", "IX", "III", "VI", "X", "X", "X", "IX", "VI", "II", "IX", "III", "IX", "0", "IV", "VII", "0", "IV", "VI", "II", "V", "IX", "IX", "II", "XI", "IV", "III", "II", "II", "II", "XII", "IX", "III", "X", "IX", "VI", "II", "IX", "III", "IX", "0", "IV", "VII", "0", "IV", "VI", "II", "V", "IX", "IX", "II", "XI", "IV", "III", "II", "II", "II", "XII", "IX", "III", "0", "VI", "VII", "II", "I", "I", "XII", "IX", "V", "IV", "VI", "VII", "IX", "VI", "0", "IV", "VI", "VII", "II", "X", "I", "0", "VI", "IX", "III", "VII", "II", "IX", "VII", "X", "IX", "VI", "IV", "X", "IX", "II", "III", "VII", "IV", "IX", "I", "IX", "II", "VI", "III", "VII", "II", "II", "II", "VII", "IX", "II", "IV", "X", "X", "0", "VII", "II", "VI", "X", "V", "IX", "II", "VII", "VII", "X", "II", "II", "IX", "III", "V", "IX", "VI", "II", "VI", "VI", "IV", "IV", "II", "II", "IX", "III", "III", "IV", "VI", "0", "IX", "III", "VII", "0", "IX", "IX", "II", "VII", "II", "VI", "III", "VII", "II", "IX", "II", "III", "IV", "XI", "II", "III", "VII", "0", "IX", "IX", "II", "VII", "II", "VI", "III", "VII", "II", "IX", "II", "III", "IV", "XI", "II", "0", "VI", "VII", "II", "I", "I", "XII", "IX", "V", "IV", "VII", "II", "IX", "IX", "II", "IV", "III", "VII", "0", "III", "VII", "II", "II", "0", "IX", "II", "VII", "II", "II", "IX", "XII", "IV", "VI", "VII", "IX", "IX", "II", "VI", "III", "VII", "II", "IX", "0", "XI", "X", "II", "II", "II", "XII", "VI", "V", "IX", "VI", "II", "VI", "VI", "IV", "IV", "II", "II", "VI", "III", "VII", "0", "IX", "III", "IV", "VI", "VI", "II", "IV", "II", "XII", "IX", "III", "II", "III", "IV", "XI", "II", "II", "XII", "IX", "III", "II", "VI", "X", "V", "IX", "0", "III", "II", "II", "IX", "IX", "VII", "X", "VII", "0", "VI", "I", "IV", "III", "IV", "VI", "VII", "VII", "VII", "VI", "IX", "0", "III", "II", "II", "IX", "IX", "VII", "X", "VII", "0", "VI", "I", "IV", "III", "IV", "VI", "VII", "VII", "VII", "VI", "X", "VII", "VII", "V", "V", "IX", "IX", "II", "II", "X", "III", "IX", "VII", "III", "0", "XI", "IV", "IX", "II", "IX", "II", "II", "IX", "VII", "XI", "IV", "X", "VII", "VII", "IX", "III", "IX", "IV", "II", "0", "IX", "0", "VIII", "II", "II", "VI", "IX", "II", "XI", "XII", "VI", "III", "II", "II", "X", "IV", "IV", "VI", "X", "V", "V", "IX", "IV", "II", "II", "VII", "IX", "II", "VI", "0", "II", "IX", "II", "VI", "III", "VII", "II", "VI", "X", "V", "II", "III", "IV", "VI", "VI", "II", "IV", "VI", "X", "V", "II", "II", "0", "VI", "III"};

    public static void main(String[] args) {
        String decrypted = sumPairs(asList(ENCRYPTED_MESSAGE)
                .stream()
                .map(Luke5::toArabic)
                .collect(toList()))
                .stream()
                .map(charValue -> (char) (charValue + 96))
                .map(Object::toString)
                .reduce(String::concat)
                .orElse("");

        System.out.println(decrypted);
    }

    private static List<Integer> sumPairs(List<Integer> numbers) {
        List<Integer> summedPairs = new ArrayList<>();

        int fromStart = 0;
        int fromEnd = numbers.size() - 1;
        while (fromStart < fromEnd) {
            summedPairs.add(numbers.get(fromStart++) + numbers.get(fromEnd--));
        }

        return summedPairs;
    }

    private static int toArabic(String roman) {
        final HashMap<Character, Integer> romanToArabic = new HashMap<Character, Integer>() {{
            put('0', 0);
            put('I', 1);
            put('V', 5);
            put('X', 10);
        }};

        return (roman + '0').chars()
                .mapToObj(value -> (char) value)
                .map(romanToArabic::get)
                .reduce((left, right) -> left < right ? right - left : right + left)
                .orElseGet(() -> 0);
    }
}
