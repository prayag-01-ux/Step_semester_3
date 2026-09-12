class Employee {

    private int empId;
    private String empName;
    private double salary;

    Employee(
        int empId,
        String empName,
        double salary
    ) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {

    private double teamBonus;

    ManagerEmployee(
        int empId,
        String empName,
        double salary,
        double teamBonus
    ) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {

    private double stipendCap;

    InternEmployee(
        int empId,
        String empName,
        double salary,
        double stipendCap
    ) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {

        return Math.min(
            getSalary(),
            stipendCap
        );
    }
}

class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(
        String slotNo,
        int capacity,
        int occupiedCount
    ) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    boolean allot(String vehicleNo) {

        if (occupiedCount < capacity) {

            occupiedCount++;

            return true;
        }

        return false;
    }

    static ParkingSlot findAvailableSlot(
        ParkingSlot[] slots
    ) {

        for (ParkingSlot slot : slots) {

            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }

        return null;
    }
}

class CompanyEmployeeRecord {

    String name;
    String empId;

    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(
        String name,
        String empId,
        Employee employee
    ) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;

        totalRecords++;
    }

    String fullProfile() {

        double pay;

        if (employee instanceof ManagerEmployee) {

            ManagerEmployee manager =
                (ManagerEmployee) employee;

            pay = manager.effectiveSalary();

        } else if (employee instanceof InternEmployee) {

            InternEmployee intern =
                (InternEmployee) employee;

            pay = intern.effectiveSalary();

        } else {

            pay = employee.getSalary();
        }

        String slotInfo;

        if (slot != null) {
            slotInfo = slot.slotNo;
        } else {
            slotInfo = "no parking assigned";
        }

        return name +
               " | Pay: Rs " +
               pay +
               " | Slot: " +
               slotInfo;
    }
}

public class Main3 {

    public static void main(String[] args) {

        ParkingSlot slot1 =
            new ParkingSlot("A1", 1, 0);

        ParkingSlot slot2 =
            new ParkingSlot("A2", 1, 0);

        ManagerEmployee manager =
            new ManagerEmployee(
                101,
                "Divya",
                70000,
                8000
            );

        Employee normal =
            new Employee(
                102,
                "Karan",
                40000
            );

        InternEmployee intern =
            new InternEmployee(
                103,
                "Meera",
                12000,
                10000
            );

        CompanyEmployeeRecord record1 =
            new CompanyEmployeeRecord(
                "Divya",
                "E101",
                manager
            );

        CompanyEmployeeRecord record2 =
            new CompanyEmployeeRecord(
                "Karan",
                "E102",
                normal
            );

        CompanyEmployeeRecord record3 =
            new CompanyEmployeeRecord(
                "Meera",
                "E103",
                intern
            );

        // Allot parking only to two employees.
        if (slot1.allot("DIVYA123")) {
            record1.slot = slot1;
        }

        if (slot2.allot("KARAN123")) {
            record2.slot = slot2;
        }

        // Meera intentionally has no parking slot.

        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());

        System.out.println(
            "Total records: " +
            CompanyEmployeeRecord.totalRecords
        );
    }
}