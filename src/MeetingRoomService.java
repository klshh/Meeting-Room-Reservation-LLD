import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MeetingRoomService {

    ConcurrentHashMap<Long, MeetingRoom> meetingRoomList;

    public MeetingRoomService(ConcurrentHashMap<Long, MeetingRoom> meetingRoomList) {
        this.meetingRoomList = meetingRoomList;
    }


    public boolean getRoomStatusForGivenInterval(Long roomId, Interval requestedInterval) {
        MeetingRoom meetingRoom = getMeetingRoomById(roomId);

        Optional<Interval> interval = meetingRoom.getTimeSlotBookedList().stream().filter(interval1 -> {
            return interval1.getStartTime().isBefore(requestedInterval.getEndTime()) &&
                    interval1.getEndTime().isAfter(requestedInterval.getStartTime());

        }).findFirst();

        return interval.isEmpty();
    }

    public MeetingRoom getMeetingRoomById(Long roomId) {
        return meetingRoomList.get(roomId);
    }

    public void bookedRoomForTimeSlot(Long roomId, Interval interval, Booking booking) {
        MeetingRoom meetingRoom = meetingRoomList.get(roomId);
        List<Interval> intervalList = meetingRoom.getTimeSlotBookedList();
        intervalList.add(interval);
        meetingRoom.setTimeSlotBookedList(intervalList);

        List<Booking> bookingList = meetingRoom.getRoomBookingList();
        bookingList.add(booking);
        meetingRoom.setRoomBookingList(bookingList);
    }

    public List<MeetingRoom> getAvailableRooms(Interval interval) {
        Set<Map.Entry<Long, MeetingRoom>> meetingRooms = meetingRoomList.entrySet().stream().filter(roomEntry-> {

            Optional<Interval> intervalMatch = roomEntry.getValue().getTimeSlotBookedList().stream().filter(roomInterval -> roomInterval.getStartTime().isBefore(interval.getEndTime())
                    && roomInterval.getEndTime().isAfter(interval.getStartTime())).findFirst();
            return intervalMatch.isEmpty();
        }).collect(Collectors.toSet());


        return meetingRooms.stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }
}
