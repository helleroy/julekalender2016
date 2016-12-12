import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.TreeMap;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class Luke12 {

    private static final String TRIM_PATTERN = "[^\\w\\s]+| ";

    private static final TreeMap<Integer, String> ARABIC_TO_ROMAN = new TreeMap<Integer, String>() {{
        put(1, "I");
        put(4, "IV");
        put(5, "V");
        put(9, "IX");
        put(10, "X");
    }};

    public static void main(String[] args) throws URISyntaxException, IOException {
        String message = new String(readAllBytes(get(Luke12.class.getClassLoader().getResource("luke12input.txt").toURI())));

        LinkedList<String> encrypted = new StringBuilder(message)
                .reverse()
                .toString()
                .replaceAll(TRIM_PATTERN, "")
                .toLowerCase()
                .chars()
                .mapToObj(c -> (char) (c - 96))
                .map(SplitChar::new)
                .collect(LinkedList::new,
                        (strings, splitChar) -> {
                            strings.addFirst(splitChar.left == 0 ? "0" : toRoman(splitChar.left));
                            strings.addLast(splitChar.right == 0 ? "0" : toRoman(splitChar.right));
                        },
                        (strings, strings2) -> {
                        });


        System.out.println(encrypted);
    }

    private static String toRoman(int arabic) {
        Integer key = ARABIC_TO_ROMAN.floorKey(arabic);

        if (arabic == key) {
            return ARABIC_TO_ROMAN.get(arabic);
        }

        return ARABIC_TO_ROMAN.get(key) + toRoman(arabic - key);
    }

    private static class SplitChar {
        public final int left;
        public final int right;

        private SplitChar(char character) {
            int half = character / 2;
            this.left = half + character % 2;
            this.right = half;
        }
    }
}
