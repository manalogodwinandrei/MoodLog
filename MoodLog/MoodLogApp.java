import java.util.Scanner;
import Display.*;
import Controller.MoodTracker;

public class MoodLogApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IconDisplay icon = new IconDisplay();
        BannerDisplay banner = new BannerDisplay();
        MoodTracker tracker = new MoodTracker();

        try {
            icon.show();
            System.out.print("Press Enter to continue...");
            sc.nextLine();
            int choice = 0;
            
            while (choice != 5) {
                banner.show();
                System.out.println("\n\t\t\t\t\t\t1. Log Today's Mood");
                System.out.println("\t\t\t\t\t\t2. View Mood History");
                System.out.println("\t\t\t\t\t\t3. View Statistics");
                System.out.println("\t\t\t\t\t\t4. Delete Log");
                System.out.println("\t\t\t\t\t\t5. Exit");
                System.out.print("\n\t\t\t\t\t\t\033[38;5;45mEnter choice:\033[0m ");
                
                try {
                    String input = sc.nextLine().trim();
                    
                    if (input.isEmpty()) {
                        System.out.println("\n\t\t\t\t\t\tError: Input cannot be empty.");
                        System.out.print("\n\t\t\t\t\t\tPress Enter to continue...");
                        sc.nextLine();
                        continue;
                    }

                    choice = Integer.parseInt(input);
                    if (choice < 1 || choice > 5) {
                        System.out.println("\n\t\t\t\t\t\tError: Please enter a number between 1 and 5.");
                        System.out.print("\n\t\t\t\t\t\tPress Enter to continue...");
                        sc.nextLine();
                        choice = 0;
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\n\t\t\t\t\t\tError: Please enter a valid number.");
                    System.out.print("\n\t\t\t\t\t\tPress Enter to continue...");
                    sc.nextLine();
                    choice = 0;
                    continue;
                }

                if (choice == 1) {
                    tracker.logMood();
                } else if (choice == 2) {
                    tracker.viewHistory();
                } else if (choice == 3) {
                    tracker.viewStats();
                } else if (choice == 4) {
                    tracker.deleteLog();
                } else if (choice == 5) {
                    System.out.println("\n\t\t\t\t\tThank you for using MoodLog. Take care!");
                    System.out.println("\t\t\t\t\tRemember: Your mental health matters.\n");
                }
            }
        } catch (Exception e) {
            System.err.println("\nA critical error occurred: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}