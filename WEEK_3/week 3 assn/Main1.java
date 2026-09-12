class Employee {

    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
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

        if (getSalary() < stipendCap) {
            return getSalary();
        }

        return stipendCap;
    }
}

public class Main1 {

    public static void main(String[] args) {

        Employee plain =
            new Employee(
                101,
                "Ravi",
                40000
            );

        ManagerEmployee manager =
            new ManagerEmployee(
                102,
                "Divya",
                70000,
                8000
            );

        InternEmployee intern =
            new InternEmployee(
                103,
                "Meera",
                12000,
                10000
            );

        Employee[] employees = {
            plain,
            manager,
            intern
        };

        for (Employee employee : employees) {

            if (employee instanceof ManagerEmployee) {

                ManagerEmployee m =
                    (ManagerEmployee) employee;

                System.out.println(
                    "Manager effective pay: Rs " +
                    m.effectiveSalary()
                );

            } else if (employee instanceof InternEmployee) {

                InternEmployee i =
                    (InternEmployee) employee;

                System.out.println(
                    "Intern effective pay: Rs " +
                    i.effectiveSalary()
                );

            } else {

                System.out.println(
                    "Plain employee pay: Rs " +
                    employee.getSalary()
                );
            }
        }
    }
}