class BasePass {
    protected String attendeeId;
    protected double basePrice;
    protected double amountPaid;

    BasePass(String attendeeId, double basePrice) {
        if (attendeeId == null ||
            attendeeId.trim().isEmpty() ||
            attendeeId.length() < 4) {
            throw new IllegalArgumentException("Invalid attendee ID");
        }

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.amountPaid = 0;
    }

    void pay(double amount) {
        amountPaid += amount;
    }

    double getBalanceDue() {
        return basePrice - amountPaid;
    }

    static String registerBatch(String[] ids, double price) {
        int registered = 0;
        int rejected = 0;

        for (String id : ids) {
            try {
                new BasePass(id, price);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered +
               " | Rejected: " + rejected;
    }
}

class TrainingPass extends BasePass {
    String track;

    TrainingPass(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }
}

public class PassDemo {
    public static void main(String[] args) {

        TrainingPass p =
            new TrainingPass("STU2", 1200, "AI/ML");

        p.pay(500);

        System.out.println(p.getBalanceDue());

        String[] ids = {
            "STU1", "ST1", "STU2", " ", "STU3"
        };

        System.out.println(
            BasePass.registerBatch(ids, 500)
        );
    }
}