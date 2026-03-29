import java.util.HashMap;

public class EmployeeService {

    private HashMap<Long, Employee> employeeList;

    public EmployeeService(HashMap<Long, Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public HashMap<Long, Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(HashMap<Long, Employee> employeeList) {
        this.employeeList = employeeList;
    }

    void addMeetingForEmployee(Long employeeId, Booking bookingDetails) throws Exception {
        Employee employee = employeeList.get(employeeId);
        if(employee == null) {
            throw new Exception("Employee Not found");
        }
        employee.addMeeting(bookingDetails);
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeList.get(employeeId);
    }
}
