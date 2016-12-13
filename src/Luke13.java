import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Luke13 {

    private static final Pattern PATTERN = Pattern.compile("([\\w+\\s]+)\\s(\\d+),(\\d+)\\s\\w+\\s(\\d+),(\\d+)");

    public static void main(String[] args) throws IOException {
        try (BufferedReader commands = new BufferedReader(new InputStreamReader(Luke13.class.getClassLoader().getResource("luke13input.txt").openStream()))) {

            boolean[][] lights = new boolean[10000][10000];

            commands
                    .lines()
                    .map(command -> {
                        Matcher matcher = PATTERN.matcher(command);
                        if (matcher.matches()) {
                            String onOffToggle = matcher.group(1);
                            int fromX = Integer.parseInt(matcher.group(2));
                            int fromY = Integer.parseInt(matcher.group(3));
                            int toX = Integer.parseInt(matcher.group(4));
                            int toY = Integer.parseInt(matcher.group(5));

                            return new Command(onOffToggle, fromX, fromY, toX, toY);
                        }

                        throw new RuntimeException("No match found, fix your regex");
                    })
                    .forEach(command -> {
                        for (int x = command.fromX; x <= command.toX; x++) {
                            for (int y = command.fromY; y <= command.toY; y++) {
                                switch (command.toggle) {
                                    case ON:
                                        lights[x][y] = true;
                                        break;
                                    case OFF:
                                        lights[x][y] = false;
                                        break;
                                    case TOGGLE:
                                        lights[x][y] = !lights[x][y];
                                        break;
                                }
                            }
                        }
                    });


            int turnedOn = 0;
            for (boolean[] lightCol : lights) {
                for (boolean light : lightCol) {
                    if (light) {
                        turnedOn++;
                    }
                }
            }

            System.out.println(turnedOn);
        }
    }

    private static class Command {

        public final Toggle toggle;
        public final int fromX;
        public final int fromY;
        public final int toX;
        public final int toY;

        private Command(String command, int fromX, int fromY, int toX, int toY) {

            String lowerCaseCommand = command.toLowerCase();
            if ("turn on".equals(lowerCaseCommand)) {
                this.toggle = Toggle.ON;
            } else if ("turn off".equals(lowerCaseCommand)) {
                this.toggle = Toggle.OFF;
            } else if ("toggle".equals(lowerCaseCommand)) {
                this.toggle = Toggle.TOGGLE;
            } else {
                throw new IllegalArgumentException("Unrecognized command");
            }

            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
        }

        public enum Toggle {
            ON, OFF, TOGGLE,
        }
    }
}

