import java.util.*;

interface ShippingType {
    double calculateCharge(double weight);
}

class StandardShipping implements ShippingType {

    public double calculateCharge(double weight) {
        return 40 + 10 * weight;
    }
}

class ExpressShipping implements ShippingType {

    public double calculateCharge(double weight) {
        return 80 + 15 * weight;
    }
}

class FragileShipping implements ShippingType {

    public double calculateCharge(double weight) {
        return 40 + 10 * weight + 50;
    }
}

interface NotificationChannel {
    void notify(String parcelId, String status);
}

class SmsChannel implements NotificationChannel {

    public void notify(String parcelId, String status) {
        System.out.println(
                "[SMS] " + parcelId + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {

    public void notify(String parcelId, String status) {
        System.out.println(
                "[Email] " + parcelId + " is now " + status + ".");
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Parcel {

    String id;
    double weight;
    ShippingType shippingType;
    String status = "BOOKED";

    List<NotificationChannel> channels = new ArrayList<>();

    Parcel(String id, double weight,
            ShippingType shippingType) {

        this.id = id;
        this.weight = weight;
        this.shippingType = shippingType;
    }

    double getCharge() {
        return shippingType.calculateCharge(weight);
    }

    void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    void notifyChannels() {
        for (NotificationChannel channel : channels) {
            channel.notify(id, status);
        }
    }

    void changeStatus(String newStatus) {

        boolean valid = false;

        if (status.equals("BOOKED")
                && newStatus.equals("PICKED_UP")) {
            valid = true;
        }

        else if (status.equals("PICKED_UP")
                && newStatus.equals("IN_TRANSIT")) {
            valid = true;
        }

        else if (status.equals("IN_TRANSIT")
                && newStatus.equals("OUT_FOR_DELIVERY")) {
            valid = true;
        }

        else if (status.equals("OUT_FOR_DELIVERY")
                && newStatus.equals("DELIVERED")) {
            valid = true;
        }

        if (!valid) {
            System.out.println(
                    "Invalid transition: " +
                            status + " → " + newStatus +
                            " is not allowed.");
            return;
        }

        status = newStatus;
        notifyChannels();
    }

    void cancel() {

        if (!status.equals("BOOKED")) {
            System.out.println(
                    "Cancellation failed: " + id +
                            " can be cancelled only while BOOKED.");
            return;
        }

        status = "CANCELLED";

        System.out.println(
                "Parcel " + id + " cancelled.");
    }
}

public class Q2_SwiftShip {

    public static void main(String[] args) {

        Customer customer = new Customer("Prayag");

        Parcel parcel = new Parcel(
                "P101",
                2,
                new ExpressShipping());

        parcel.subscribe(new SmsChannel());
        parcel.subscribe(new EmailChannel());

        System.out.println(
                "Parcel " + parcel.id +
                        " booked (Express, 2 kg).");

        System.out.printf(
                "Charge: ₹%.2f%n",
                parcel.getCharge());

        parcel.notifyChannels();

        parcel.changeStatus("PICKED_UP");

        parcel.cancel();

        parcel.changeStatus("IN_TRANSIT");

        parcel.changeStatus("DELIVERED");
    }
}