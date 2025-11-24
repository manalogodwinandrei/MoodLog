package Display;

public abstract class Display {
    protected void setColor(int color) {
        System.out.print("\033[38;5;" + color + "m");
    }

    protected void resetColor() {
        System.out.print("\033[0m");
    }

    protected void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    public abstract void show();
}
