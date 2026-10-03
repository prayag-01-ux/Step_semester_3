import java.util.*;

interface PricingPlan {
    double getPrice(double originalPrice);
}

class DayScholarPlan implements PricingPlan {

    public double getPrice(double originalPrice) {
        return originalPrice;
    }
}

class HostellerPlan implements PricingPlan {

    public double getPrice(double originalPrice) {
        return originalPrice * 0.90;
    }
}

class StaffPlan implements PricingPlan {

    public double getPrice(double originalPrice) {
        return originalPrice * 0.80;
    }
}

class Transaction {

    double amount;
    String description;

    Transaction(double amount,
            String description) {

        this.amount = amount;
        this.description = description;
    }
}

class Purchase {

    String item;
    double amount;
    boolean refunded = false;

    Purchase(String item, double amount) {
        this.item = item;
        this.amount = amount;
    }
}

class SmartCard {

    private String cardNumber;
    private PricingPlan plan;

    private double balance = 0;

    private List<Transaction> transactions = new ArrayList<>();

    private List<Purchase> purchases = new ArrayList<>();

    private boolean blocked = false;

    SmartCard(String cardNumber,
            PricingPlan plan) {

        this.cardNumber = cardNumber;
        this.plan = plan;
    }

    void topUp(double amount) {

        if (blocked) {
            System.out.println(
                    "Top-up rejected: Card is blocked.");
            return;
        }

        if (amount < 100) {
            System.out.println(
                    "Top-up failed: Minimum top-up is ₹100.");
            return;
        }

        if (balance + amount > 5000) {
            System.out.println(
                    "Top-up failed: Maximum balance is ₹5000.");
            return;
        }

        balance += amount;

        transactions.add(
                new Transaction(
                        amount,
                        "Top-up"));

        System.out.printf(
                "%s topped up with ₹%.2f. Balance: ₹%.2f%n",
                cardNumber,
                amount,
                balance);
    }

    Purchase purchase(String item,
            double originalPrice) {

        if (blocked) {
            System.out.println(
                    "Purchase failed: Card is blocked.");
            return null;
        }

        double chargedPrice = plan.getPrice(originalPrice);

        if (balance < chargedPrice) {

            System.out.printf(
                    "Purchase failed: Insufficient balance " +
                            "(required ₹%.2f, available ₹%.2f).%n",
                    chargedPrice,
                    balance);

            return null;
        }

        balance -= chargedPrice;

        Purchase purchase = new Purchase(
                item,
                chargedPrice);

        purchases.add(purchase);

        transactions.add(
                new Transaction(
                        -chargedPrice,
                        item));

        System.out.printf(
                "%s purchased for ₹%.2f. Balance: ₹%.2f%n",
                item,
                chargedPrice,
                balance);

        return purchase;
    }

    void refund(Purchase purchase) {

        if (purchase == null) {
            return;
        }

        if (purchase.refunded) {

            System.out.println(
                    "Refund rejected: " +
                            purchase.item +
                            " has already been refunded.");

            return;
        }

        balance += purchase.amount;

        purchase.refunded = true;

        transactions.add(
                new Transaction(
                        purchase.amount,
                        "Refund - " +
                                purchase.item));

        System.out.printf(
                "Refund of ₹%.2f for %s processed. Balance: ₹%.2f%n",
                purchase.amount,
                purchase.item,
                balance);
    }

    void block() {
        blocked = true;
        System.out.println(
                cardNumber + " blocked.");
    }

    void unblock() {
        blocked = false;
        System.out.println(
                cardNumber + " unblocked.");
    }

    void miniStatement() {

        System.out.println(
                "Mini-statement for " +
                        cardNumber + ":");

        for (Transaction transaction : transactions) {

            System.out.printf(
                    "%+.2f",
                    transaction.amount);

            if (transaction != transactions.get(
                    transactions.size() - 1)) {

                System.out.print(", ");
            }
        }

        System.out.printf(
                " = ₹%.2f%n",
                balance);
    }
}

public class Q5_SmartCard {

    public static void main(String[] args) {

        SmartCard card = new SmartCard(
                "C-2045",
                new HostellerPlan());

        card.topUp(500);

        Purchase vegThali = card.purchase(
                "Veg Thali",
                120);

        card.purchase(
                "Cold Coffee",
                60);

        card.purchase(
                "Food Items",
                400);

        card.refund(vegThali);

        card.refund(vegThali);

        card.miniStatement();
    }
}