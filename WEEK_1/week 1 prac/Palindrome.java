import java.util.Scanner;

public class Palindrome {

    // Approach 1: Iterative
    static boolean isPalindromeIterative(String text) {

        int left = 0;
        int right = text.length() - 1;

        while (left < right) {

            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // Approach 2: Recursive
    static boolean isPalindromeRecursive(String text) {

        if (text.length() <= 1) {
            return true;
        }

        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }

        return isPalindromeRecursive(
                text.substring(1, text.length() - 1)
        );
    }

    // Approach 3: Array Reversal
    static boolean isPalindromeArrayReversal(String text) {

        char[] arr = text.toCharArray();

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        String reversed = new String(arr);

        return text.equals(reversed);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.println();

        System.out.println("Iterative: " +
                (isPalindromeIterative(text)
                        ? "Palindrome"
                        : "Not Palindrome"));

        System.out.println("Recursive: " +
                (isPalindromeRecursive(text)
                        ? "Palindrome"
                        : "Not Palindrome"));

        System.out.println("Array Reversal: " +
                (isPalindromeArrayReversal(text)
                        ? "Palindrome"
                        : "Not Palindrome"));

        sc.close();
    }
}