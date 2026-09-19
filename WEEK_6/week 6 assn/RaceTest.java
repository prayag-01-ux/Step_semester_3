class MarathonEntry {

    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    MarathonEntry(String bibNumber, double entryFee) {

        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid Bib Number");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;
    }

    void pay(double amount) {
        amountPaid += amount;
    }

    double getBalanceDue() {
        return entryFee - amountPaid;
    }

    static String registerBatch(String[] bibNumbers, double entryFee) {

        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {
            try {
                new MarathonEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered +
               " | Rejected: " + rejected;
    }
}


class MarathonRunner extends MarathonEntry {

    String category;

    MarathonRunner(String bibNumber, double entryFee, String category) {

        super(bibNumber, entryFee);
        this.category = category;
    }
}


public class RaceTest {

    public static void main(String[] args) {

        MarathonRunner runner =
            new MarathonRunner("BIB2001", 80, "Open 10K");

        runner.pay(30);

        System.out.println(runner.getBalanceDue());

        String[] bibs = {
            "BIB1",
            "B1",
            "BIB2"
        };

        System.out.println(
            MarathonEntry.registerBatch(bibs, 80)
        );
    }
}