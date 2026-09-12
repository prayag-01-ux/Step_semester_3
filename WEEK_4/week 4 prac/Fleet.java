class FleetAccount {

    static double penaltyRate;

    static {
        penaltyRate = 0.01;
    }

    String bookingId;
    double ticketFare;

    FleetAccount(String bookingId, double ticketFare) {

        if (ticketFare < 0)
            throw new IllegalArgumentException("Invalid fare");

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    FleetAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0)
            throw new IllegalArgumentException("Invalid minutes");

        return ticketFare * penaltyRate * minutesLate;
    }
}

class SleeperAccount extends FleetAccount {

    SleeperAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    @Override
    final double calculatePenalty(int minutesLate) {

        return super.calculatePenalty(minutesLate) * 1.5;
    }
}

public class Fleet {

    static void processAccount(
            FleetAccount account,
            double amount,
            int minutesLate) {

        double penalty = account.calculatePenalty(minutesLate);

        System.out.println(
            "Booking: " + account.bookingId +
            " Amount: " + amount +
            " Penalty: " + penalty
        );
    }

    static void processBatch(
            FleetAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        if (accounts == null ||
            amounts == null ||
            minutesLateArray == null) {

            System.out.println("Invalid arrays");
            return;
        }

        int length = Math.min(
            accounts.length,
            Math.min(amounts.length, minutesLateArray.length)
        );

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;

        double grandTotal = 0;

        for (int i = 0; i < length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            try {

                processAccount(
                    accounts[i],
                    amounts[i],
                    minutesLateArray[i]
                );

                grandTotal +=
                    accounts[i].calculatePenalty(minutesLateArray[i]);

                processed++;

                if (accounts[i] instanceof SleeperAccount)
                    sleeper++;
                else
                    regular++;

            } catch (Exception e) {
                System.out.println("Invalid account skipped");
            }
        }

        System.out.println(
            processed + " processed | " +
            nullSkipped + " null skipped | " +
            sleeper + " sleeper | " +
            regular + " regular"
        );

        System.out.println(
            "Grand total penalties = " + grandTotal
        );
    }

    public static void main(String[] args) {

        FleetAccount[] accounts = {
            new SleeperAccount("BK001", 2000),
            null,
            new FleetAccount("BK002", 1200)
        };

        double[] amounts = {1200, 900, 700};

        int[] minutes = {10, 5, 0};

        processBatch(accounts, amounts, minutes);
    }
}