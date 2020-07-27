package frontend;

public class LocalDriver {
    public static void main(String[] args) {
        GuiManager.launchGui();
        GuiManager.getGui().update();
    }
}
