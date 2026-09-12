class DeliveryAccountRecord {

    static double surgeRate;

    static {
        surgeRate = 0.01;
    }

    String studentId;
    double orderValue;

    DeliveryAccountRecord(
            String studentId,
            double orderValue) {

        if (orderValue < 0)
            throw new IllegalArgumentException(
                "Invalid order value"
            );

        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    DeliveryAccountRecord(String studentId) {
        this(studentId, 0.0);
    }

    final double calculateSurgeFee(int delayMinutes) {

        if (delayMinutes < 0)
            throw new IllegalArgumentException(
                "Invalid delay"
            );

        return orderValue * surgeRate * delayMinutes;
    }
}

class PremiumAccountRecord extends DeliveryAccountRecord {

    PremiumAccountRecord(
            String studentId,
            double orderValue) {

        super(studentId, orderValue);
    }

    @Override
    final double calculateSurgeFee(int delayMinutes) {

        return super.calculateSurgeFee(delayMinutes) * 1.5;
    }
}

public class KitchenReconciliationSystem {

    static void processAccount(
            DeliveryAccountRecord account,
            double amount,
            int delayMinutes) {

        double fee =
            account.calculateSurgeFee(delayMinutes);

        System.out.println(
            "Student: " + account.studentId
        );

        System.out.println(
            "Amount: " + amount
        );

        System.out.println(
            "Surge Fee: " + fee
        );
    }

    static void processBatch(
            DeliveryAccountRecord[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        if (accounts == null ||
            amounts == null ||
            delayMinutesArray == null) {

            System.out.println("Invalid input arrays");
            return;
        }

        int length = Math.min(
            accounts.length,
            Math.min(
                amounts.length,
                delayMinutesArray.length
            )
        );

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;

        double grandTotal = 0.0;

        for (int i = 0; i < length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            try {

                processAccount(
                    accounts[i],
                    amounts[i],
                    delayMinutesArray[i]
                );

                double fee =
                    accounts[i].calculateSurgeFee(
                        delayMinutesArray[i]
                    );

                grandTotal += fee;
                processed++;

                if (accounts[i] instanceof PremiumAccountRecord)
                    premium++;
                else
                    regular++;

            } catch (Exception e) {

                System.out.println(
                    "Invalid account skipped"
                );
            }
        }

        System.out.println(
            processed + " processed | " +
            nullSkipped + " null skipped | " +
            premium + " premium | " +
            regular + " regular"
        );

        System.out.println(
            "Grand total surge fees = " +
            grandTotal
        );
    }

    public static void main(String[] args) {

        DeliveryAccountRecord[] accounts = {

            new PremiumAccountRecord(
                "STU001",
                500
            ),

            null,

            new DeliveryAccountRecord(
                "STU002",
                300
            )
        };

        double[] amounts = {
            500, 400, 300
        };

        int[] delays = {
            10, 5, 0
        };

        processBatch(
            accounts,
            amounts,
            delays
        );
    }
}