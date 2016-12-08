import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Luke8 {

    private static final Pattern PAIR_PATTERN = Pattern.compile("\\((\\d+),(\\d+)\\)");

    private static final int NUMBER_OF_PLAYERS = 1337;
    private static final int BOARD_SIZE = 10 * 9;

    public static void main(String[] args) throws IOException {
        try (BufferedReader diceThrowsFromFile = new BufferedReader(new InputStreamReader(Luke8.class.getClassLoader().getResource("luke8dicethrows.txt").openStream()));
             BufferedReader laddersFromFile = new BufferedReader(new InputStreamReader(Luke8.class.getClassLoader().getResource("luke8ladders.txt").openStream()))) {

            int usedLadderCount = 0;
            int currentPlayer = 0;

            Map<Integer, Integer> playerPosition = new HashMap<>(NUMBER_OF_PLAYERS);
            Map<Integer, Integer> ladders = readLadderInput(laddersFromFile);

            String line = diceThrowsFromFile.readLine();
            int lineNumber = 0;
            while (line != null) {

                currentPlayer = lineNumber++ % NUMBER_OF_PLAYERS + 1;
                int position = playerPosition.getOrDefault(currentPlayer, 1);
                int diceThrow = Integer.valueOf(line);

                int newPosition = position + diceThrow;

                if (newPosition == BOARD_SIZE) {
                    break;
                }

                if (newPosition < BOARD_SIZE && ladders.containsKey(newPosition)) {
                    newPosition = ladders.get(newPosition);
                    usedLadderCount++;
                }

                playerPosition.put(currentPlayer, newPosition);

                line = diceThrowsFromFile.readLine();
            }

            System.out.println(usedLadderCount * currentPlayer);
        }
    }

    private static Map<Integer, Integer> readLadderInput(BufferedReader laddersFromFile) {

        Map<Integer, Integer> ladders = new HashMap<>();

        laddersFromFile
                .lines()
                .forEach(line -> {
                    String[] ladderPairs = line.split(", ");
                    for (String ladderPair : ladderPairs) {
                        Matcher matcher = PAIR_PATTERN.matcher(ladderPair);
                        if (matcher.matches()) {
                            ladders.put(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
                        }
                    }
                });

        return ladders;
    }
}
