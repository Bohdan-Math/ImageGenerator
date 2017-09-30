package utility.pattern;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

public class InformationalImage extends BufferedImage {
    public static final int TYPE_INT_RGB = BufferedImage.TYPE_INT_RGB;

    public InformationalImage(BufferedImage bufferedImage) {
        this(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        this.createGraphics()
            .drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);
    }

    public InformationalImage(int width, int height, int typeIntRgb) {
        super(width, height, typeIntRgb);
    }

    public Color averagedColor() {

        int width  = getWidth();
        int height = getHeight();
        int pixels = width * height;

        final int[] sumR = {0};
        final int[] sumG = {0};
        final int[] sumB = {0};

        final int[] avrR = {0};
        final int[] avrG = {0};
        final int[] avrB = {0};

        IntStream.range(0, width).forEach( (int i) -> {

            IntStream.range(0, height).forEach((int j) -> {

                int rgb = getRGB(i, j);

                int r = (rgb & 0x00ff0000) >> 16;
                int g = (rgb & 0x0000ff00) >> 8;
                int b =  rgb & 0x000000ff;

                sumR[0] += r;
                sumG[0] += g;
                sumB[0] += b;

            });

            avrR[0] = sumR[0] / pixels;
            avrG[0] = sumG[0] / pixels;
            avrB[0] = sumB[0] / pixels;
        });

        return new Color(avrR[0], avrG[0], avrB[0]);
    }

    public InformationalImage getSubImage(int x, int y, int width, int height) {
        return new InformationalImage(getSubimage(x, y, width, height));
    }
}