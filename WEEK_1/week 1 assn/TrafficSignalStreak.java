import java.util.Scanner;

public class TrafficSignalStreak {

    static void findLongestStreak(String signalLog) {

        if (signalLog.length() == 0) {
            System.out.println("Signal log is empty");
            return;
        }

        char longestColor = signalLog.charAt(0);

        int longest = 1;
        int current = 1;

        for (int i = 1; i < signalLog.length(); i++) {

            if (signalLog.charAt(i) ==
                    signalLog.charAt(i - 1)) {

                current++;

            } else {

                current = 1;
            }

            if (current > longest) {

                longest = current;
                longestColor = signalLog.charAt(i);
            }
        }

        System.out.println(
                "Longest Streak: '" +
                longestColor +
                "' repeated " +
                longest +
                " times"
        );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter signal log: ");
        String signalLog = sc.nextLine();

        findLongestStreak(signalLog);

        sc.close();
    }
}