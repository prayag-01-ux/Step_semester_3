class EventEntry {

    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    EventEntry(String bibNumber, double entryFee) {

        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid Bib");
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

    void announce() {
        System.out.println(
            "Entry | Bib: " + bibNumber +
            " | Balance: " + getBalanceDue()
        );
    }
}


class EventRunner extends EventEntry {

    String category;

    EventRunner(
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


class ChampionRunner extends EventRunner {

    double sponsorBonus;

    ChampionRunner(
        String bibNumber,
        double entryFee,
        String category,
        double sponsorBonus
    ) {

        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    void announce() {

        System.out.println(
            "Champion Runner | Bib: " + bibNumber +
            " | Category: " + category +
            " | Sponsor Bonus: " + sponsorBonus +
            " | Balance: " + getBalanceDue()
        );
    }
}


class RelayGroup extends EventEntry {

    int teamSize;

    RelayGroup(
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
            "Relay Group | Bib: " + bibNumber +
            " | Team Size: " + teamSize +
            " | Balance: " + getBalanceDue()
        );
    }
}


public class InheritanceTest {

    static String classifyGeneration(EventEntry entry) {

        if (entry instanceof ChampionRunner) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (entry instanceof RelayGroup) {
            return "Hierarchical sibling (independent branch)";
        }

        return "Regular entry";
    }


    static double getTotalBalanceDue(EventEntry[] entries) {

        double total = 0;

        for (EventEntry entry : entries) {
            total += entry.getBalanceDue();
        }

        return total;
    }


    public static void main(String[] args) {

        EventRunner runner =
            new EventRunner(
                "BIB2001",
                80,
                "Open 10K"
            );

        ChampionRunner champion =
            new ChampionRunner(
                "BIB3001",
                150,
                "Elite Marathon",
                500
            );

        RelayGroup relay =
            new RelayGroup(
                "BIB4001",
                300,
                4
            );

        runner.announce();
        champion.announce();
        relay.announce();

        System.out.println(
            classifyGeneration(champion)
        );

        System.out.println(
            classifyGeneration(relay)
        );

        EventEntry[] entries = {
            runner,
            champion,
            relay
        };

        System.out.println(
            getTotalBalanceDue(entries)
        );
    }
}