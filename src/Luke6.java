import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.Math.abs;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Luke6 {

    private static final long N = 1_000_000_000_000_000L;

    public static void main(String[] args) {

        Knight knight = new Knight(new Square(0, 0));

        for (long i = 0; i < 10000; i++) {
            knight.move();
        }

        // n is too large to evaluate.
        // However - we can discern a pattern from using a smaller n and calculating the real answer below
        System.out.println("Calculated distance with actual n = " + ((2 * (N - 1)) + (N - 1)));
        System.out.println("Distance found by using smaller n = " + knight.getLongestDistance());
    }

    private static class Knight {

        private static final long VISITED_VALUE = 1000;

        private final Map<Square, Square> visited = new HashMap<>();
        private long longestDistance = 0;
        private Square current;

        public Knight(Square current) {
            this.current = current;
        }

        public long getLongestDistance() {
            return longestDistance;
        }

        public void move() {
            Square moveTo = chooseLocation();

            markCurrentAsVisited();

            calculateLongestDistance();

            current = moveTo;
        }

        private Square chooseLocation() {
            Square moveTo;

            List<Square> resolvedVisited = legalMoves(current)
                    .stream()
                    .map(square -> visitedSquare(square).orElse(square))
                    .collect(toList());

            List<Square> closestValue = findClosestValue(resolvedVisited);
            if (closestValue.size() > 1) {
                List<Square> minX = findMinX(closestValue);

                if (minX.size() > 1) {
                    moveTo = minX
                            .stream()
                            .reduce((square1, square2) -> square1.y < square2.y ? square1 : square2)
                            .get();
                } else {
                    moveTo = minX.get(0);
                }
            } else {
                moveTo = closestValue.get(0);
            }
            return moveTo;
        }

        private void markCurrentAsVisited() {
            VisitedSquare previous = new VisitedSquare(current, current.getValue() == VISITED_VALUE ? 0 : VISITED_VALUE);
            visited.put(previous, previous);
        }

        private void calculateLongestDistance() {
            Long distance = visited.values()
                    .stream()
                    .reduce((square1, square2) -> current.distanceTo(square1) > current.distanceTo(square2) ? square1 : square2)
                    .map(current::distanceTo)
                    .get();

            longestDistance = distance > longestDistance ? distance : longestDistance;
        }

        private Optional<Square> visitedSquare(Square square) {
            return Optional.ofNullable(visited.get(square));
        }

        private List<Square> findClosestValue(List<Square> squares) {
            long currentValue = current.getValue();

            Square closestValue = squares
                    .stream()
                    .reduce((square1, square2) -> abs(square1.getValue() - currentValue) < abs(square2.getValue() - currentValue) ? square1 : square2)
                    .get();

            return squares
                    .stream()
                    .filter(square -> square.getValue() == closestValue.getValue())
                    .collect(toList());
        }

        private List<Square> findMinX(List<Square> squares) {
            Square minX = squares
                    .stream()
                    .reduce((square1, square2) -> square1.x < square2.x ? square1 : square2)
                    .get();

            return squares
                    .stream()
                    .filter(square -> square.x == minX.x)
                    .collect(toList());
        }

        private static List<Square> legalMoves(Square current) {
            return asList(
                    new Square(current.x + 1, current.y + 2),
                    new Square(current.x + 2, current.y + 1),
                    new Square(current.x - 1, current.y - 2),
                    new Square(current.x - 2, current.y - 1),
                    new Square(current.x + 2, current.y - 1),
                    new Square(current.x + 1, current.y - 2),
                    new Square(current.x - 1, current.y + 2),
                    new Square(current.x - 2, current.y + 1));
        }
    }


    private static class Square {

        public final long x;
        public final long y;

        public Square(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getValue() {
            return x + y;
        }

        public long distanceTo(Square square) {
            return abs(x - square.x) + abs(y - square.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;

            Square square = (Square) o;

            return x == square.x && y == square.y;
        }

        @Override
        public int hashCode() {
            int result = (int) (x ^ (x >>> 32));
            result = 31 * result + (int) (y ^ (y >>> 32));
            return result;
        }
    }

    private static class VisitedSquare extends Square {

        private final long value;

        private VisitedSquare(Square square, long value) {
            super(square.x, square.y);
            this.value = value;
        }

        @Override
        public long getValue() {
            return value;
        }
    }

}
