import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Luke9 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader transactions = new BufferedReader(new InputStreamReader(Luke9.class.getClassLoader().getResource("luke9input.txt").openStream()))) {

            Map<String, Long> accounts = new HashMap<>();

            transactions
                    .lines()
                    .map(transaction -> {
                        String[] split = transaction.split(",");
                        return new Transaction(split[0], split[1], Long.valueOf(split[2]));
                    })
                    .forEach(transaction -> {
                        accounts.put(transaction.to, transaction.amount + accounts.getOrDefault(transaction.to, 0L));
                        if (!transaction.isPrintingMoney) {
                            accounts.put(transaction.from, accounts.getOrDefault(transaction.from, 0L) - transaction.amount);
                        }
                    });

            long moreThanTen = accounts
                    .values()
                    .stream()
                    .filter(amount -> amount > 10)
                    .count();

            System.out.println(moreThanTen);
        }
    }

    private static class Transaction {
        public final String from;
        public final String to;
        public final long amount;
        public final boolean isPrintingMoney;

        private Transaction(String from, String to, long amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
            this.isPrintingMoney = from.equalsIgnoreCase("None");
        }
    }
}
