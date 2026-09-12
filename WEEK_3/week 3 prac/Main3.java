class FeeAccount {

    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }

    void pay(double amount) {

        if (amount > 0) {
            amountPaid += amount;
        } else {
            System.out.println("Invalid payment amount");
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    void payInTwoInstallments(double amount) {
        pay(amount);
        pay(amount);
    }
}

class Hostel {

    String roomNo;
    int beds;
    int occupied;

    Hostel(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    boolean allot(String name) {

        if (occupied < beds) {
            occupied++;
            return true;
        }

        return false;
    }
}

class SrmStudent {

    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    Hostel room;

    static int totalStudents = 0;

    SrmStudent(
        String name,
        String regNo,
        HostelFeeAccount feeAccount
    ) {

        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;

        totalStudents++;
    }

    String fullStatus() {

        String roomNumber;

        if (room != null) {
            roomNumber = room.roomNo;
        } else {
            roomNumber = "unallotted";
        }

        return name +
               " | Due: Rs " +
               feeAccount.getDue() +
               " | Room: " +
               roomNumber;
    }
}

public class Main3 {

    public static void main(String[] args) {

        Hostel room1 =
            new Hostel("C-214", 1, 0);

        Hostel room2 =
            new Hostel("C-507", 1, 0);

        SrmStudent ravi =
            new SrmStudent(
                "Ravi",
                "RA001",
                new HostelFeeAccount("RA001", 200000)
            );

        SrmStudent anitha =
            new SrmStudent(
                "Anitha",
                "RA002",
                new HostelFeeAccount("RA002", 200000)
            );

        SrmStudent karthik =
            new SrmStudent(
                "Karthik",
                "RA003",
                new HostelFeeAccount("RA003", 200000)
            );

        // Valid payment
        ravi.feeAccount.pay(60000);

        // Valid payment
        anitha.feeAccount.pay(20000);

        // Rejected payment
        karthik.feeAccount.pay(-5000);

        // Allot rooms only to two students
        if (room1.allot(ravi.name)) {
            ravi.room = room1;
        }

        if (room2.allot(anitha.name)) {
            anitha.room = room2;
        }

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println(
            "Total students: " +
            SrmStudent.totalStudents
        );
    }
}