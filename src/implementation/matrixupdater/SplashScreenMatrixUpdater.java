package implementation.matrixupdater;

import frontend.GuiManager;
import images.ImageMatrix;
import images.ImageSource;
import images.TextImageSource;

import java.awt.*;

public class SplashScreenMatrixUpdater implements MatrixUpdater {

    private static final Color AZURE = new Color(63, 159, 255);
    private static final Color VIRIDIAN = new Color(80, 160, 48);
    
    private ImageMatrix imageMatrix;
    
    @Override
    public ImageMatrix update(int imageHeight, int imageWidth, int matrixHeight, int matrixWidth, int layer) {
        imageMatrix = new ImageMatrix(imageHeight, imageWidth, matrixHeight, matrixWidth);
        switch (layer) {
            case 0:
                for (int i = 0; i < matrixHeight / 2; ++i) {
                    for (int j = 0; j < matrixWidth; ++j) {
                        imageMatrix.set(i, j, new TextImageSource(new Color(144 + (j / 3), 144 - ((matrixHeight / 2) - i), 255 - ((matrixHeight / 2) - i)), Color.BLACK, ' '));
                    }
                }
                for (int i =  matrixHeight / 3; i < matrixHeight; ++i) {
                    for (int j = 0; j < matrixWidth; ++j) {
                        if (i < matrixHeight / 2 && !(j > 3 * (matrixHeight - (4 + i)))) continue;
                        imageMatrix.set(i, j, new TextImageSource(new Color(196 + (i - (matrixHeight / 2)), 128 - (j / 2), Math.max(0, 32 + (i - j))), Color.BLACK, ' '));
                    }
                }
                mecha();
                sun();
                viridianDrive();
                break;
            case 1:
                write(30, 22, "A game by Vector Shadow Digital Labs", /*new Color(196, 128, 32)*/ GuiManager.BG_RGB, Color.BLUE);
                write(32, 26, "[Press any key to continue]", /*new Color(196, 128, 32)*/ GuiManager.BG_RGB, Color.BLUE);
                break;
                default:
                    throw new IllegalArgumentException("Unhandled layer " + layer);
        }
        return imageMatrix;
    }

    private void mecha() {
        //chassis
        setColorTile(Color.GRAY, 16, 68);
        setColorTile(Color.GRAY, 16, 69);
        setColorTile(Color.GRAY, 16, 70);
        setColorTile(Color.GRAY, 17, 67);
        setColorTile(Color.CYAN, 17, 68);
        setColorTile(Color.CYAN, 17, 69);
        setColorTile(Color.CYAN, 17, 70);
        setColorTile(Color.GRAY, 17, 71);
        setColorTile(Color.GRAY, 18, 67);
        setColorTile(Color.GRAY, 18, 68);
        setColorTile(Color.GRAY, 18, 69);
        setColorTile(Color.GRAY, 18, 70);
        setColorTile(Color.GRAY, 18, 71);
        //right arm
        setColorTile(Color.DARK_GRAY, 18, 66);
        setColorTile(Color.GRAY, 18, 65);
        setColorTile(Color.GRAY, 17, 64);
        setColorTile(Color.GRAY, 17, 63);
        setColorTile(Color.LIGHT_GRAY, 17, 62);
        setColorTile(Color.LIGHT_GRAY, 18, 61);
        setColorTile(AZURE, 16, 62);
        setColorTile(AZURE, 15, 62);
        setColorTile(AZURE, 14, 63);
        setColorTile(AZURE, 13, 63);
        //left arm
        setColorTile(Color.DARK_GRAY, 18, 72);
        setColorTile(Color.GRAY, 18, 73);
        setColorTile(Color.GRAY, 18, 74);
        setColorTile(Color.GRAY, 18, 75);
        setColorTile(Color.GRAY, 17, 74);
        setColorTile(Color.GRAY, 19, 74);
        setColorTile(Color.RED, 17, 73);
        setColorTile(Color.BLACK, 17, 75);
        setColorTile(Color.BLACK, 19, 73);
        setColorTile(Color.ORANGE, 19, 75);
        //legs
        setColorTile(Color.DARK_GRAY, 19, 69);
        setColorTile(Color.GRAY, 20, 68);
        setColorTile(Color.GRAY, 20, 69);
        setColorTile(Color.GRAY, 20, 70);
        setColorTile(Color.DARK_GRAY, 21, 67);
        setColorTile(Color.DARK_GRAY, 21, 71);
        setColorTile(Color.GRAY, 21, 66);
        setColorTile(Color.GRAY, 21, 72);
        setColorTile(Color.GRAY, 22, 66);
        setColorTile(Color.GRAY, 22, 72);
        setColorTile(Color.GRAY, 23, 66);
        setColorTile(Color.GRAY, 23, 72);
        setColorTile(Color.GRAY, 24, 65);
        setColorTile(Color.GRAY, 24, 66);
        setColorTile(Color.GRAY, 24, 67);
        setColorTile(Color.GRAY, 24, 71);
        setColorTile(Color.GRAY, 24, 72);
        setColorTile(Color.GRAY, 24, 73);
    }
    private void sun() {
        setColorTile(AZURE, 0,0);
        setColorTile(AZURE, 0,1);
        setColorTile(AZURE, 0,2);
        setColorTile(AZURE, 0,3);
        setColorTile(AZURE, 0,4);
        setColorTile(AZURE, 0,5);
        setColorTile(AZURE, 1,0);
        setColorTile(AZURE, 1,1);
        setColorTile(AZURE, 1,2);
        setColorTile(AZURE, 1,3);
        setColorTile(AZURE, 1,4);
        setColorTile(AZURE, 1,5);
        setColorTile(AZURE, 2,0);
        setColorTile(AZURE, 2,1);
        setColorTile(AZURE, 2,2);
        setColorTile(AZURE, 2,3);
        setColorTile(AZURE, 2,4);
        setColorTile(AZURE, 3,0);
        setColorTile(AZURE, 3,1);
        setColorTile(AZURE, 3,2);
        setColorTile(AZURE, 3,3);
        setColorTile(AZURE, 4,0);
        setColorTile(AZURE, 4,1);
        setColorTile(AZURE, 4,2);
    }

    private void viridianDrive() {
        V(8, 9);
        I(8, 15);
        R(8, 19);
        I(8, 24);
        D(8, 28);
        I(8, 33);
        A(8, 37);
        N(8, 43);
        D(20, 16);
        R(20, 21);
        I(20, 26);
        V(20, 30);
        E(20, 36);
    }

    private void verticalLine(int row, int col) {
        setColorTile(VIRIDIAN, row, col);
        setColorTile(VIRIDIAN, row + 1, col);
        setColorTile(VIRIDIAN, row + 2, col);
        setColorTile(VIRIDIAN, row + 3, col);
        setColorTile(VIRIDIAN, row + 4, col);
        setColorTile(VIRIDIAN, row + 5, col);
        setColorTile(VIRIDIAN, row + 6, col);
        setColorTile(VIRIDIAN, row + 7, col);
    }

    private void A(int row, int col) { //3, 31
        setColorTile(VIRIDIAN, row + 7, col);
        setColorTile(VIRIDIAN, row + 6, col);
        setColorTile(VIRIDIAN, row + 5, col);
        setColorTile(VIRIDIAN, row + 4, col);
        setColorTile(VIRIDIAN, row + 4, col + 1);
        setColorTile(VIRIDIAN, row + 3, col + 1);
        setColorTile(VIRIDIAN, row + 2, col + 1);
        setColorTile(VIRIDIAN, row + 1, col + 1);
        setColorTile(VIRIDIAN, row + 1, col + 2);
        setColorTile(VIRIDIAN, row, col + 2);
        setColorTile(VIRIDIAN, row + 1, col + 3);
        setColorTile(VIRIDIAN, row + 2, col + 3);
        setColorTile(VIRIDIAN, row + 3, col + 3);
        setColorTile(VIRIDIAN, row + 4, col + 3);
        setColorTile(VIRIDIAN, row + 4, col + 4);
        setColorTile(VIRIDIAN, row + 5, col + 4);
        setColorTile(VIRIDIAN, row + 6, col + 4);
        setColorTile(VIRIDIAN, row + 7, col + 4);
        setColorTile(VIRIDIAN, row + 4, col + 2);

    }
    
    private void D(int row, int col) {
        verticalLine(row, col);
        setColorTile(VIRIDIAN, row, col + 1);
        setColorTile(VIRIDIAN, row, col + 2);
        setColorTile(VIRIDIAN, row + 1, col + 2);
        setColorTile(VIRIDIAN, row + 1, col + 3);
        setColorTile(VIRIDIAN, row + 2, col + 3);
        setColorTile(VIRIDIAN, row + 3, col + 3);
        setColorTile(VIRIDIAN, row + 4, col + 3);
        setColorTile(VIRIDIAN, row + 5, col + 3);
        setColorTile(VIRIDIAN, row + 6, col + 3);
        setColorTile(VIRIDIAN, row + 6, col + 2);
        setColorTile(VIRIDIAN, row + 7, col + 2);
        setColorTile(VIRIDIAN, row + 7, col + 1);

    }

    private void E(int row, int col) {
        verticalLine(row, col);
        setColorTile(VIRIDIAN, row, col + 1);
        setColorTile(VIRIDIAN, row, col + 2);
        setColorTile(VIRIDIAN, row, col + 3);
        setColorTile(VIRIDIAN, row + 3, col + 1);
        setColorTile(VIRIDIAN, row + 3, col + 2);
        setColorTile(VIRIDIAN, row + 7, col + 1);
        setColorTile(VIRIDIAN, row + 7, col + 2);
        setColorTile(VIRIDIAN, row + 7, col + 3);
    }
    
    private void I(int row, int col) {
        setColorTile(VIRIDIAN, row, col);
        setColorTile(VIRIDIAN, row, col + 1);
        setColorTile(VIRIDIAN, row, col + 2);
        setColorTile(VIRIDIAN, row + 1, col + 1);
        setColorTile(VIRIDIAN, row + 2, col + 1);
        setColorTile(VIRIDIAN, row + 3, col + 1);
        setColorTile(VIRIDIAN, row + 4, col + 1);
        setColorTile(VIRIDIAN, row + 5, col + 1);
        setColorTile(VIRIDIAN, row + 6, col + 1);
        setColorTile(VIRIDIAN, row + 7, col);
        setColorTile(VIRIDIAN, row + 7, col + 1);
        setColorTile(VIRIDIAN, row + 7, col + 2);

    }

    private void N(int row, int col) {
        verticalLine(row, col);
        setColorTile(VIRIDIAN, row + 1, col + 1);
        setColorTile(VIRIDIAN, row + 2, col + 2);
        setColorTile(VIRIDIAN, row + 3, col + 2);
        setColorTile(VIRIDIAN, row + 4, col + 2);
        setColorTile(VIRIDIAN, row + 5, col + 2);
        setColorTile(VIRIDIAN, row + 6, col + 3);
        verticalLine(row, col + 4);
    }
    
    private void R(int row, int col) {
        verticalLine(row, col);
        setColorTile(VIRIDIAN, row, col + 1);
        setColorTile(VIRIDIAN, row, col + 2);
        setColorTile(VIRIDIAN, row + 1, col + 2);
        setColorTile(VIRIDIAN, row + 1, col + 3);
        setColorTile(VIRIDIAN, row + 2, col +3);
        setColorTile(VIRIDIAN, row + 3, col + 3);
        setColorTile(VIRIDIAN, row + 3, col + 2);
        setColorTile(VIRIDIAN, row + 3, col + 1);
        setColorTile(VIRIDIAN, row + 4, col + 1);
        setColorTile(VIRIDIAN, row + 4, col + 2);
        setColorTile(VIRIDIAN, row + 5, col + 2);
        setColorTile(VIRIDIAN, row + 5, col + 3);
        setColorTile(VIRIDIAN, row + 6, col + 3);
        setColorTile(VIRIDIAN, row + 7, col + 3);
    }
    
    private void V(int row, int col) {
        setColorTile(VIRIDIAN, row, col);
        setColorTile(VIRIDIAN, row + 1, col);
        setColorTile(VIRIDIAN, row + 2, col);
        setColorTile(VIRIDIAN, row + 3, col);
        setColorTile(VIRIDIAN, row + 3, col + 1);
        setColorTile(VIRIDIAN, row + 4, col + 1);
        setColorTile(VIRIDIAN, row + 5, col + 1);
        setColorTile(VIRIDIAN, row + 6, col + 1);
        setColorTile(VIRIDIAN, row + 6, col + 2);
        setColorTile(VIRIDIAN, row + 7, col + 2);
        setColorTile(VIRIDIAN, row + 6, col + 3);
        setColorTile(VIRIDIAN, row + 5, col + 3);
        setColorTile(VIRIDIAN, row + 4, col + 3);
        setColorTile(VIRIDIAN, row + 3, col + 3);
        setColorTile(VIRIDIAN, row + 3, col + 4);
        setColorTile(VIRIDIAN, row + 2, col + 4);
        setColorTile(VIRIDIAN, row + 1, col + 4);
        setColorTile(VIRIDIAN, row, col + 4);
    }

    private void write(int row, int col, String text, Color background, Color foreground) {
        for (int i = 0; i < text.length(); ++i)
            setImageTile(row, col + i, new TextImageSource(background, foreground, text.charAt(i)));
    }
    private void setColorTile(Color color, int row, int col) {
        setImageTile(row, col, new TextImageSource(color, color, ' '));
    }
    private void setImageTile(int row, int col, ImageSource imageSource) {
        imageMatrix.set(row, col, imageSource);
    }
}
