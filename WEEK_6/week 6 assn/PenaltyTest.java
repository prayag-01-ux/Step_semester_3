class BaseRegistration {

    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    private double[] penaltyHistory;
    private int penaltyCount;


    BaseRegistration(String bibNumber, double entryFee) {

        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid Bib");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;

        penaltyHistory = new double[10];
        penaltyCount = 0;
    }


    void pay(double amount) {
        amountPaid += amount;
    }


    double getBalanceDue() {

        double totalPenalty = 0;

        for (int i = 0; i < penaltyCount; i++) {
            totalPenalty += penaltyHistory[i];
        }

        return entryFee - amountPaid + totalPenalty;
    }


    protected void applyLateFee(double amount) {

        if (penaltyCount < 10) {
            penaltyHistory[penaltyCount] = amount;
            penaltyCount++;
        }
    }


    double[] getLateFeeHistory() {

        double[] copy = new double[penaltyCount];

        for (int i = 0; i < penaltyCount; i++) {
            copy[i] = penaltyHistory[i];
        }

        return copy;
    }
}


class SpecialRunner extends BaseRegistration {

    String category;


    SpecialRunner(
        String bibNumber,
        double entryFee,
        String category
    ) {

        super(bibNumber, entryFee);
        this.category = category;
    }


    @Override
    protected void applyLateFee(double amount) {

        super.applyLateFee(amount * 2);
    }
}


public class PenaltyTest {

    public static void main(String[] args) {

        SpecialRunner runner =
            new SpecialRunner(
                "BIB2001",
                80,
                "Open 10K"
            );

        runner.pay(30);

        runner.applyLateFee(20);

        System.out.println(
            runner.getBalanceDue()
        );

        double[] history =
            runner.getLateFeeHistory();

        System.out.println(history[0]);

        history[0] = 999;

        System.out.println(
            runner.getLateFeeHistory()[0]
        );
    }
}