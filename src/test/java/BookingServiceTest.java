import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;


public class BookingServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(BookingServiceTest.class);

    @Test
    public void testBooking() {
        BookingService service = new BookingService();
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = from.plusHours(1);

        try {
            boolean result = service.book("user1", from, to);
            logger.info("Booking successful: {}", result);
        } catch (CantBookException e) {
            logger.error("Booking failed: {}", e.getMessage());
        }

    }
}
