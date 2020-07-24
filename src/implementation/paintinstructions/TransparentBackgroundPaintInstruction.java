package implementation.paintinstructions;

import main.Canvas;

import java.awt.image.BufferedImage;

/**
 * For use with external image files - this will not work well with TextImageSources, due to the way the text
 * is rendered.
 *
 * Text can be rendered with pseudo-transparency by using the ImageMatrix.getBackgroundColorAt() method, on the
 * image matrix below the one currently being updated.
 */
public class TransparentBackgroundPaintInstruction implements PaintInstruction {

    @Override
    public void paint(BufferedImage image, Canvas canvas, int fromX, int fromY, int height, int width) {
        if (image == null)
            return;
        int pixelRGB;
        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++ col) {
                pixelRGB = image.getRGB(col, row);
                if (pixelRGB != canvas.getBaseRGBValue())
                    canvas.paint(fromY + row, fromX + col, pixelRGB);
            }
        }
    }
}
