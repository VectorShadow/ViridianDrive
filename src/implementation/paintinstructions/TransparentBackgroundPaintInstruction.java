package implementation.paintinstructions;

import main.Canvas;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TransparentBackgroundPaintInstruction implements PaintInstruction {

    private static final int TOLERANCE = 96;

    @Override
    public void paint(BufferedImage image, Canvas canvas, int fromX, int fromY, int height, int width) {
        if (image == null)
            return;
        int pixelRGB;
        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++ col) {
                pixelRGB = image.getRGB(col, row);
                if (!(near(pixelRGB, canvas.getBaseRGBValue())))
                    canvas.paint(fromY + row, fromX + col, pixelRGB);
            }
        }
    }

    private boolean near(int rgb1, int rgb2) {
        Color c1 = new Color(rgb1);
        Color c2 = new Color(rgb2);
        return Math.abs(c1.getRed() - c2.getRed()) < TOLERANCE
                && Math.abs(c1.getGreen() - c2.getGreen()) < TOLERANCE
                && Math.abs(c1.getBlue() - c2.getBlue()) < TOLERANCE;
    }
}
