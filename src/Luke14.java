import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public class Luke14 {

    private static final double REQUIRED_ENERGY = 2.25 * Math.pow(10, 32);
    private static final Set<Integer> ALLOYS = new HashSet<>(asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16));

    public static void main(String[] args) {

        List<Alloy> enoughResistance = new ArrayList<>();

        powerSet(ALLOYS)
                .stream()
                .filter(integers -> integers.size() % 2 == 0)
                .map(Alloy::new)
                .forEach(alloy -> {
                    if (alloy.resistance >= REQUIRED_ENERGY) {
                        enoughResistance.add(alloy);
                    }
                });

        Alloy selectedAlloy = enoughResistance
                .stream()
                .reduce((alloy1, alloy2) -> alloy1.factorialSum() < alloy2.factorialSum() ? alloy1 : alloy2)
                .orElseThrow(() -> new RuntimeException("No alloys are strong enough!"));

        System.out.println(selectedAlloy);
    }

    private static Set<Set<Integer>> powerSet(Set<Integer> originalSet) {
        Set<Set<Integer>> sets = new HashSet<>();

        if (originalSet.isEmpty()) {
            sets.add(new HashSet<>());
            return sets;
        }

        List<Integer> list = new ArrayList<>(originalSet);
        Integer head = list.get(0);
        Set<Integer> rest = new HashSet<>(list.subList(1, list.size()));

        for (Set<Integer> set : powerSet(rest)) {
            Set<Integer> newSet = new HashSet<>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

    private static class Alloy {
        public final Set<Integer> metals;
        public final double resistance;

        private Alloy(Set<Integer> metals) {
            this.metals = metals;

            this.resistance = metals
                    .stream()
                    .mapToDouble(num -> Math.pow(num, num))
                    .reduce((left, right) -> left * right)
                    .orElse(0);
        }

        public double factorialSum() {
            return metals
                    .stream()
                    .mapToDouble(num -> IntStream.rangeClosed(2, num)
                            .reduce((left, right) -> left * right)
                            .orElse(1))
                    .sum();
        }

        @Override
        public String toString() {
            return metals.stream().reduce("", (s, i) -> s + i, String::concat);
        }
    }
}
