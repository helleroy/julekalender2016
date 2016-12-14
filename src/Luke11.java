import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Luke11 {

    public static void main(String[] args) {
        int secondsPerDay = 60 * 60 * 25;

        LocalDateTime localDateTime = LocalDateTime
                .ofEpochSecond(0, 0, ZoneOffset.UTC)
                .plusDays(Integer.MAX_VALUE / secondsPerDay)
                .plusSeconds(Integer.MAX_VALUE % secondsPerDay);

        System.out.println(localDateTime);
    }
}
