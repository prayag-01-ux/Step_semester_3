final class BoardingPenaltyRecord {

    private final double minimumPenaltyPercent;

    BoardingPenaltyRecord(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    final double calculatePenalty(double ticketFare, int minutesLate) {

        if (ticketFare < 0)
            throw new IllegalArgumentException("Invalid ticket fare");

        if (minutesLate < 0)
            throw new IllegalArgumentException("Invalid minutes");

        if (minutesLate == 0)
            return 0.0;

        double penalty = 0.0;

        int firstTier = Math.min(minutesLate, 5);
        penalty += firstTier * ticketFare * 0.005;

        if (minutesLate > 5) {
            int secondTier = Math.min(minutesLate - 5, 10);
            penalty += secondTier * ticketFare * 0.01;
        }

        if (minutesLate > 15) {
            int thirdTier = minutesLate - 15;
            penalty += thirdTier * ticketFare * 0.02;
        }

        double minimumPenalty =
            ticketFare * minimumPenaltyPercent / 100.0;

        return Math.max(penalty, minimumPenalty);
    }
}

public class BoardingFeeSystem {

    public static void main(String[] args) {

        BoardingPenaltyRecord calculator =
            new BoardingPenaltyRecord(1.0);

        System.out.println(
            calculator.calculatePenalty(1000, 0)
        );

        System.out.println(
            calculator.calculatePenalty(1000, 1)
        );

        System.out.println(
            calculator.calculatePenalty(1000, 16)
        );
    }
}