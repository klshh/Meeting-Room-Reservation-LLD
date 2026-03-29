public class Booking {
    private Long bookingId;
    private Employee bookedBy;
    private MeetingRoom bookedRoom;
    private Interval timeSlot;
    private BookingStatus bookingStatus;

    public Booking(Long bookingId, Employee bookedBy, MeetingRoom bookedRoom, Interval timeSlot) {
        this.bookingId = bookingId;
        this.bookedBy = bookedBy;
        this.bookedRoom = bookedRoom;
        this.timeSlot = timeSlot;
        this.bookingStatus = BookingStatus.ACTIVE;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Employee getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(Employee bookedBy) {
        this.bookedBy = bookedBy;
    }

    public MeetingRoom getBookedRoom() {
        return bookedRoom;
    }

    public void setBookedRoom(MeetingRoom bookedRoom) {
        this.bookedRoom = bookedRoom;
    }

    public Interval getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Interval timeSlot) {
        this.timeSlot = timeSlot;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
