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

class ScholarshipFeeAccount extends FeeAccount {

    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee,
                          double scholarshipPercent) {
        super(regNo, totalFee);
        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        return getDue() -
               (getDue() * scholarshipPercent / 100);
    }
}

public class Main1 {

    public static void main(String[] args) {

        FeeAccount plain =
            new FeeAccount("RA001", 150000);

        HostelFeeAccount hostel =
            new HostelFeeAccount("RA002", 200000);

        ScholarshipFeeAccount scholarship =
            new ScholarshipFeeAccount("RA003", 180000, 20);

        plain.pay(150000);

        hostel.pay(60000);

        Object[] accounts = {
            plain,
            hostel,
            scholarship
        };

        for (Object obj : accounts) {

            if (obj instanceof ScholarshipFeeAccount) {

                ScholarshipFeeAccount s =
                    (ScholarshipFeeAccount) obj;

                System.out.println(
                    "Scholarship account effective due: Rs "
                    + s.effectiveDue()
                );

            } else if (obj instanceof HostelFeeAccount) {

                HostelFeeAccount h =
                    (HostelFeeAccount) obj;

                System.out.println(
                    "Hostel account due: Rs "
                    + h.getDue()
                );

            } else if (obj instanceof FeeAccount) {

                FeeAccount f =
                    (FeeAccount) obj;

                System.out.println(
                    "Plain account due: Rs "
                    + f.getDue()
                );
            }
        }
    }
}