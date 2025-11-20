package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import Model.MoodEntry;
import TestTypes.*;

public class MoodTracker {
    private List<MoodEntry> logs;
    private Scanner sc;
    private static final String DATA_FILE = "mood_logs.txt";
    private DateTimeFormatter formatter;

    public MoodTracker() {
        logs = new ArrayList<>();
        sc = new Scanner(System.in);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        loadFromFile();
    }

    private void setColor(int c) { 
        System.out.print("\033[38;5;" + c + "m"); 
    }
    
    private void resetColor() { 
        System.out.print("\033[0m"); 
    }

    private void loadFromFile() {
        try {
            File file = new File(DATA_FILE);
            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                MoodEntry entry = MoodEntry.fromFileString(line);
                if (entry != null) {
                    logs.add(entry);
                }
            }
            reader.close();
            Collections.sort(logs);
        } catch (IOException e) {
            System.err.println("Warning: Could not load previous mood logs.");
        }
    }

    private void saveToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE));
            for (int i = 0; i < logs.size(); i++) {
                writer.write(logs.get(i).toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error: Could not save mood logs.");
        }
    }

    private PsychologyTest getTestByTrigger(String trigger) {
        if (trigger.equalsIgnoreCase("work")) {
            return new WorkTest();
        } else if (trigger.equalsIgnoreCase("academics")) {
            return new AcademicsTest();
        } else if (trigger.equalsIgnoreCase("peer relationship")) {
            return new PeerTest();
        } else if (trigger.equalsIgnoreCase("family")) {
            return new FamilyTest();
        } else {
            return null;
        }
    }

    private String getValidatedMood() {
        while (true) {
            setColor(45);
            System.out.print("\nEnter your current mood (Happy, Sad, Angry, Anxious, Calm, etc.): ");
            resetColor();
            String mood = sc.nextLine().trim();

            if (mood.isEmpty()) {
                System.out.println("Error: Mood cannot be empty.");
                continue;
            }

            if (!mood.matches("[a-zA-Z\\s]+")) {
                System.out.println("Error: Mood should only contain letters.");
                continue;
            }

            if (mood.length() > 50) {
                System.out.println("Error: Mood is too long.");
                continue;
            }

            return mood.substring(0, 1).toUpperCase() + mood.substring(1).toLowerCase();
        }
    }

    private String getValidatedTrigger() {
        while (true) {
            setColor(45);
            System.out.print("\nSelect mood trigger:\n");
            System.out.println("  1. Work");
            System.out.println("  2. Academics");
            System.out.println("  3. Peer Relationship");
            System.out.println("  4. Family");
            System.out.print("Enter choice (1-4): ");
            resetColor();
            
            String input = sc.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                if (choice == 1) {
                    return "work";
                } else if (choice == 2) {
                    return "academics";
                } else if (choice == 3) {
                    return "peer relationship";
                } else if (choice == 4) {
                    return "family";
                } else {
                    System.out.println("Error: Please enter 1, 2, 3, or 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }

    private String getValidatedYesNo(String prompt) {
        while (true) {
            setColor(45);
            System.out.print(prompt + " (yes/no): ");
            resetColor();
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("yes") || input.equals("y")) {
                return "yes";
            } else if (input.equals("no") || input.equals("n")) {
                return "no";
            }
            System.out.println("Error: Please enter 'yes' or 'no'.");
        }
    }

    public void logMood() {
        try {
            String dateTime = LocalDateTime.now().format(formatter);
            String mood = getValidatedMood();
            String trigger = getValidatedTrigger();

            PsychologyTest test = getTestByTrigger(trigger);
            if (test == null) {
                System.out.println("Error: Invalid trigger.");
                promptEnterKey();
                return;
            }

            StringBuilder record = new StringBuilder();
            int score = test.conductTest(sc, record);
            
            String stressLevel;
            if (score > 30) {
                stressLevel = "High stress";
            } else {
                stressLevel = "Low stress";
            }

            String journal = "";
            if (getValidatedYesNo("\nDo you want to add a journal entry?").equals("yes")) {
                setColor(45);
                System.out.print("Enter your journal entry: ");
                resetColor();
                journal = sc.nextLine().trim();
            }

            MoodEntry entry = new MoodEntry(dateTime, mood, trigger, record.toString(), journal, stressLevel);
            logs.add(entry);
            Collections.sort(logs);
            saveToFile();

            System.out.println("\nMood log successfully saved!");
            promptEnterKey();

        } catch (Exception e) {
            System.err.println("Error while logging mood.");
            promptEnterKey();
        }
    }

    public void viewHistory() {
        try {
            if (logs.isEmpty()) {
                System.out.println("\nNo mood logs found.\n");
                promptEnterKey();
                return;
            }

            setColor(45);
            System.out.print("\nEnter the date (YYYY-MM-DD) or 'all' to view all logs: ");
            resetColor();
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("all")) {
                viewAllLogs();
                promptEnterKey();
                return;
            }

            if (!input.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Error: Invalid date format. Use YYYY-MM-DD.");
                promptEnterKey();
                return;
            }

            boolean found = false;
            setColor(51);
            System.out.println("\n================================================================================");
            System.out.println("MOOD LOGS FOR " + input);
            System.out.println("================================================================================");
            resetColor();

            for (int i = 0; i < logs.size(); i++) {
                MoodEntry e = logs.get(i);
                if (e.getDateTime().startsWith(input)) {
                    setColor(51);
                    System.out.println("\n[Entry #" + (i + 1) + "]");
                    resetColor();
                    displayEntry(e);
                    setColor(51);
                    System.out.println("--------------------------------------------------------------------------------");
                    resetColor();
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No entries found for " + input);
            }

            promptEnterKey();

        } catch (Exception e) {
            System.err.println("Error while viewing history.");
            promptEnterKey();
        }
    }

    private void viewAllLogs() {
        setColor(51);
        System.out.println("\n================================================================================");
        System.out.println("ALL MOOD LOGS (Sorted by Date - Newest First)");
        System.out.println("================================================================================");
        resetColor();

        for (int i = 0; i < logs.size(); i++) {
            MoodEntry e = logs.get(i);
            setColor(51);
            System.out.println("\n[Entry #" + (i + 1) + "]");
            resetColor();
            displayEntry(e);
            setColor(51);
            System.out.println("--------------------------------------------------------------------------------");
            resetColor();
        }
    }

    private void displayEntry(MoodEntry e) {
        setColor(51);
        System.out.println("Date & Time: " + e.getDateTime());
        System.out.println("Mood: " + e.getMood());
        System.out.println("Trigger: " + capitalize(e.getTrigger()));
        System.out.println("Stress Level: " + e.getStressLevel());
        
        if (e.getJournal().isEmpty()) {
            System.out.println("Journal: No journal entry.");
        } else {
            System.out.println("Journal: " + e.getJournal());
        }
        resetColor();
    }

    public void viewStats() {
        try {
            if (logs.isEmpty()) {
                System.out.println("\nNo mood logs found.\n");
                promptEnterKey();
                return;
            }

            Map<String, Integer> moodCount = new HashMap<>();
            Map<String, Integer> triggerCount = new HashMap<>();
            int highStress = 0;
            int lowStress = 0;

            for (int i = 0; i < logs.size(); i++) {
                MoodEntry e = logs.get(i);
                String mood = e.getMood().toLowerCase();
                String trigger = e.getTrigger().toLowerCase();
                
                if (moodCount.containsKey(mood)) {
                    moodCount.put(mood, moodCount.get(mood) + 1);
                } else {
                    moodCount.put(mood, 1);
                }
                
                if (triggerCount.containsKey(trigger)) {
                    triggerCount.put(trigger, triggerCount.get(trigger) + 1);
                } else {
                    triggerCount.put(trigger, 1);
                }
                
                if (e.getStressLevel().equals("High stress")) {
                    highStress++;
                } else {
                    lowStress++;
                }
            }

            String mostMood = "";
            int maxMoodCount = 0;
            for (String mood : moodCount.keySet()) {
                if (moodCount.get(mood) > maxMoodCount) {
                    maxMoodCount = moodCount.get(mood);
                    mostMood = mood;
                }
            }

            String mostTrigger = "";
            int maxTriggerCount = 0;
            for (String trigger : triggerCount.keySet()) {
                if (triggerCount.get(trigger) > maxTriggerCount) {
                    maxTriggerCount = triggerCount.get(trigger);
                    mostTrigger = trigger;
                }
            }

            setColor(51);
            System.out.println("\n==================================================");
            System.out.println("MOOD STATISTICS");
            System.out.println("==================================================");
            System.out.println("Total entries: " + logs.size());
            System.out.println("Most frequent mood: " + capitalize(mostMood) + " (" + maxMoodCount + " times)");
            System.out.println("Most frequent trigger: " + capitalize(mostTrigger) + " (" + maxTriggerCount + " times)");
            System.out.println("\nStress Level Summary:");
            System.out.println("  High stress days: " + highStress);
            System.out.println("  Low stress days: " + lowStress);
            System.out.println("\nMood Breakdown:");
            for (String mood : moodCount.keySet()) {
                System.out.println("  " + capitalize(mood) + ": " + moodCount.get(mood));
            }
            System.out.println("\nTrigger Breakdown:");
            for (String trigger : triggerCount.keySet()) {
                System.out.println("  " + capitalize(trigger) + ": " + triggerCount.get(trigger));
            }
            System.out.println("==================================================");
            resetColor();
            promptEnterKey();

        } catch (Exception e) {
            System.err.println("Error while calculating statistics.");
            promptEnterKey();
        }
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public void deleteLog() {
        try {
            if (logs.isEmpty()) {
                System.out.println("\nNo mood logs to delete.\n");
                promptEnterKey();
                return;
            }

            // Display all logs with numbers
            setColor(51);
            System.out.println("\n================================================================================");
            System.out.println("SELECT ENTRY TO DELETE");
            System.out.println("================================================================================");
            resetColor();

            for (int i = 0; i < logs.size(); i++) {
                MoodEntry e = logs.get(i);
                setColor(51);
                System.out.println("\n[" + (i + 1) + "] " + e.getDateTime() + " - " + e.getMood() + " (" + capitalize(e.getTrigger()) + ")");
                resetColor();
            }

            setColor(51);
            System.out.println("\n================================================================================");
            resetColor();

            setColor(45);
            System.out.print("\nEnter the number of the entry to delete (or 0 to cancel): ");
            resetColor();
            
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty.");
                promptEnterKey();
                return;
            }

            try {
                int choice = Integer.parseInt(input);

                if (choice == 0) {
                    System.out.println("Delete operation cancelled.");
                    promptEnterKey();
                    return;
                }

                if (choice < 1 || choice > logs.size()) {
                    System.out.println("Error: Invalid entry number. Please enter a number between 1 and " + logs.size());
                    promptEnterKey();
                    return;
                }

                // Show the entry to be deleted
                MoodEntry toDelete = logs.get(choice - 1);
                setColor(51);
                System.out.println("\nYou are about to delete:");
                System.out.println("Date & Time: " + toDelete.getDateTime());
                System.out.println("Mood: " + toDelete.getMood());
                System.out.println("Trigger: " + capitalize(toDelete.getTrigger()));
                resetColor();

                if (getValidatedYesNo("\nAre you sure you want to delete this entry?").equals("yes")) {
                    logs.remove(choice - 1);
                    saveToFile();
                    System.out.println("\nLog deleted successfully.");
                } else {
                    System.out.println("\nDelete operation cancelled.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }

            promptEnterKey();

        } catch (Exception e) {
            System.err.println("Error while deleting log.");
            promptEnterKey();
        }
    }

    private void promptEnterKey() {
        try {
            System.out.print("\nPress Enter key to continue...");
            sc.nextLine();
        } catch (Exception e) {
            // Ignore
        }
    }
}


