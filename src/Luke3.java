import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toSet;

public class Luke3 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> friendsFromFile = readAllLines(get(Luke3.class.getClassLoader().getResource("luke3input.txt").toURI()));

        Map<String, Set<String>> friends = new HashMap<>();
        Map<String, Set<String>> hates = new HashMap<>();

        for (String line : friendsFromFile) {
            List<String> split = asList(line.split(" +"));

            if (split.contains("friends")) {
                mergeValues(friends, split.get(1), split.get(2));
                mergeValues(friends, split.get(2), split.get(1));
            }
            if (split.contains("hates")) {
                mergeValues(hates, split.get(0), split.get(2));
            }
        }

        Map.Entry<String, Integer> chameleon = getHateCount(friends, hates)
                .entrySet()
                .stream()
                .reduce((entry1, entry2) -> entry1.getValue() < entry2.getValue() ? entry2 : entry1)
                .get();

        System.out.println(chameleon);
    }

    private static Map<String, Integer> getHateCount(Map<String, Set<String>> friends, Map<String, Set<String>> hates) {
        Map<String, Integer> hateCount = new HashMap<>();

        for (String person : friends.keySet()) {
            friends.get(person)
                    .stream()
                    .filter(friend -> hates.get(person).contains(friend) && !hates.get(friend).contains(person))
                    .forEach(friend -> hateCount.merge(person, 1, (oldVal, newVal) -> oldVal + newVal));
        }

        return hateCount;
    }

    private static void mergeValues(Map<String, Set<String>> map, String key, String value) {
        map.merge(key, new HashSet<>(singletonList(value)),
                (oldVal, newVal) -> Stream.concat(oldVal.stream(), newVal.stream()).collect(toSet()));
    }
}
