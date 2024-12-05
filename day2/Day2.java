
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day2 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");

        Scanner sc = new Scanner(file);
        int safeReports = 0;
        while (sc.hasNextLine()) {
            // store current report in an arraylist
            ArrayList<Integer> report = new ArrayList<>();

            for (String s : sc.nextLine().split(" ")) {
                report.add(Integer.valueOf(s));
            }

            // part 1: check if basic rules declare the report as safe
            if ((isIncreasing(report) || isDecreasing(report)) && differsByOneToThree(report)) {
                // System.out.println(report + " is safe without removing any values");
                safeReports++;
            }
            // part 2: check if the report is safe if removing any ONE value will make the
            // report safe
            else {
                for (int i = 0; i < report.size(); i++) {
                    // make copy of report and remove one value
                    ArrayList<Integer> temp = new ArrayList<>(report);
                    temp.remove(i);
                    // check if remaining report is safe
                    if ((isIncreasing(temp) || isDecreasing(temp)) && differsByOneToThree(temp)) {
                        // System.out.println(temp + " is safe by removing " + report.get(i));
                        safeReports++;
                        break;
                    }
                }
            }

        }

        System.out.println("Safe reports: " + safeReports);
        sc.close();
    };

    /*
     * A report only counts as safe if both of the following are true:
     * 
     * 1. The levels are either all increasing or all decreasing.
     * 2. Any two adjacent levels differ by at least one and at most three.
     */

    // check if any two adjacent levels differ by at least one and at most three
    public static boolean differsByOneToThree(ArrayList<Integer> report) {
        boolean safe = true;
        for (int i = 1; i < report.size(); i++) {
            // get difference between current and previous value
            int diff = Math.abs(report.get(i) - report.get(i - 1));
            if (diff < 1 || diff > 3) {
                safe = false;
                break;
            }
        }
        return safe;
    }

    // check if the report is strictly increasing
    public static boolean isIncreasing(ArrayList<Integer> report) {
        boolean isIncreasing = true;
        for (int i = 1; i < report.size(); i++) {
            // current value is smaller than previous value
            if (report.get(i) < report.get(i - 1)) {
                isIncreasing = false;
                break;
            }
        }
        return isIncreasing;
    }

    // check if the report is strictly decreasing
    public static boolean isDecreasing(ArrayList<Integer> report) {
        boolean isDecreasing = true;
        for (int i = 1; i < report.size(); i++) {
            // current value is larger than previous value
            if (report.get(i) > report.get(i - 1)) {
                isDecreasing = false;
                break;
            }
        }
        return isDecreasing;
    }

}