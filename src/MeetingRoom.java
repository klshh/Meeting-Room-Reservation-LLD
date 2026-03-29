import java.util.ArrayList;
import java.util.List;

public class MeetingRoom {
    private Long roomId;
    private List<Interval> timeSlotBookedList;
    private List<Booking> roomBookingList;

    public MeetingRoom(Long roomId) {
        this.roomId = roomId;
        this.timeSlotBookedList = new ArrayList<>();
        this.roomBookingList = new ArrayList<>();
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public List<Interval> getTimeSlotBookedList() {
        return timeSlotBookedList;
    }

    public void setTimeSlotBookedList(List<Interval> timeSlotBookedList) {
        this.timeSlotBookedList = timeSlotBookedList;
    }

    public List<Booking> getRoomBookingList() {
        return roomBookingList;
    }

    public void setRoomBookingList(List<Booking> roomBookingList) {
        this.roomBookingList = roomBookingList;
    }
}
