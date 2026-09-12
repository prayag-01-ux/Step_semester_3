import java.util.HashSet;

class BusTicketRecord {
    String passengerName;
    String destination;
    boolean checkedIn;

    BusTicketRecord(String passengerName, String destination) {
        if (passengerName == null || passengerName.trim().isEmpty())
            throw new IllegalArgumentException("Invalid passenger name");

        if (!passengerName.matches("[a-zA-Z ]+"))
            throw new IllegalArgumentException("Invalid passenger name");

        if (destination == null || destination.trim().isEmpty())
            throw new IllegalArgumentException("Invalid destination");

        this.passengerName = passengerName;
        this.destination = destination;
        this.checkedIn = false;
    }

    void markCheckedIn() {
        if (checkedIn) {
            System.out.println("Already checked in");
        } else {
            checkedIn = true;
            System.out.println("Checked in successfully");
        }
    }
}

public class BusTicketValidator {

    static void processBatch(String[][] rawBookings) {
        HashSet<String> accepted = new HashSet<>();

        int valid = 0;
        int rejected = 0;
        int duplicate = 0;

        for (String[] booking : rawBookings) {

            try {
                if (booking == null || booking.length < 2)
                    throw new IllegalArgumentException();

                BusTicketRecord ticket =
                    new BusTicketRecord(booking[0], booking[1]);

                String key = ticket.passengerName.toLowerCase()
                           + "|" + ticket.destination.toLowerCase();

                if (accepted.contains(key)) {
                    duplicate++;
                } else {
                    accepted.add(key);
                    valid++;
                }

            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid);
        System.out.println("Rejected: " + rejected);
        System.out.println("Duplicates skipped: " + duplicate);
    }

    public static void main(String[] args) {

        String[][] bookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        processBatch(bookings);
    }
}