package util;

import java.time.LocalDate;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputRequiredInt(String title) {
        System.out.print(title + ": ");
        return scanner.nextInt();
    }

    public static String inputRequiredString(String title) {
        System.out.print(title + ": ");
        return scanner.nextLine().trim();
    }

    public static double inputRequiredDouble(String title) {
        System.out.print(title + ": ");
        return scanner.nextDouble();
    }

    public static long inputRequiredLong(String title) {
        System.out.print(title + ": ");
        return scanner.nextLong();
    }

    public static LocalDate inputRequiredDate(String title) {
        System.out.print(title + " (YYYY-MM-DD): ");
        String input = scanner.nextLine().trim();
        return LocalDate.parse(input);
    }

    public static void closeScanner() {
        scanner.close();
    }
}
