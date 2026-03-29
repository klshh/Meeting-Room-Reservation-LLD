import java.time.LocalDateTime;

public class Interval {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Interval(LocalDateTime startTime, LocalDateTime endTime) {

        if(endTime.isBefore(startTime)){
            throw new IllegalArgumentException("Please provide the correct time");
        }

        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
