class BrokenStudent {

    // WRONG:
    // These fields are shared by every object.
    // Therefore, the second student's values overwrite the first student's values.
    static String name;
    static String regNo;
    static int attendance;

    BrokenStudent(String name, String regNo, int attendance) {
        BrokenStudent.name = name;
        BrokenStudent.regNo = regNo;
        BrokenStudent.attendance = attendance;
    }
}

class Srm{

    // Instance fields: each student gets separate values.
    String name;
    String regNo;
    int attendance;

    // Static fields: shared by the entire university/class.
    static String university = "SRMIST";
    static int admissionCount = 0;

    Srm(String name, int attendance) {

        this.name = name;
        this.attendance = attendance;

        admissionCount++;

        this.regNo = "RA2311003010" +
                     String.format("%02d", admissionCount);
    }

    void printIdCard() {

        System.out.println(
            name + " | " + regNo
        );
    }

    static void printTotalAdmissions() {

        System.out.println(
            "Students admitted so far: "
            + admissionCount
        );
    }
}

public class Main2 {

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenStudent s1 =
            new BrokenStudent("Ravi", "RA001", 82);

        BrokenStudent s2 =
            new BrokenStudent("Meera", "RA002", 90);

        System.out.println(BrokenStudent.name);
        System.out.println(BrokenStudent.name);

        System.out.println();
        System.out.println("Fixed version:");

        Srm student1 =
            new Srm("Ravi", 82);

        Srm student2 =
            new Srm("Meera", 90);

        student1.printIdCard();
        student2.printIdCard();

        Srm.printTotalAdmissions();
    }
}