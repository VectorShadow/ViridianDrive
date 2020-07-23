package frontend;

public class Driver {
    public static void main(String[] args) {
        GuiManager.launchGui();
        GuiManager.getGui().update();
    }
}
