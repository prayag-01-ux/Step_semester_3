import java.util.*;

interface CreditPolicy {
    int getCreditLimit();
}

class RegularPolicy implements CreditPolicy {

    public int getCreditLimit() {
        return 24;
    }
}

class HonorsPolicy implements CreditPolicy {

    public int getCreditLimit() {
        return 28;
    }
}

class ExchangePolicy implements CreditPolicy {

    public int getCreditLimit() {
        return 20;
    }
}

class Student {

    String name;
    int currentCredits;
    CreditPolicy policy;

    Student(String name,
            int currentCredits,
            CreditPolicy policy) {

        this.name = name;
        this.currentCredits = currentCredits;
        this.policy = policy;
    }

    boolean canAdd(int credits) {
        return currentCredits + credits <= policy.getCreditLimit();
    }

    void addCredits(int credits) {
        currentCredits += credits;
    }

    void removeCredits(int credits) {
        currentCredits -= credits;
    }
}

class Elective {

    String name;
    int credits;
    int capacity;

    List<Student> enrolled = new ArrayList<>();

    Queue<Student> waitlist = new LinkedList<>();

    Elective(String name,
            int credits,
            int capacity) {

        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    boolean isEnrolled(Student student) {
        return enrolled.contains(student);
    }

    boolean isWaitlisted(Student student) {
        return waitlist.contains(student);
    }

    void enroll(Student student) {

        if (isEnrolled(student)
                || isWaitlisted(student)) {

            System.out.println(
                    "Student already enrolled or waitlisted.");
            return;
        }

        // Credit limit checked FIRST
        if (!student.canAdd(credits)) {

            System.out.println(
                    "Enrollment failed: " +
                            student.name +
                            " would exceed the " +
                            getType(student) +
                            " credit limit (" +
                            (student.currentCredits + credits) +
                            "/" +
                            student.policy.getCreditLimit() +
                            ").");

            return;
        }

        if (enrolled.size() < capacity) {

            enrolled.add(student);
            student.addCredits(credits);

            System.out.println(
                    student.name +
                            " enrolled in " +
                            name +
                            " (credits: " +
                            student.currentCredits +
                            "/" +
                            student.policy.getCreditLimit() +
                            ").");

        } else {

            System.out.println(
                    name + " is full.");

            waitlist.add(student);

            System.out.println(
                    student.name +
                            " added to waitlist (position " +
                            waitlist.size() +
                            ").");
        }
    }

    void drop(Student student) {

        if (!enrolled.contains(student)) {
            System.out.println(
                    student.name +
                            " is not enrolled.");
            return;
        }

        enrolled.remove(student);
        student.removeCredits(credits);

        System.out.println(
                student.name +
                        " dropped " +
                        name +
                        " (credits: " +
                        student.currentCredits +
                        "/" +
                        student.policy.getCreditLimit() +
                        ").");

        promote();
    }

    void promote() {

        Iterator<Student> iterator = waitlist.iterator();

        while (iterator.hasNext()) {

            Student student = iterator.next();

            if (student.canAdd(credits)) {

                iterator.remove();

                enrolled.add(student);
                student.addCredits(credits);

                System.out.println(
                        student.name +
                                " promoted from waitlist and enrolled in " +
                                name +
                                " (credits: " +
                                student.currentCredits +
                                "/" +
                                student.policy.getCreditLimit() +
                                ").");

                return;
            }
        }
    }

    String getType(Student student) {

        if (student.policy instanceof HonorsPolicy)
            return "Honors";

        if (student.policy instanceof ExchangePolicy)
            return "Exchange";

        return "Regular";
    }
}

public class Q4_ElectiveRush {

    public static void main(String[] args) {

        Elective cloud = new Elective(
                "Cloud Computing",
                4,
                2);

        Student asha = new Student(
                "Asha",
                20,
                new RegularPolicy());

        Student ravi = new Student(
                "Ravi",
                22,
                new HonorsPolicy());

        Student neha = new Student(
                "Neha",
                12,
                new ExchangePolicy());

        Student kiran = new Student(
                "Kiran",
                22,
                new RegularPolicy());

        cloud.enroll(asha);

        cloud.enroll(ravi);

        cloud.enroll(neha);

        cloud.enroll(kiran);

        cloud.drop(asha);
    }
}