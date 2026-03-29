import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class MeetingRoomFacade {
//    book a room
//    getAvailableRooms
//    listBookingForRooms
//    listBookingsForEmployees

    BookingService bookingService;
    AtomicLong bookingId = new AtomicLong(0);
    EmployeeService employeeService;
    MeetingRoomService meetingRoomService;

    public MeetingRoomFacade(BookingService bookingService, EmployeeService employeeService, MeetingRoomService meetingRoomService) {
        this.bookingService = bookingService;
        this.employeeService = employeeService;
        this.meetingRoomService = meetingRoomService;
    }

    void bookRoom(Long employeeId, Long roomId, LocalDateTime startTime, LocalDateTime endTime) throws Exception {

        Interval interval = new Interval(startTime, endTime);

//        check roomStatus
        boolean roomBookingAvailable = meetingRoomService.getRoomStatusForGivenInterval(roomId, interval);

        if(roomBookingAvailable){
            Employee employee = employeeService.getEmployeeById(employeeId);
            MeetingRoom meetingRoom = meetingRoomService.getMeetingRoomById(roomId);
            Booking booking = new Booking(bookingId.incrementAndGet(), employee, meetingRoom, interval);

            bookingService.addBooking(booking);
            employeeService.addMeetingForEmployee(employeeId, booking);
            meetingRoomService.bookedRoomForTimeSlot(roomId, interval, booking);
        }else {
            System.out.println(" This room is already booked by someone else for given time interval");
        }

    }

    public List<MeetingRoom> getAvailableRooms(Interval interval) {
        return meetingRoomService.getAvailableRooms(interval);

    }
}
