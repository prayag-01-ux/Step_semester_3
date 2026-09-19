class Pass {

    protected double price;

    Pass(double price) {
        this.price = price;
    }

    void printPass() {
        System.out.print(
            "Standard | Balance: "
            + price
        );
    }

    double getBalanceDue() {
        return price;
    }
}

class WorkshopPass extends Pass {

    String track;

    WorkshopPass(double price, String track) {
        super(price);
        this.track = track;
    }

    @Override
    void printPass() {
        System.out.print(
            "Workshop | Track: " +
            track +
            " | Balance: " +
            getBalanceDue()
        );
    }
}

public class PolymorphismDemo {

    static String batchPrint(Pass[] passes) {

        StringBuilder result =
            new StringBuilder();

        for (Pass p : passes) {

            // Polymorphism
            p.printPass();

            // Safe downcasting
            if (p instanceof WorkshopPass) {

                WorkshopPass wp =
                    (WorkshopPass) p;

                result.append(
                    "[Track via downcast: "
                    + wp.track + "] | "
                );
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        Pass p1 =
            new Pass(500);

        WorkshopPass p2 =
            new WorkshopPass(
                1200, "AI/ML"
            );

        Pass[] arr = {p1, p2};

        System.out.println(
            batchPrint(arr)
        );
    }
}