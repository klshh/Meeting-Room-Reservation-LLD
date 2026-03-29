import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        Interval interval = new Interval(LocalDateTime.of(2025, 11, 30, 12, 0),
                LocalDateTime.of(2025, 11, 30, 12, 30));
        MeetingRoomFacade meetingRoomReservation = getMeetingRoomFacade();

        List<MeetingRoom> getAvailableRoomsAsPerInterval = meetingRoomReservation.getAvailableRooms(interval);
        System.out.println(" Meeting rooms list initially :" + getAvailableRoomsAsPerInterval);

        meetingRoomReservation.bookRoom(1L,1L,LocalDateTime.of(2026,3,29,12,0),LocalDateTime.of(
                2026, 3,29,12,30));

        meetingRoomReservation.bookRoom(2L,2L,LocalDateTime.of(2026,3,29,12,0),
                LocalDateTime.of(2026,3,29,13,59));

        Interval newInterval = new Interval(LocalDateTime.of(2026,3,29,12,0),
                LocalDateTime.of(2026,3,29,13,0));

        List<MeetingRoom> getAvailableRoomsAfterBooking = meetingRoomReservation.getAvailableRooms(newInterval);

        System.out.println(" Meeting rooms list after booking 2 meetings  :" + getAvailableRoomsAfterBooking);




    }

    private static MeetingRoomFacade getMeetingRoomFacade() {
        Employee e1 = new Employee(1L, "Ram");
        Employee e2 = new Employee(2L, "Shyam");
        Employee e3 = new Employee(3L, "Kalash");

        HashMap<Long, Employee> employeeList = new HashMap<>();
        employeeList.put(1L, e1);
        employeeList.put(2L, e2);
        employeeList.put(3L,e3);

        ConcurrentHashMap<Long, MeetingRoom> meetingRoomMap = new ConcurrentHashMap<>();

        MeetingRoom meetingRoom1 = new MeetingRoom(1L);
        MeetingRoom meetingRoom2 = new MeetingRoom(2L);
        MeetingRoom meetingRoom3 = new MeetingRoom(3L);
        MeetingRoom meetingRoom4 = new MeetingRoom(4L);
        MeetingRoom meetingRoom5 = new MeetingRoom(5L);
        meetingRoomMap.put(1L,meetingRoom1);
        meetingRoomMap.put(2L,meetingRoom2);
        meetingRoomMap.put(3L,meetingRoom3);
        meetingRoomMap.put(4L,meetingRoom4);
        meetingRoomMap.put(5L,meetingRoom5);


        ConcurrentHashMap<Long, Booking> bookingsMap = new ConcurrentHashMap<>();

        BookingService bookingService = new BookingService(bookingsMap);
        EmployeeService employeeService = new EmployeeService(employeeList);
        MeetingRoomService meetingRoomService = new MeetingRoomService(meetingRoomMap);

        return new MeetingRoomFacade(bookingService,employeeService, meetingRoomService);
    }
}