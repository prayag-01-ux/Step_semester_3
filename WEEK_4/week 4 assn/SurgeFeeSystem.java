final class SurgeFeeRecord {

    private final double minimumSurgePercent;

    SurgeFeeRecord(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    final double calculateSurgeFee(
            double orderValue,
            int delayMinutes) {

        if (orderValue < 0)
            throw new IllegalArgumentException(
                "Invalid order value"
            );

        if (delayMinutes < 0)
            throw new IllegalArgumentException(
                "Invalid delay"
            );

        if (delayMinutes == 0)
            return 0.0;

        double fee = 0.0;

        // Minutes 1-5 = 0.5%
        int first = Math.min(delayMinutes, 5);

        fee += first * orderValue * 0.005;

        // Minutes 6-15 = 1%
        if (delayMinutes > 5) {

            int second =
                Math.min(delayMinutes - 5, 10);

            fee += second * orderValue * 0.01;
        }

        // Minute 16 onwards = 2%
        if (delayMinutes > 15) {

            int third = delayMinutes - 15;

            fee += third * orderValue * 0.02;
        }

        double minimumFee =
            orderValue * minimumSurgePercent / 100.0;

        return Math.max(fee, minimumFee);
    }
}

public class SurgeFeeSystem {

    public static void main(String[] args) {

        SurgeFeeRecord calculator =
            new SurgeFeeRecord(1.0);

        System.out.println(
            calculator.calculateSurgeFee(500, 0)
        );

        System.out.println(
            calculator.calculateSurgeFee(500, 1)
        );

        System.out.println(
            calculator.calculateSurgeFee(500, 16)
        );
    }
}