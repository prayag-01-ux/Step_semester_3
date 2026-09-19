class CompetitionEntry {

    protected String bibNumber;
    protected double entryFee;


    CompetitionEntry(
        String bibNumber,
        double entryFee
    ) {

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }


    void announce() {

        System.out.println(
            "Competition Entry | Bib: "
            + bibNumber
        );
    }


    double getBalanceDue() {
        return entryFee;
    }
}


class IndividualRunner extends CompetitionEntry {

    String category;


    IndividualRunner(
        String bibNumber,
        double entryFee,
        String category
    ) {

        super(bibNumber, entryFee);
        this.category = category;
    }


    @Override
    void announce() {

        System.out.println(
            "Runner | Bib: " + bibNumber +
            " | Category: " + category +
            " | Balance: " + getBalanceDue()
        );
    }
}


class RelaySquad extends CompetitionEntry {

    int teamSize;


    RelaySquad(
        String bibNumber,
        double entryFee,
        int teamSize
    ) {

        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }


    @Override
    void announce() {

        System.out.println(
            "Relay | Bib: " + bibNumber +
            " | Team Size: " + teamSize +
            " | Balance: " + getBalanceDue()
        );
    }
}


public class AnnouncerTest {


    static String announceAll(
        CompetitionEntry[] entries
    ) {

        StringBuilder result =
            new StringBuilder();


        for (CompetitionEntry entry : entries) {

            // Polymorphism
            entry.announce();

            result.append(
                "Bib: " + entry.bibNumber + " | "
            );


            // Safe downcasting
            if (entry instanceof RelaySquad) {

                RelaySquad relay =
                    (RelaySquad) entry;

                result.append(
                    "Team size via downcast: "
                    + relay.teamSize
                    + " | "
                );
            }
        }


        return result.toString();
    }


    public static void main(String[] args) {

        IndividualRunner runner =
            new IndividualRunner(
                "BIB2001",
                90,
                "Open 10K"
            );


        RelaySquad relay =
            new RelaySquad(
                "BIB4001",
                300,
                4
            );


        CompetitionEntry[] entries = {
            runner,
            relay
        };


        System.out.println(
            announceAll(entries)
        );
    }
}