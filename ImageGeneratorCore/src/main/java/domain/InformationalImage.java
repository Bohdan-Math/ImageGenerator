package domain;

import com.google.common.annotations.VisibleForTesting;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.stream.IntStream;

import static javax.imageio.ImageIO.read;

public class InformationalImage extends BufferedImage {

    public static final int TYPE_INT_RGB = BufferedImage.TYPE_INT_RGB;

    public static InformationalImage madeOf(byte[] bytes) {
        try {
            return new InformationalImage(read(new ByteArrayInputStream(bytes)));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static InformationalImage madeOf(File file) {
        try {
            return new InformationalImage(read(file));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public InformationalImage(BufferedImage bufferedImage) {
        this(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        this.createGraphics()
            .drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);
    }

    public InformationalImage(int width, int height, int typeIntRgb) {
        super(width, height, typeIntRgb);
    }

    public boolean widthLessThan(int allowedMinWidth) {
        return getWidth() < allowedMinWidth;
    }

    public boolean heightLessThan(int allowedMinHeight) {
        return getHeight() < allowedMinHeight;
    }

    public boolean widthMoreThan(int allowedMaxWidth) {
        return getWidth() > allowedMaxWidth;
    }

    public boolean heightMoreThan(int allowedMaxHeight) {
        return getHeight() > allowedMaxHeight;
    }

    public InputStream asStream() {
        return new ByteArrayInputStream(asBytes());
    }

    public byte[] asBytes() {
        try {
            return uncheckedAsBytes();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @VisibleForTesting
    byte[] uncheckedAsBytes() throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(this, "jpg", stream);
        stream.flush();
        byte[] byteArray = stream.toByteArray();
        stream.close();
        return byteArray;
    }

    public InformationalColor averagedColor() {

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

        return new InformationalColor(avrR[0], avrG[0], avrB[0]);
    }

    public InformationalImage getSubImage(int x, int y, int width, int height) {
        return new InformationalImage(getSubimage(x, y, width, height));
    }

    public InformationalImage resizeTo(int newWidth, int newHeight) {
        try {
            return uncheckedResizeTo(newWidth, newHeight);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @VisibleForTesting
    InformationalImage uncheckedResizeTo(int newWidth, int newHeight) throws IOException {
        return new InformationalImage(Thumbnails.of(this).size(newWidth, newHeight).asBufferedImage());
    }
}
