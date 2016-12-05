import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Luke5 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        String raw = new String(readAllBytes(get(Luke5.class.getClassLoader().getResource("luke5input.txt").toURI())));
        String[] encryptedMessage = raw.substring(1, raw.length() - 1).split(", ");

        String decrypted = sumPairs(asList(encryptedMessage)
                .stream()
                .filter(roman -> !roman.isEmpty())
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
