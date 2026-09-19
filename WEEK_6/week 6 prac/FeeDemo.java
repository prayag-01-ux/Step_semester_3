class Registration {

    protected double price;
    protected double balance;

    private double[] feeHistory = new double[10];
    private int feeCount = 0;

    Registration(double price) {
        this.price = price;
        this.balance = price;
    }

    void pay(double amount) {
        balance -= amount;
    }

    protected void applyLateFee(double amount) {
        balance += amount;

        feeHistory[feeCount] = amount;
        feeCount++;
    }

    double getBalanceDue() {
        return balance;
    }

    double[] getLateFeeHistory() {

        double[] result = new double[feeCount];

        for (int i = 0; i < feeCount; i++) {
            result[i] = feeHistory[i];
        }

        return result;
    }
}

class WorkshopRegistration extends Registration {

    WorkshopRegistration(double price) {
        super(price);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class FeeDemo {

    public static void main(String[] args) {

        WorkshopRegistration w =
            new WorkshopRegistration(1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(
            w.getBalanceDue()
        );

        double[] history =
            w.getLateFeeHistory();

        history[0] = 999;

        double[] original =
            w.getLateFeeHistory();

        System.out.println(original[0]);
    }
}