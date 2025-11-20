package Model;

public class MoodEntry implements Comparable<MoodEntry> {
    private String dateTime;
    private String mood;
    private String trigger;
    private String psychologyTest;
    private String journal;
    private String stressLevel;

    public MoodEntry(String dateTime, String mood, String trigger, String test, String journal, String stressLevel) {
        this.dateTime = dateTime;
        this.mood = mood;
        this.trigger = trigger;
        this.psychologyTest = test;
        this.journal = journal;
        this.stressLevel = stressLevel;
    }

    public String getDateTime() { return dateTime; }
    public String getMood() { return mood; }
    public String getTrigger() { return trigger; }
    public String getPsychologyTest() { return psychologyTest; }
    public String getJournal() { return journal; }
    public String getStressLevel() { return stressLevel; }

    @Override
    public int compareTo(MoodEntry other) {
        return other.getDateTime().compareTo(this.dateTime); // Descending order (newest first)
    }

    public String toFileString() {
        return dateTime + "|" + mood + "|" + trigger + "|" + psychologyTest + "|" + 
               journal.replace("|", "~") + "|" + stressLevel;
    }

    public static MoodEntry fromFileString(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length < 6) return null;
        
        try {
            return new MoodEntry(
                parts[0],
                parts[1],
                parts[2],
                parts[3],
                parts[4].replace("~", "|"),
                parts[5]
            );
        } catch (Exception e) {
            return null;
        }
    }
}