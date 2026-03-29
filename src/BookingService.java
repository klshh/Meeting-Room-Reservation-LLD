import java.util.concurrent.ConcurrentHashMap;

public class BookingService {
    ConcurrentHashMap<Long,Booking> bookings;

    public BookingService(ConcurrentHashMap<Long, Booking> bookings) {
        this.bookings = bookings;
    }

    public ConcurrentHashMap<Long, Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ConcurrentHashMap<Long, Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
    }
}
