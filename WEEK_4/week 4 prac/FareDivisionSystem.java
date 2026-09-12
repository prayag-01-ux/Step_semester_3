class FareSplitRecord {

    String tripId;
    double totalFare;
    int passengerCount;

    FareSplitRecord(String tripId, double totalFare, int passengerCount) {

        if (totalFare < 0)
            throw new IllegalArgumentException("Fare cannot be negative");

        if (passengerCount <= 0)
            throw new IllegalArgumentException("Invalid passenger count");

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    FareSplitRecord(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    FareSplitRecord(String tripId) {
        this(tripId, 0.0, 2);
    }

    double[] fareBreakdown() {

        double[] result = new double[passengerCount];

        long totalCents = Math.round(totalFare * 100);
        long base = totalCents / passengerCount;
        long remainder = totalCents % passengerCount;

        for (int i = 0; i < passengerCount; i++) {

            long cents = base;

            if (i == passengerCount - 1) {
                cents += remainder;
            }

            result[i] = cents / 100.0;
        }

        return result;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }
}

public class FareDivisionSystem {

    public static void main(String[] args) {

        FareSplitRecord fare =
            new FareSplitRecord("TRIP001", 100000, 3);

        double[] result = fare.fareBreakdown();

        for (double x : result) {
            System.out.printf("%.2f ", x);
        }

        System.out.println();

        System.out.println(
            fare.isConfirmationOverdue(2, 3)
        );
    }
}