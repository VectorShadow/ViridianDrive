package frontend.io;

public class GUIConstants {
    public static final int CHANNEL_SPLASH_SCREEN = 0;
    public static final int CHANNEL_LOGIN = 1;
    public static final int CHANNEL_MAIN_GAME = 2;

    public static final int CANVAS_HEIGHT = 864;
    public static final int CANVAS_WIDTH = 1280;

    public static final int REGION_VIEW_INDEX = 0;

    public static final int REGION_VIEW_ORIGIN_X = 96;
    public static final int REGION_VIEW_ORIGIN_Y = 48;

    public static final int REGION_VIEW_HEIGHT = 25;
    public static final int REGION_VIEW_WIDTH = 30;

    public static final int REGION_VIEW_TILE_DIMENSION = 24;

    public static final int TEXT_TILE_0_HEIGHT = 32;
    public static final int TEXT_TILE_0_WIDTH = 20;

    public static final int LOGIN_SCREEN_HEIGHT = CANVAS_HEIGHT / TEXT_TILE_0_HEIGHT;
    public static final int LOGIN_SCREEN_WIDTH = CANVAS_WIDTH / TEXT_TILE_0_WIDTH;
}
