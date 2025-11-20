package Display;

public class IconDisplay extends Display {
    @Override
    public void show() {
        clearScreen();
        setColor(45);
        System.out.println("\t\t\t\t\t\t   ████████████████");
        System.out.println("\t\t\t\t\t\t ██               █████");
        System.out.println("\t\t\t\t\t\t ██    █        █    ██");
        System.out.println("\t\t\t\t\t\t ██                  ██");
        System.out.println("\t\t\t\t\t\t ██     \\_______/    ██");
        System.out.println("\t\t\t\t\t\t ██                  ██");
        System.out.println("\t\t\t\t\t\t   ██████████████████");
        resetColor();
        System.out.println("\n\t\t\t\t\t\t         MoodLog\n");
    }
}

