class FoodOrderRecord {

    String studentName;
    String dishName;
    boolean delivered;

    FoodOrderRecord(String studentName, String dishName) {

        if (studentName == null ||
            studentName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Invalid student name"
            );
        }

        if (dishName == null ||
            dishName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Invalid dish name"
            );
        }

        this.studentName = studentName;
        this.dishName = dishName;
        this.delivered = false;
    }

    void markDelivered() {

        if (delivered) {
            System.out.println(
                "Order was already delivered"
            );
        } else {
            delivered = true;
            System.out.println(
                "Order delivered successfully"
            );
        }
    }
}

public class FoodOrderValidator {

    static void processBatch(String[][] rawOrders) {

        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {

            try {

                if (order == null || order.length < 2)
                    throw new IllegalArgumentException();

                new FoodOrderRecord(
                    order[0],
                    order[1]
                );

                valid++;

            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println(
            "Valid: " + valid +
            " | Rejected: " + rejected
        );
    }

    public static void main(String[] args) {

        String[][] orders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        processBatch(orders);
    }
}