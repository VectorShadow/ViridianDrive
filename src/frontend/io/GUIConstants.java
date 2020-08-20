package frontend.io;

public class GUIConstants {
    public static final int CHANNEL_SPLASH_SCREEN = 0;
    public static final int CHANNEL_LOGIN = 1;
    public static final int CHANNEL_AVATAR_SELECTION = 2;
    public static final int CHANNEL_MAIN_GAME = 3;

    public static final int CANVAS_HEIGHT = 864;
    public static final int CANVAS_WIDTH = 1280;

    public static final int CH_MAIN_RG_VIEW_INDEX = 0;
    public static final int CH_MAIN_RG_COMPASS_INDEX = 1;

    public static final int CH_MAIN_RG_VIEW_ORIGIN_X = 96;
    public static final int CH_MAIN_RG_VIEW_ORIGIN_Y = 48;

    public static final int CH_MAIN_RG_VIEW_HEIGHT = 25;
    public static final int CH_MAIN_RG_VIEW_WIDTH = 30;

    public static final int CH_MAIN_RG_VIEW_TILE_DIMENSION = 24;

    public static final int CH_MAIN_RG_COMPASS_TILE_DIMENSION = 71;

    public static final int CH_MAIN_RG_COMPASS_ORIGIN_X = CH_MAIN_RG_VIEW_ORIGIN_X + (CH_MAIN_RG_VIEW_WIDTH * CH_MAIN_RG_VIEW_TILE_DIMENSION) - CH_MAIN_RG_COMPASS_TILE_DIMENSION;
    public static final int CH_MAIN_RG_COMPASS_ORIGIN_Y = CH_MAIN_RG_VIEW_ORIGIN_Y;


    public static final int TEXT_TILE_0_HEIGHT = 32;
    public static final int TEXT_TILE_0_WIDTH = 20;

    public static final int FULL_TEXT_SCREEN_HEIGHT = CANVAS_HEIGHT / TEXT_TILE_0_HEIGHT;
    public static final int FULL_TEXT_SCREEN_WIDTH = CANVAS_WIDTH / TEXT_TILE_0_WIDTH;
}
