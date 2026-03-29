import java.util.ArrayList;
import java.util.List;

public class Employee {
    private Long employeeId;
    private String employeeName;

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    private List<Booking> bookingList;

    public Employee(Long employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.bookingList = new ArrayList<>();
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void addMeeting(Booking booking){
        this.bookingList.add(booking);
    }

    public void removeMeeting(Booking booking){
        this.bookingList.remove(booking);
    }
}
