import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class NormalizeOffset {
    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        OffsetDateTime dt = LocalDateTime.parse("2024-01-02T00:00:00").atZone(ZoneId.systemDefault()).toOffsetDateTime();
        OffsetDateTime normalizedDt = NormalizeOffsetAlgorithm.normalize(dt, -3*60*60);
        assert normalizedDt.format(fmt).equals("2024-01-01 21:00:00");

        TimeZone.setDefault(TimeZone.getTimeZone("Canada/Central"));
        dt = LocalDateTime.parse("2024-01-02T00:00:00").atZone(ZoneId.systemDefault()).toOffsetDateTime();
        normalizedDt = NormalizeOffsetAlgorithm.normalize(dt, -3*60*60);
        assert normalizedDt.format(fmt).equals("2024-01-01 21:00:00");
    }
}

class NormalizeOffsetAlgorithm {
    public static OffsetDateTime normalize(OffsetDateTime dt, int desiredOffsetSeconds) {
        if (dt.getOffset().getTotalSeconds() == desiredOffsetSeconds) {
            return dt;
        }

        int factor = desiredOffsetSeconds - dt.getOffset().getTotalSeconds();
        return dt.plusSeconds(desiredOffsetSeconds);
    }
}