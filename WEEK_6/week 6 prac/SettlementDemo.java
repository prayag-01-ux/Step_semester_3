class EntryPass {

    private static int counter = 1000;

    private final String passId;

    protected double price;
    protected double balance;

    EntryPass(double price) {

        counter++;

        passId = "PS-" + counter;

        this.price = price;
        this.balance = price;
    }

    void pay(double amount) {
        balance -= amount;
    }

    void pay(double amount, String mode) {

        System.out.println(
            "Payment Mode: " + mode
        );

        pay(amount);
    }

    double getBalanceDue() {
        return balance;
    }

    String getPassId() {
        return passId;
    }

    static boolean isValidPromoCode(String code) {

        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'F') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(3))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }

        return true;
    }

    static int getPassesIssued() {
        return counter - 1000;
    }
}

class TeamPass extends EntryPass {

    int groupSize;

    TeamPass(double price, int groupSize) {
        super(price);
        this.groupSize = groupSize;
    }
}

public class SettlementDemo {

    static String processNightlySettlement(
        EntryPass[] passes
    ) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (EntryPass p : passes) {

            if (p == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (p instanceof TeamPass) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               group + " group | " +
               individual + " individual";
    }

    public static void main(String[] args) {

        EntryPass p1 =
            new EntryPass(500);

        System.out.println(
            p1.getPassId()
        );

        System.out.println(
            EntryPass.getPassesIssued()
        );

        System.out.println(
            EntryPass.isValidPromoCode("F123A")
        );

        System.out.println(
            EntryPass.isValidPromoCode("F12A")
        );

        System.out.println(
            EntryPass.isValidPromoCode("X123A")
        );

        p1.pay(200);

        p1.pay(200, "UPI");

        System.out.println(
            p1.getBalanceDue()
        );

        EntryPass[] passes = {
            new TeamPass(2000, 5),
            null,
            new EntryPass(500)
        };

        System.out.println(
            processNightlySettlement(passes)
        );
    }
}