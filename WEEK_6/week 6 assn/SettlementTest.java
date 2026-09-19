class Registration {

    private static int counter = 0;

    private final String entryCode;

    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;


    Registration(
        String bibNumber,
        double entryFee
    ) {

        if (bibNumber == null ||
            bibNumber.trim().length() < 4) {

            throw new IllegalArgumentException(
                "Invalid Bib"
            );
        }


        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;


        counter++;

        entryCode = "ENT" + counter;
    }


    void pay(double amount) {

        amountPaid += amount;
    }


    void pay(
        double amount,
        String mode
    ) {

        pay(amount);

        System.out.println(
            "Paying via " + mode
        );
    }


    double getBalanceDue() {

        return entryFee - amountPaid;
    }


    static boolean isValidDiscountCode(
        String code
    ) {

        if (code == null ||
            code.length() != 5) {

            return false;
        }


        if (code.charAt(0) != 'M') {
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


    static int getBibCounter() {

        return counter;
    }
}


class RaceRunner extends Registration {

    String category;


    RaceRunner(
        String bibNumber,
        double entryFee,
        String category
    ) {

        super(bibNumber, entryFee);
        this.category = category;
    }
}


class TeamRelay extends Registration {

    int teamSize;


    TeamRelay(
        String bibNumber,
        double entryFee,
        int teamSize
    ) {

        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }
}


public class SettlementTest {


    static String settleNight(
        Registration[] entries
    ) {

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;


        for (Registration entry : entries) {

            if (entry == null) {

                nullSkipped++;
                continue;
            }


            processed++;


            if (entry instanceof TeamRelay) {

                relay++;

            } else {

                individual++;
            }
        }


        return processed
            + " processed | "
            + nullSkipped
            + " null skipped | "
            + relay
            + " relay | "
            + individual
            + " individual";
    }


    public static void main(String[] args) {


        RaceRunner runner =
            new RaceRunner(
                "BIB2001",
                80,
                "Open"
            );


        TeamRelay relay =
            new TeamRelay(
                "BIB4001",
                300,
                4
            );


        System.out.println(
            Registration.isValidDiscountCode(
                "M123A"
            )
        );


        System.out.println(
            Registration.isValidDiscountCode(
                "M12A"
            )
        );


        System.out.println(
            Registration.isValidDiscountCode(
                "X123A"
            )
        );


        runner.pay(10, "UPI");


        Registration[] entries = {
            runner,
            null,
            relay
        };


        System.out.println(
            settleNight(entries)
        );


        System.out.println(
            Registration.getBibCounter()
        );
    }
}