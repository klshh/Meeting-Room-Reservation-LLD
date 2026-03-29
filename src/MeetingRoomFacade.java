import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MeetingRoomFacade {
//    book a room
//    getAvailableRooms
//    listBookingForRooms
//    listBookingsForEmployees

    BookingService bookingService;
    AtomicLong bookingId = new AtomicLong(0);
    EmployeeService employeeService;
    MeetingRoomService meetingRoomService;
    private final ConcurrentHashMap<Long, Lock> roomLocks = new ConcurrentHashMap<>();

    public MeetingRoomFacade(BookingService bookingService, EmployeeService employeeService, MeetingRoomService meetingRoomService) {
        this.bookingService = bookingService;
        this.employeeService = employeeService;
        this.meetingRoomService = meetingRoomService;
    }

    void bookRoom(Long employeeId, Long roomId, LocalDateTime startTime, LocalDateTime endTime) throws Exception {

        Interval interval = new Interval(startTime, endTime);

        Lock roomLock = roomLocks.computeIfAbsent(roomId, k -> new ReentrantLock());
        roomLock.lock();
        try {
//            check roomStatus
            boolean roomBookingAvailable = meetingRoomService.getRoomStatusForGivenInterval(roomId, interval);

            if (roomBookingAvailable) {
                Employee employee = employeeService.getEmployeeById(employeeId);
                MeetingRoom meetingRoom = meetingRoomService.getMeetingRoomById(roomId);
                Booking booking = new Booking(bookingId.incrementAndGet(), employee, meetingRoom, interval);

                bookingService.addBooking(booking);
                employeeService.addMeetingForEmployee(employeeId, booking);
                meetingRoomService.bookedRoomForTimeSlot(roomId, interval, booking);
            } else {
                System.out.println(" This room is already booked by someone else for given time interval");
            }
        } finally {
            roomLock.unlock();
        }

    }

    public List<MeetingRoom> getAvailableRooms(Interval interval) {
        return meetingRoomService.getAvailableRooms(interval);

    }
}
