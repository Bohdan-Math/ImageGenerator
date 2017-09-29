package utility.exception;

import domain.BasicImageGenerator;
import domain.BasicSettings;
import domain.ImageGenerator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utility.pattern.InformationalImage;

import static utility.pattern.InformationalImage.TYPE_INT_RGB;


public class MatrixSizeExceptionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void exceptionMessage() throws Exception {

        thrown.expect(MatrixSizeException.class);
        thrown.expectMessage("Number of expected columns (is 10) could not be more than image width (is 1)");

        ImageGenerator imageGenerator = new BasicImageGenerator();
        imageGenerator.setSettings(new BasicSettings() {{
            setIncomeImage(new InformationalImage(1, 1, TYPE_INT_RGB));
            setExpectedColumnsNumber(10);
        }});

        imageGenerator.generateImage();
    }
}