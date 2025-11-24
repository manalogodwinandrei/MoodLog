package Display;

public class BannerDisplay extends Display {
    @Override
    public void show() {
        clearScreen();
        setColor(33);
        System.out.println("\t\t\t══════════════════════════════════════════════════════════════════════════════");
        setColor(117);
        System.out.println("\t\t\t    ████    ████   ██████    ██████   ███████   ██      ██████    ███████");
        System.out.println("\t\t\t    ██ ██  ██ ██  ██    ██  ██    ██  ██    ██  ██     ██    ██  ██");
        System.out.println("\t\t\t    ██   ██   ██  ██    ██  ██    ██  ██    ██  ██     ██    ██  ██ █████");
        System.out.println("\t\t\t    ██        ██  ██    ██  ██    ██  ██    ██  ██     ██    ██  ██    ██");
        System.out.println("\t\t\t    ██        ██   ██████    ██████   ███████   ██████  ██████    ███████");
        setColor(152);
        System.out.println("\t\t\t\t\tMonitoring and Assessing Emotional Well-being!");
        setColor(33);
        System.out.println("\t\t\t══════════════════════════════════════════════════════════════════════════════");
        resetColor();
    }
}
