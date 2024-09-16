import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BookingService {
    private Map<String, LocalDateTime[]> bookings = new HashMap<>();

    public boolean book(String userId, LocalDateTime from, LocalDateTime to) throws CantBookException {
        if (checkTimeInBD(from, to)) {
            return createBook(userId, from, to);
        }
        throw new CantBookException("The time slot is already booked.");
    }

    public boolean checkTimeInBD(LocalDateTime from, LocalDateTime to) {
        for (LocalDateTime[] slot : bookings.values()) {
            if ((from.isEqual(slot[0]) || from.isAfter(slot[0])) && (from.isBefore(slot[1]) || from.isEqual(slot[1]))) {
                return false; // Slot is taken
            }
            if ((to.isEqual(slot[1]) || to.isBefore(slot[1])) && (to.isAfter(slot[0]) || to.isEqual(slot[0]))) {
                return false; // Slot is taken
            }
        }
        return true; // Slot is available
}

public boolean createBook(String userId, LocalDateTime from, LocalDateTime to) {
    bookings.put(userId, new LocalDateTime[]{from, to});
    return true; // Booking successful
    }
}
