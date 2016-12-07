import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Luke7 {

    private static final Pattern PATTERN = Pattern.compile("\\w+ (\\d*) \\w+ (\\w*)", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) throws URISyntaxException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Luke7.class.getClassLoader().getResource("luke7input.txt").openStream()))) {

            Direction result = reader.lines()
                    .map(instruction -> {
                        Matcher matcher = PATTERN.matcher(instruction.trim().toLowerCase());

                        int north = 0;
                        int west = 0;

                        if (matcher.matches()) {
                            int distance = Integer.valueOf(matcher.group(1));
                            String direction = matcher.group(2);

                            switch (direction) {
                                case "north":
                                    north += distance;
                                    break;
                                case "south":
                                    north -= distance;
                                    break;
                                case "west":
                                    west += distance;
                                    break;
                                case "east":
                                    west -= distance;
                                    break;
                            }
                        }

                        return new Direction(north, west);
                    })
                    .reduce(Direction::add)
                    .orElseThrow(() -> new RuntimeException("Something went terribly wrong"));

            System.out.println(result);
        }
    }

    private static class Direction {
        public final int north;
        public final int west;

        private Direction(int north, int west) {
            this.north = north;
            this.west = west;
        }

        public Direction add(Direction that) {
            return new Direction(this.north + that.north, this.west + that.west);
        }

        @Override
        public String toString() {
            return north + "," + west;
        }
    }
}
