class BasePass {
    protected String attendeeId;
    protected double basePrice;
    protected double amountPaid;

    BasePass(String id, double price) {
        attendeeId = id;
        basePrice = price;
        amountPaid = 0;
    }

    void pay(double amount) {
        amountPaid += amount;
    }

    double getBalanceDue() {
        return basePrice - amountPaid;
    }

    void printPass() {
        System.out.println(
            "Standard Event Pass | Balance Due: "
            + getBalanceDue()
        );
    }
}

class TrainingPass extends BasePass {
    protected String track;

    TrainingPass(String id, double price, String track) {
        super(id, price);
        this.track = track;
    }

    @Override
    void printPass() {
        System.out.println(
            "Training Pass | Track: " + track +
            " | Balance Due: " + getBalanceDue()
        );
    }
}

class EliteTrainingPass extends TrainingPass {
    double kitFee;

    EliteTrainingPass(
        String id,
        double price,
        String track,
        double kitFee
    ) {
        super(id, price, track);
        this.kitFee = kitFee;
    }

    @Override
    void printPass() {
        System.out.println(
            "Elite Training Pass | Track: " + track +
            " | Kit Fee: " + kitFee +
            " | Balance Due: " + getBalanceDue()
        );
    }
}

class CodingPass extends BasePass {
    String teamName;

    CodingPass(String id, double price, String teamName) {
        super(id, price);
        this.teamName = teamName;
    }

    @Override
    void printPass() {
        System.out.println(
            "Coding Pass | Team: " + teamName +
            " | Balance Due: " + getBalanceDue()
        );
    }
}

public class InheritanceDemo {

    static String classifyGeneration(BasePass pass) {

        if (pass instanceof EliteTrainingPass) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (pass instanceof CodingPass) {
            return "Hierarchical sibling (independent branch)";
        }

        return "Base generation";
    }

    static double getTotalBalanceDue(BasePass[] passes) {

        double total = 0;

        for (BasePass p : passes) {
            total += p.getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {

        BasePass p1 =
            new BasePass("STU1", 500);

        TrainingPass p2 =
            new TrainingPass("STU2", 1200, "AI/ML");

        EliteTrainingPass p3 =
            new EliteTrainingPass(
                "STU3", 2000,
                "Cloud Native", 300
            );

        CodingPass p4 =
            new CodingPass(
                "STU4", 800, "Byte Force"
            );

        p1.printPass();
        p2.printPass();
        p3.printPass();
        p4.printPass();

        System.out.println(
            classifyGeneration(p3)
        );

        System.out.println(
            classifyGeneration(p4)
        );

        BasePass[] arr = {
            p1, p2, p3, p4
        };

        System.out.println(
            getTotalBalanceDue(arr)
        );
    }
}