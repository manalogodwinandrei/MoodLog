package TestTypes;

import java.util.Arrays;
import java.util.Scanner;

public abstract class BaseTest implements PsychologyTest {
    protected void setColor(int color) { System.out.print("\033[38;5;" + color + "m"); }
    protected void resetColor() { System.out.print("\033[0m"); }

    protected int askQuestions(Scanner sc, String[] questions, StringBuilder record) {
        int[] ans = new int[questions.length];
        int score = 0;
        System.out.println("\n" + "=".repeat(80));
        System.out.println("PSYCHOLOGY ASSESSMENT");
        System.out.println("Rate each statement from 1 to 5:");
        System.out.println("  1 = Strongly Disagree");
        System.out.println("  2 = Disagree");
        System.out.println("  3 = Neutral");
        System.out.println("  4 = Agree");
        System.out.println("  5 = Strongly Agree");
        System.out.println("=".repeat(80) + "\n");

        for (int i = 0; i < questions.length; i++) {
            boolean validInput = false;
            int attempts = 0;
            int maxAttempts = 5;

            while (!validInput && attempts < maxAttempts) {
                try {
                    System.out.println((i + 1) + ". " + questions[i]);
                    setColor(45);
                    System.out.print("Your answer (1-5): ");
                    resetColor();
                    
                    String input = sc.nextLine().trim();
                    if (input.isEmpty()) {
                        System.out.println("Error: Input cannot be empty. Please enter a number from 1 to 5.\n");
                        attempts++;
                        continue;
                    }

                    int r = Integer.parseInt(input);
                    if (r < 1 || r > 5) {
                        System.out.println("Error: Number out of range. Please enter a number from 1 to 5.\n");
                        attempts++;
                        continue;
                    }

                    ans[i] = r;
                    score += r;
                    validInput = true;
                    System.out.println();
                } catch (NumberFormatException ex) {
                    System.out.println("Error: Invalid input. Please enter a valid number between 1 and 5.\n");
                    attempts++;
                } catch (Exception ex) {
                    System.out.println("Error: An unexpected error occurred. Please try again.\n");
                    attempts++;
                }
            }

            if (!validInput) {
                System.out.println("Maximum attempts reached. Defaulting to neutral (3) for this question.");
                ans[i] = 3;
                score += 3;
            }
        }

        record.append(Arrays.toString(ans));
        System.out.println("=".repeat(80));
        System.out.println("Assessment completed.");
        System.out.println("=".repeat(80) + "\n");
        return score;
    }
}