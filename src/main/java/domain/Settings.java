package domain;

import utility.core.InformationalImage;

import java.awt.*;
import java.util.Map;

public interface Settings {
    Integer MIN_NUMBER_OF_EXPECTED_COLUMNS = 0;
    Integer MAX_NUMBER_OF_EXPECTED_COLUMNS = 128;

    Integer INCOME_IMAGE_ALLOWED_MIN_WIDTH  = 80;
    Integer INCOME_IMAGE_ALLOWED_MIN_HEIGHT = 64;
    Integer INCOME_IMAGE_ALLOWED_MAX_WIDTH  = 5120;
    Integer INCOME_IMAGE_ALLOWED_MAX_HEIGHT = 4096;

    /**
     * The values of ImageSize should be as close as possible to patterns average size, if they different.
     * The best way if all patterns have same width and height. Then set it in in WIDTH and HEIGHT and you are C00L :).
     */
    Integer PATTERN_WIDTH = 16;
    Integer PATTERN_HEIGHT = 16;

    InformationalImage getIncomeImage();

    void setIncomeImage(InformationalImage image);

    Map<Color, InformationalImage> getPatterns();

    void setPatterns(Map<Color, InformationalImage> patterns);

    Integer getExpectedColumnsNumber();

    void setExpectedColumnsNumber(Integer expectedColumnsNumber);

    int getImageWidth();

    int getImageHeight();

    InformationalImage getSubImage(int x, int y, int width, int height);

    String getImageFileName();

    void setImageFileName(String imageFileName);

    void addColumnsNumberListener(ColumnsNumberListener listener);

    void notifyColumnsNumberListeners();

    interface ColumnsNumberListener {
        void changeValueTo(Integer expectedColumnsNumber);
    }
}
